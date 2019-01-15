package org.centralplains.daas;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author flysLi
 * @date 2018/1/31
 */
public class Util {
    public static void main(String[] args) throws ParseException {
    }

    /**
     * 校验手机号码格式
     *
     * @param phone
     * @return {Boolean}
     * @auther flysLi
     */
    public static boolean checkMobile(String phone) {
        String regExp = "^1\\d{10}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 自定义正则验证
     *
     * @param pattern
     * @param source
     * @return
     */
    public static boolean regCheck(String pattern, String source) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.matches();
    }

    /**
     * 图片转化成base64字符串
     *
     * @param imgFile 文件所在地址
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 将流转换成base64
     *
     * @param inputStream
     * @return
     */
    public static String getImageStr(InputStream inputStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = IOUtils.toByteArray(inputStream);
            return getImageStr(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字节转换为base64格式
     *
     * @param bytes 字节流
     * @return base64
     */
    public static String getImageStr(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * 将文件转换成字节数组
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        byte[] data = getBytes(in);
        in.close();

        return data;
    }

    /**
     * 将输入流转换成字节数组
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * 自定义正则表达式验证
     *
     * @param reg 正则表达式
     * @param str 被验证字符
     * @return {Boolean}
     * @auther flysLi
     */
    public static boolean regularCheck(String reg, String str) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 检查是否是合格的身份证号码
     *
     * @param no
     * @return
     */
    public static boolean isIdNo(String no) {
        // 对身份证号进行长度等简单判断
        if (no == null || no.length() != 18 || !no.matches("\\d{17}[0-9X]")) {
            return false;
        }
        // 1-17位相乘因子数组
        int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // 18位随机码数组
        char[] random = "10X98765432".toCharArray();
        // 计算1-17位与相应因子乘积之和
        int total = 0;
        for (int i = 0; i < 17; i++) {
            total += Character.getNumericValue(no.charAt(i)) * factor[i];
        }
        // 判断随机码是否相等
        return random[total % 11] == no.charAt(17);
    }

}
