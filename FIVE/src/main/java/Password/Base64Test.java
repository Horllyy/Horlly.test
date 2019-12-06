package Password;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 这是原版 下面我将举例对它进行日常化封装使用
 */

public class Base64Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        final Base64.Encoder encoder = Base64.getEncoder();//编码
        final Base64.Decoder decoder = Base64.getDecoder();//解码
        final String text = "字串文字";
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
//解码
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));

    }
}

/**
 * 好吧 封装个屁 更复杂了 其实就是Base64库里几个方法的使用会就好了。。。
 */
class MyBase64{
    public String encoderMethod(String str) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] b=str.getBytes("UTF-8");
        String encoderStr=encoder.encodeToString(b);

        return encoderStr;
    }
    public String decoderMethod(String encoderStr) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        String decoderStr=new String(decoder.decode(encoderStr),"UTF-8");

        return  decoderStr;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        MyBase64 myBase64=new MyBase64();
        String str="I am Horlly.";
        String encoderStr=myBase64.encoderMethod(str);
        System.out.println(encoderStr);
        String decoderStr=myBase64.decoderMethod(encoderStr);
        System.out.println(decoderStr);
    }
}
