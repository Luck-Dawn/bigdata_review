package io;

import java.io.*;

/**
 * @Author：Dawn
 * @Date：2021/2/21 22:24
 * @Desc： 文件的复制 字节流 + 缓存流的练习
 */
public class ByteStreamTest {
    public static void main(String[] args) {
        //测试复制
        copyFile("D:\\temp\\b.txt", "D:\\temp\\b-copy.txt");
    }

    /**
     * 字节流复制，字符流同样的套路
     * @param srcPath
     * @param destPath
     */
    public static void copyFile(String srcPath, String destPath) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //创建输入 输出流
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(destPath);

            //创建缓存流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //读取文件，创建一个瓢容器， 每次来取数据
            byte[] b = new byte[1024];
            int len = 0;

            while ((len = bis.read(b)) != -1) {
                //将文件读取到目的地
                bos.write(b, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流,先关bos输出流，那个先那个，就后关闭谁，这里只需要关缓存流，字节流会自动关闭的！
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
