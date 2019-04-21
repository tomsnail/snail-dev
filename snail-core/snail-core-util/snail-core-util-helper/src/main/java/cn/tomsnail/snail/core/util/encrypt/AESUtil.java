package cn.tomsnail.snail.core.util.encrypt;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;
 
/**
 *        AES加解密算法
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年4月4日 下午4:53:54
 * @see 
 */
public class AESUtil {
       
    private static final String KEY_ALGORITHM = "AES";
       
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
       
   
    public static byte[] initSecretKey() {
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        //初始化此密钥生成器，使其具有确定的密钥大小
        //AES 要求密钥长度为 128
        kg.init(128);
        //生成一个密钥
        SecretKey  secretKey =kg.generateKey();
        return secretKey.getEncoded();
    }
       
   
    private static Key toKey(byte[] key){
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }
       
   
    public static byte[] encrypt(byte[] data,Key key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
   
    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
       
   
    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }
       
   
    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }
       
       
       
   
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
   
    public static byte[] decrypt(byte[] data,Key key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }
       
   
    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }
   
    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }
       
    private static String  showByteArray(byte[] data){
        if(null == data){
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for(byte b:data){
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
    
    /**
     *        指定秘钥
     * @methodauthor yangsong
     * @methodversion 0.0.1
     * @date 2016年4月4日 下午4:42:58
     * @see 
     * @param                   
     * @return               
     * @status 正常
     * @exception no
     */
    public static byte[]  initSecretKey(String strKey){
    	 //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        //初始化此密钥生成器，使其具有确定的密钥大小
        //AES 要求密钥长度为 128
        kg.init(128,new SecureRandom(strKey.getBytes()));
        //生成一个密钥
        SecretKey  secretKey = kg.generateKey();
       
        System.out.println(secretKey.getFormat());
        return secretKey.getEncoded();
    }
    
    public static void main(String[] args) throws Exception {
    	String keyStr = MD5Util.md5Encode("ZKJD123!@#456");
    	  System.out.println("keyStr:"+keyStr);
        byte[] key = initSecretKey(keyStr);
        System.out.println(key.length);
        System.out.println("key："+showByteArray(key));
        System.out.println("key："+Base64Util.getBase64(key).length());
           //ScLXS5mCHDNsqhitGrIs7w==
        Key k = toKey(key);
           
        String data ="admin:admin123!@";
        System.out.println(data.getBytes().length);
        System.out.println("加密前数据: string:"+data);
        System.out.println("加密前数据: byte[]:"+showByteArray(data.getBytes()));
        System.out.println();
        byte[] encryptData = encrypt(data.getBytes(), k);
        System.out.println("加密后数据: byte[]:"+showByteArray(encryptData));
        System.out.println("加密后数据: hexStr:"+Base64Util.getBase64(encryptData));
        System.out.println("加密后数据: hexStr:"+Base64Util.getBase64(encryptData).length());
        
        System.out.println();
                byte[] decryptData = decrypt(encryptData, k);
        System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));
        System.out.println("解密后数据: string:"+new String(decryptData));

    }
 
}
