package cn.tomsnail.weixin.util.payment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.tomsnail.weixin.util.common.SecUtil;

public class SignModel {

	StringBuffer sb = new StringBuffer();
	List<String> sbList = new ArrayList<String>();

	public StringBuffer put(String key, String value) {
		sb.append(key).append("=").append(value).append("&");
		return sb;
	}

	public String createSign(String key) {

		sb.append("key=" + key);
		System.out.println("md5 sb:" + sb);
		String sign = SecUtil.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		System.out.println("签名:" + sign);
		return sign;

	}

	public void puts(String key, String value) {
		sbList.add(key + "=" + value);
	}

	public String createSHA1() {
		String[] arr = new String[sbList.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sbList.get(i);
		}

		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i + 1 != arr.length)
				content.append(arr[i] + "&");
			else
				content.append(arr[i]);
		}
		System.out.println(content);
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			return byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
	
	public static void main(String[] args) {
		String t = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VB4JbwEHYLFh63lzjnXu7HThj-OXAvw9MsAu4VIuS5V0aHCj1nOB0sXU7JC5o2Neow&noncestr=e17a886efc21fa45b9dc49a17c29dcf1&timestamp=null&url=https://weixin.yzdhxp.com/payTest/pay.html?code=021wVeZo1stFHn0h40Zo1KSgZo1wVeZC&state=STATE";
		String s = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VB4JbwEHYLFh63lzjnXu7HThj-OXAvw9MsAu4VIuS5V0aHCj1nOB0sXU7JC5o2Neow&noncestr=e17a886efc21fa45b9dc49a17c29dcf1&timestamp=null&url=https://weixin.yzdhxp.com/payTest/pay.html?code=041QCUIZ1uliDX0DFMKZ1yo6JZ1QCUIT&state=STATE";
		try {
			MessageDigest md = null;
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(s.toString().getBytes());
			System.out.println(byteToStr(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
