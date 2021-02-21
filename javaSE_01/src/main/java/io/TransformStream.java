package io;

import org.junit.Test;

import java.io.*;

/**
 * @Author：Dawn
 * @Date：2021/2/21 22:49
 * @Desc： 测试转换流
 */
public class TransformStream {
    /**
     * 解码：字节数组  -> 字符串
     */
    @Test
    public void test01() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\temp\\b.txt");
        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        BufferedReader br = new BufferedReader(isr);

        String str = null;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }

        br.close();
    }

    /**
     * 编码：字符串 -> 字节数组
     */
    @Test
    public void test02() throws IOException {
        String str = "dawn is best. come on";

        //本例测试转换流，因此用字节流操作文本文件
        FileOutputStream fos = new FileOutputStream("D:\\temp\\b-transform.txt");
        OutputStreamWriter ows = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(ows);

        bw.write(str);

        bw.close();
    }
}
