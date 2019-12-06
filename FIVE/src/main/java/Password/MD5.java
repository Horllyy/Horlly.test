package Password;

import java.security.MessageDigest;
import java.util.Random;
public class MD5 {
    public static String md5str(String data) {
        MessageDigest mdDigest;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        data = data +88;
        try {
            mdDigest = MessageDigest.getInstance("md5");
            // 进过计算 md5算法处理之后的字节码
            byte[] databytes = data.getBytes();
            mdDigest.update(databytes);
            byte[] md5dis = mdDigest.digest();
            // 如何把一个16长度的 byte 数组变成32位长度的16进制形式

            for (int i = 0; i < md5dis.length; i++) {
                byte b = md5dis[i];
                // 一个 byte 等于8个字节，4个 bit 表示一个16进制
                // 变成16进制形式，最长2位
                String tempstr = Integer.toHexString(b & 0xff);
                if (tempstr.length() < 2) {
                    tempstr = "0" + tempstr;
                }
                stringBuffer.append(tempstr);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        MD5 md5Test=new MD5();
        String str="123456";
        String md5Str=md5Test.md5str(str);
        System.out.println(md5Str);
    }
}