package cn.tomsnail.snail.core.util.string;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成随机数
 * @author nero
 *
 */
public class RandomUtils {

	private static final char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final char[] numSequence = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 
	 * 随机生成字母+数字
	 * 
	 * @param size
	 * 
	 * @return
	 */
	public static String getRandSeq(int size) {
		StringBuffer randSB = new StringBuffer();
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < size; i++) {
			randSB.append(String.valueOf(codeSequence[random.nextInt(34)]));
		}
		return randSB.toString();
	}

	/**
	 * 
	 * 生成随机数字
	 * 
	 * @param size
	 *        
	 * @return
	 */
	public static String getRandNum(int size) {
		StringBuffer randSB = new StringBuffer();
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < size; i++) {
			randSB.append(String.valueOf(numSequence[random.nextInt(9)]));
		}
		return randSB.toString();
	}

	/*
	 * 生成随机字母和数字
	 */
	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月12日 下午4:23:24
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String getRandLetterAndNum(int size) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		String sRand = "";
		String codeList = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz0123456789";
		for (int i = 0; i < size; i++) {
			int a = random.nextInt(codeList.length() - 1);
			String rand = codeList.substring(a, a + 1);
			sRand += rand;

		}
		return sRand;
	}
	
	   /**
			*        
			* @methodauthor yangsong
			* @methodversion 0.0.1
			* @date 2017年9月12日 下午4:23:24
			* @see 
			* @param                   
			* @return               
			* @status 正常
			* @exception no
			*/
		public static String getRandLetterAndNum(int size,long seed) {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			random.setSeed(seed);
			String sRand = "";
			String codeList = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz0123456789";
			for (int i = 0; i < size; i++) {
				int a = random.nextInt(codeList.length() - 1);
				String rand = codeList.substring(a, a + 1);
				sRand += rand;

			}
			return sRand;
		}
}
