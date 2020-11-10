package com.lege.android.base.file;

import android.content.Context;

import com.lege.android.base.log.APPLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static android.content.Context.MODE_APPEND;

public class FileUtil {
    public static String readFile(File file) {
        Reader read = null;
        String content = "";
        String result = "";
        BufferedReader br = null;
        try {
            read = new FileReader(file);
            br = new BufferedReader(read);
            if (br != null) {
                while (true) {
                    content = br.readLine();
                    if (content == null) {
                        break;
                    }
                    result += content.trim() + "\r\n";

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                read.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void writeToFile(Context context, String name, String content) {
        try {
            FileOutputStream fops = context.openFileOutput(name, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fops);
            osw.write(content);
            osw.flush();
            osw.close();
            fops.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDateToFile(File file, String str) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(str.toString());
            osw.flush();
            osw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            APPLog.log("exception====" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            APPLog.log("exception====" + e.getMessage());
        }
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return;
        }

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    //删除崩溃日志
    public static void deleteSystemCrashLog() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "/data/corefile";
                File file = new File(path);
                if (file.isDirectory()) {
                    List<File> files = Arrays.asList(file.listFiles());
                    Iterator<File> iterator = files.iterator();
                    while (iterator.hasNext()) {
                        File child = iterator.next();
                        child.delete();
                    }
                    file.delete();
                }
            }
        }).start();

    }

    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     */
    public static boolean copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
