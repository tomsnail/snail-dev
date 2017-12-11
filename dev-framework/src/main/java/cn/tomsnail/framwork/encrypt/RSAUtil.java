package cn.tomsnail.framwork.encrypt;

import java.math.BigInteger;  
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.RSAPrivateKeySpec;  
import java.security.spec.RSAPublicKeySpec;  
import java.util.HashMap;

import javax.crypto.Cipher;  

import org.apache.commons.codec.binary.Base64;

import sun.security.rsa.RSAPublicKeyImpl;
  
public class RSAUtil {  
  
    /** 
     * 生成公钥和私钥 
     * @throws Exception 
     * 
     */  
    public static HashMap<String, Object> getKeys() throws Exception{  
        HashMap<String, Object> map = new HashMap<String, Object>();  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
        keyPairGen.initialize(1024, new SecureRandom(MD5Util.md5Encode(System.currentTimeMillis()+"").getBytes()));
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        map.put("public", publicKey);  
        map.put("private", privateKey);  
        return map;  
    }  
    /** 
     * 使用模和指数生成RSA公钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA 
     * /None/NoPadding】 
     *  
     * @param modulus 
     *            模 
     * @param exponent 
     *            指数 
     * @return 
     */  
    public static RSAPublicKey getPublicKey(String modulus, String exponent) {  
        try {  
            BigInteger b1 = new BigInteger(modulus);  
            BigInteger b2 = new BigInteger(exponent);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    public static RSAPublicKey getPublicKey(byte[] b) {  
        try {  

            RSAPublicKey key = new RSAPublicKeyImpl(b);
            return key;
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * 使用模和指数生成RSA私钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA 
     * /None/NoPadding】 
     *  
     * @param modulus 
     *            模 
     * @param exponent 
     *            指数 
     * @return 
     */  
    public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {  
        try {  
            BigInteger b1 = new BigInteger(modulus);  
            BigInteger b2 = new BigInteger(exponent);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
 
  
    /** 
     * 公钥加密 
     *  
     * @param data 
     * @param publicKey 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey)  
            throws Exception {  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        // 模长  
        int key_len = publicKey.getModulus().bitLength() / 8;  
        // 加密数据长度 <= 模长-11  
        String[] datas = splitString(data, key_len - 11);  
        String mi = "";  
        //如果明文长度大于模长-11则要分组加密  
        for (String s : datas) {  
            mi += bcd2Str(cipher.doFinal(s.getBytes()));  
        }  
        return mi;  
    }  
    
    public static String encryptByPublicKeyBase64(String data, RSAPublicKey publicKey)  
            throws Exception {  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
   
        int key_len = publicKey.getModulus().bitLength() / 8;  
        // 加密数据长度 <= 模长-11  
        String[] datas = splitString(data, key_len - 11);  
        String mi = "";  
        //如果明文长度大于模长-11则要分组加密  
        for (String s : datas) {  
            mi += Base64Util.getBase64(cipher.doFinal(s.getBytes()));  
        }  
        return mi;  
    }  
  
    /** 
     * 私钥解密 
     *  
     * @param data 
     * @param privateKey 
     * @return 
     * @throws Exception 
     */  
    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)  
            throws Exception {  
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        //模长  
        int key_len = privateKey.getModulus().bitLength() / 8;  
        byte[] bytes = data.getBytes();  
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);  
        System.err.println(bcd.length);  
        //如果密文长度大于模长则要分组解密  
        String ming = "";  
        byte[][] arrays = splitArray(bcd, key_len);  
        for(byte[] arr : arrays){  
            ming += new String(cipher.doFinal(arr));  
        }  
        return ming;  
    }  
    /** 
     * ASCII码转BCD码 
     *  
     */  
    public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
        byte[] bcd = new byte[asc_len / 2];  
        int j = 0;  
        for (int i = 0; i < (asc_len + 1) / 2; i++) {  
            bcd[i] = asc_to_bcd(ascii[j++]);  
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
        }  
        return bcd;  
    }  
    public static byte asc_to_bcd(byte asc) {  
        byte bcd;  
  
        if ((asc >= '0') && (asc <= '9'))  
            bcd = (byte) (asc - '0');  
        else if ((asc >= 'A') && (asc <= 'F'))  
            bcd = (byte) (asc - 'A' + 10);  
        else if ((asc >= 'a') && (asc <= 'f'))  
            bcd = (byte) (asc - 'a' + 10);  
        else  
            bcd = (byte) (asc - 48);  
        return bcd;  
    }  
    /** 
     * BCD转字符串 
     */  
    public static String bcd2Str(byte[] bytes) {  
        char temp[] = new char[bytes.length * 2], val;  
  
        for (int i = 0; i < bytes.length; i++) {  
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);  
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
  
            val = (char) (bytes[i] & 0x0f);  
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
        }  
        return new String(temp);  
    }  
    /** 
     * 拆分字符串 
     */  
    public static String[] splitString(String string, int len) {  
        int x = string.length() / len;  
        int y = string.length() % len;  
        int z = 0;  
        if (y != 0) {  
            z = 1;  
        }  
        String[] strings = new String[x + z];  
        String str = "";  
        for (int i=0; i<x+z; i++) {  
            if (i==x+z-1 && y!=0) {  
                str = string.substring(i*len, i*len+y);  
            }else{  
                str = string.substring(i*len, i*len+len);  
            }  
            strings[i] = str;  
        }  
        return strings;  
    }  
    /** 
     *拆分数组  
     */  
    public static byte[][] splitArray(byte[] data,int len){  
        int x = data.length / len;  
        int y = data.length % len;  
        int z = 0;  
        if(y!=0){  
            z = 1;  
        }  
        byte[][] arrays = new byte[x+z][];  
        byte[] arr;  
        for(int i=0; i<x+z; i++){  
            arr = new byte[len];  
            if(i==x+z-1 && y!=0){  
                System.arraycopy(data, i*len, arr, 0, y);  
            }else{  
                System.arraycopy(data, i*len, arr, 0, len);  
            }  
            arrays[i] = arr;  
        }  
        return arrays;  
    }  
    
    
    public static String decryptBase64(String string,RSAPrivateKey keyPair) {
        return new String(decrypt(Base64.decodeBase64(string),keyPair));
    }
     
    private static byte[] decrypt(byte[] string,RSAPrivateKey keyPair) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPair);
            byte[] plainText = cipher.doFinal(string);
            return plainText;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) throws Exception {
//    	HashMap<String, Object> keys = RSAUtil.getKeys();
//    	HashMap<String, Object> keys1 = RSAUtil.getKeys();
//    	String data = "村村淘系统";
//    	RSAPublicKey p = (RSAPublicKey)keys.get("public");
//    	System.out.println(Base64Util.getBase64(p.getEncoded()));
//    
//    	RSAPrivateKey pr = (RSAPrivateKey)keys.get("private");
//    	System.out.println(p.getModulus().toString());
//    	System.out.println(p.getPublicExponent());
//    	System.out.println(pr.getModulus().toString());
//    	System.out.println(pr.getPrivateExponent());
//    	//产生公钥
//    	System.out.println(Base64Util.getBase64(p.getEncoded()));

    	
    	//testJs();
    	testApp();
    	    	
	}
    
