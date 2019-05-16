package cn.tomsnail.snail.ext.security.core.authentication.request;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import cn.tomsnail.snail.core.util.math.encrypt.HmacSha1Util;
import cn.tomsnail.snail.core.util.math.encrypt.MD5Util;
import cn.tomsnail.snail.ext.security.core.authentication.AuthentToken;
import cn.tomsnail.snail.ext.security.core.authentication.IKeyCreator;
import cn.tomsnail.snail.ext.security.core.authentication.Key;


/**
 *        默认的tokenkey创建者，用于请求
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午2:16:46
 * @see 
 */
public class DefaultTokenKeyCreator implements IKeyCreator{
	
	private static ObjectMapper objectMapper = new ObjectMapper();;

	@Override
	public Key getKey(String key, Map<String, Object> addtionMap) {
		Key _key = new Key();
		try {
			String str = String.valueOf(key+System.currentTimeMillis());
			String md5Str = MD5Util.md5Encode(str);
			_key.setKey(md5Str);
			return _key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean validtor(Key key, AuthentToken token) {
		try {
			String paramKey = objectMapper.writeValueAsString(token.getAddtionMap());
			return HmacSha1Util.getSignature(paramKey.getBytes(), key.getKey().getBytes()).equals(token.getFingerprint());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
