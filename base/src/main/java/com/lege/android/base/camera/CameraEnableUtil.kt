package com.lege.android.base.camera

import android.hardware.Camera
import android.os.Build
import android.util.Log


/**
 * Description:
 * Created by zhonghaojie on 2019-08-21.
 */
class CameraEnableUtil {
    companion object{
        @JvmStatic
        private fun checkCameraFacing(facing: Int): Boolean {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
                return false
            }
            val cameraCount = Camera.getNumberOfCameras()
            val info = Camera.CameraInfo()
            for (i in 0 until cameraCount) {
                Camera.getCameraInfo(i, info)
                if (facing == info.facing) {
                    return true
                }
            }
            return false
        }

        /**
         * 检查设备是否有摄像头
         * @return true,有相机；false,无相机
         */
        @JvmStatic
        fun hasCamera(): Boolean {
            Log.i("相机","hasBackFacingCamera  ${hasBackFacingCamera()}")
            Log.i("相机","hasFrontFacingCamera  ${hasFrontFacingCamera()}")
            return hasBackFacingCamera() || hasFrontFacingCamera()
        }

        /**检查设备是否有后置摄像头
         * @return  true,有后置摄像头；false,后置摄像头
         */
        @JvmStatic
        fun hasBackFacingCamera(): Boolean {
            val CAMERA_FACING_BACK = 0
            return checkCameraFacing(CAMERA_FACING_BACK)
        }

        /**检查设备是否有前置摄像头
         * @return  true,有前置摄像头；false,前置摄像头
         */
        @JvmStatic
        fun hasFrontFacingCamera(): Boolean {
            val CAMERA_FACING_BACK = 1
            return checkCameraFacing(CAMERA_FACING_BACK)
        }


        /**
         * 判断相机是否可用
         * @return true,相机驱动可用，false,相机驱动不可用
         */
        @JvmStatic
        fun isCameraCanUse(): Boolean {
            var canUse = true
            var mCamera: Camera? = null
            try {
                mCamera = Camera.open()
            } catch (e: Exception) {
                canUse = false
            }

            if (canUse) {
                mCamera?.release()
                mCamera = null
            }
            return canUse
        }
    }

}