    public static void testJs(){
    	String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWzU35QtkseX8iI/A92sGb0AaPHbc970DPh8lWqgZY+gaRJ1F4LoW9IjW2KrwllvhCltIXcCSAm7XLFiXbcc/iclaD0sX0e31h+bCdygx082W2/pNy7vdHxv1UUNWoUMcx6Wsz0/kQ69WLaW4XJ1q2l8sz/2CKhT7rcYHtCg2XWQIDAQAB";
    	String m = "105896745332542798144264415826785271534080869787354843168270677740821559149448226553527117408811380692827381244477485947881471365683302139542522725658974424739230533351317803653803912581167650394034866982897909596253841414776317352234876721934280122108585336141069291830562718504253032018696992635064094136153";
    	String e = "4401524852920435511924199592782139549549663385579971509076847066023741201505973253762117091438457680505085470954677078932955856998662053925474646454598872024584523785857659340194624323046929825943327054254480000530931444417723770970654464660638503948842124854147557093430348857782577973389805620712557259073";
    	String s ="cYurOyCjEprZ7K6buPfr99UblZmEGLcQuIxkYr8ktbGy+l4Sbl9Z4I+H7fIj3H7f6TShTLHdlQGffotB0TtnqfO/h8gCpQB3Hypq86zAOrZ/62HLStG+0Adl32Kk58z8sy+3GBtfgnkowIhoNNeH70P4suk9siD3nsxeaaLl/D4=";
    	System.out.println(decryptBase64(s,getPrivateKey(m, e)));
    }
    
    public static void testApp() throws Exception{
    	String key = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMTNUbFL1wsJxlg1no5CZmITYiWT8fzqTbVOrMXiRN1/Kvibsx4CBJ972ibYkAlFKeX8jYhCenB6Ntqviqal/OUCAwEAAQ==";
    	String m = "10307358549578221136965987810249595698980027128691093517653110353604907505877575381400332636009481664103315864071481089696529066166147853491733720300125413";
    	String e = "1740095136984198829049112549134100230610418849685494585948609380232306889925191054651292598717336025962157724169274937020553426710060679294416373453088673";
	   	String s = encryptByPublicKeyBase64("123456a", getPublicKey(Base64Util.getBytesFromBase64(key)));
	   	System.out.println(Base64.decodeBase64(s).length*8);
	   	
	   	System.out.println(decryptBase64(s,getPrivateKey(m, e)));
    }
}