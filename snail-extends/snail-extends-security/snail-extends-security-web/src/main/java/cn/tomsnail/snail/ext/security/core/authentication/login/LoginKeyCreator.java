package cn.tomsnail.snail.ext.security.core.authentication.login;

import java.util.Map;

import cn.tomsnail.snail.ext.security.core.authentication.AuthentToken;
import cn.tomsnail.snail.ext.security.core.authentication.IKeyCreator;
import cn.tomsnail.snail.ext.security.core.authentication.Key;



/**
 *        登录key创建者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午2:17:16
 * @see 
 */
public class LoginKeyCreator implements IKeyCreator{

	@Override
	public Key getKey(String key, Map<String, Object> addtionMap) {
		return null;
	}

	@Override
	public boolean validtor(Key key, AuthentToken token) {
		return false;
	}

}
