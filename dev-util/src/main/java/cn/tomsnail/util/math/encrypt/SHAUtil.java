package cn.tomsnail.util.math.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/** 
 * 采用SHA加密
 * @author Xingxing,Xie
 * @datetime 2014-6-1 
 */
public class SHAUtil {
	
	
	public static final String SHA1 = "SHA";
	
	public static final String SHA256 = "SHA-256";
	
	public static final String SHA512 = "SHA-512";
	
    /*** 
     * SHA加密 生成40位SHA码
     * @param 待加密字符串
     * @return 返回40位SHA码
     */
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) { 
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    public static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }
    
    public static String shaEncode(String inStr,String shaType) throws Exception {
    	 MessageDigest messageDigest;
         String encdeStr = "";
         try {
             messageDigest = MessageDigest.getInstance(shaType);
             byte[] hash = messageDigest.digest(inStr.getBytes("UTF-8"));
             encdeStr = Hex.encodeHexString(hash);
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         return encdeStr;
    }
    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("1234567890123456789012345678901234567890123456789012345678901234567890");
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + getSHA256Str(str));
        System.out.println("SHA后：" + shaEncode(str,SHAUtil.SHA512));
    }
}
