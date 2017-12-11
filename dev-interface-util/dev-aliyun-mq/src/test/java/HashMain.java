import java.math.BigInteger;

import cn.tomsnail.framwork.encrypt.AES;
import cn.tomsnail.framwork.encrypt.AESUtil;
import cn.tomsnail.framwork.encrypt.MD5Util;
import cn.tomsnail.mq.aliyun.MD5;


public class HashMain {

	public static void main(String[] args) {
		try {
			String md5 = MD5.getInstance().getMD5String("1232423525w");
			System.out.println(md5);
			String md5_2 = hexString2binaryString(md5);
			byte[] bs = MD5.getInstance().hash("1232423525w");
			//10001110001101001110000100111111111110101110100011011000000000000000000000000000000000000000000000000000000000000000000000000000
			System.out.println(md5_2);
			//System.out.println("10001110001101001110000100111111111110101110100011011000000000000000000000000000000000000000000000000000000000000000000000000000");
			BigInteger t = new BigInteger(md5_2);
			System.out.println(t.longValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String hexString2binaryString(String hexString)  
    {  
        if (hexString == null || hexString.length() % 2 != 0)  
            return null;  
        String bString = "", tmp;  
        for (int i = 0; i < hexString.length(); i++)  
        {  
            tmp = "0000"  
                    + Integer.toBinaryString(Integer.parseInt(hexString  
                            .substring(i, i + 1), 16));  
            bString += tmp.substring(tmp.length() - 4);  
        }  
        return bString;  
    }  
}
