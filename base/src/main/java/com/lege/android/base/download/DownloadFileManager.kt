package com.lege.android.base.download

import android.app.Application
import android.util.ArrayMap
import android.util.Log
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection
import com.liulishuo.filedownloader.util.FileDownloadUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

interface DownloadListener {
    fun pending()
    fun onStart(soFarBytes: Int, totalBytes: Int)
    fun onProgress(progress: Int)
    fun onSuccess()
    fun onError(msg: String)
    fun onPause(soFarBytes: Int, totalBytes: Int)
}

object DownloadFileManager {
    private const val TAG = "DownloadFileManager"
    private var taskID = -1
    private var task: BaseDownloadTask? = null

    private var listener: FileDownloadListener = object : FileDownloadListener() {
        override fun retry(task: BaseDownloadTask?, ex: Throwable?, retryingTimes: Int, soFarBytes: Int) {
            super.retry(task, ex, retryingTimes, soFarBytes)
            Log.i(TAG, "重试   retryingTimes   $retryingTimes  soFarBytes  $soFarBytes")
        }

        override fun warn(task: BaseDownloadTask?) {
            Log.i(TAG, "warn")
        }

        override fun completed(task: BaseDownloadTask?) {
            Log.i(TAG, "下载完成  文件大小：${getMB(task?.smallFileTotalBytes ?: 0)}")
            downloadListener.forEach {
                it.value.onSuccess()
            }
        }

        override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
            Log.i(TAG, "加入队列，等待下载")
            downloadListener.forEach {
                it.value.pending()
            }
        }

        override fun connected(task: BaseDownloadTask?, etag: String?, isContinue: Boolean, soFarBytes: Int, totalBytes: Int) {
            super.connected(task, etag, isContinue, soFarBytes, totalBytes)
            Log.i(TAG, "已连接，正在下载  文件大小：${getMB(totalBytes)}")
            downloadListener.forEach {
                it.value.onStart(soFarBytes, totalBytes)
            }
        }

        override fun error(task: BaseDownloadTask?, e: Throwable?) {
            Log.i(TAG, "下载出错 ")
            e?.printStackTrace()
            downloadListener.forEach {
                it.value.onError("下载更新包失败")
            }
        }

        override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
            Log.i(TAG, "下载中...  $soFarBytes     $totalBytes")
            Log.i(TAG, "下载中...  ${100 * soFarBytes.toFloat() / totalBytes.toFloat()}")
            var progress = (100 * soFarBytes.toFloat() / totalBytes.toFloat())
            if (progress > 100) {
                progress = 100f
            }
            downloadListener.forEach {
                it.value.onProgress(progress.toInt())
            }
        }

        override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
            Log.i(TAG, "暂停下载   ${(100 * soFarBytes.toFloat() / totalBytes.toFloat()).toInt()}")
            downloadListener.forEach {
                it.value.onPause(soFarBytes, totalBytes)
            }
        }

    }
    private var downloadListener: ArrayMap<String, DownloadListener> = ArrayMap()
    fun registerDownloadListener(name: String, listener: DownloadListener) {
        if (!downloadListener.contains(name)) {
            downloadListener.put(name, listener)
        }
    }

    fun unregisterDownloadListener(name: String) {
        if (downloadListener.contains(name)) {
            downloadListener.remove(name)
        }
    }

    private var localPath: String = ""
    private var isInit = false
    fun init(context: Application) {
        FileDownloader.setupOnApplicationOnCreate(context)
                .connectionCreator(FileDownloadUrlConnection
                        .Creator(FileDownloadUrlConnection.Configuration()
                                .connectTimeout(15 * 1000) // set connection timeout.
                                .readTimeout(15 * 1000) // set read timeout.
                        ))
                .commit()

        isInit = true
    }


    fun pause() {
        if (taskID != -1) {
            FileDownloader.getImpl().pause(taskID)
        }

    }

    //region 版本更新相关下载

    //版本更新之前还要删除一些本地文件，所以这个方法单独给版本更新，其他下载写其他的方法
    fun downloadForVersionUpdate(url: String, localPath: String) {
        if (!isInit) {
            return
        }
        DownloadFileManager.localPath = localPath
        doAsync {
            deleteLocalFile()
            deleteLocalOTAFile()
            uiThread {
                task = FileDownloader.getImpl()
                        .create(url)
                        .setAutoRetryTimes(3)
                        .setPath(localPath)
                        .setCallbackProgressMinInterval(1000)
                        .setListener(listener)
                taskID = task?.start() ?: -1
            }
        }
    }

    //版本更新之前还要删除一些本地文件，所以这个方法单独给版本更新，其他下载写其他的方法
    fun cancelForVersionUpdate() {

        doAsync {
            //清除所有的下载任务
            FileDownloader.getImpl().clearAllTaskData()
            //删除单个任务的database记录
            FileDownloader.getImpl().clear(taskID, localPath)
            deleteLocalFile()
            deleteLocalOTAFile()
        }

    }

    private fun deleteLocalFile() {
        val file = File(localPath)
        if (file.exists()) {
            file.delete()
        }
        val fileTemp = File(FileDownloadUtils.getTempPath(localPath))
        if (fileTemp.exists()) {
            fileTemp.delete()
        }
    }

    //下载之前把
    private fun deleteLocalOTAFile() {
        try {
            val path = "/cache"
            val fileCache = File(path)
            fileCache.listFiles().forEach {
                if (it.isDirectory && it.name == "recovery") {
                    it.listFiles().forEach { subFile ->
                        val name = subFile.name
                        subFile.delete()
                        Log.i("删除文件", "recovery目录下   $name")
                    }
                } else if (it.isFile && it.name == "saved.file") {
                    Log.i("删除文件", "cache目录下   saved.file")
                    it.delete()
                }
            }
        } catch (e: Exception) {

        }

    }

    //endregion
    private fun getMB(byte: Int): Double {
        return BigDecimal(byte / 1024.0 / 1024.0).setScale(1, RoundingMode.HALF_UP).toDouble()
    }

}