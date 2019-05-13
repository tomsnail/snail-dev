package cn.tomsnail.snail.ext.security.core.authentication;

import java.util.Map;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午1:58:13
 * @see 
 */
public class DefaultAuthenticationService implements IAuthenticationService{

	private IKeyStore keyStore;
	
	private IKeyCreator keyCreator;
	
	@Override
	public AuthentToken createToken(String key, Map<String, Object> addtionMap) {
		//创建
		Key _key = keyCreator.getKey(key, addtionMap);
		//保存
		keyStore.save(_key);
		//组成token返回
		AuthentToken authentToken = new AuthentToken();
		authentToken.setExpire(_key.getExpire());
		authentToken.setKey(key);
		authentToken.setToken(_key.getPubKey());
		authentToken.setStartTime(System.currentTimeMillis());
		return authentToken;
	}

	@Override
	public boolean validateToken(AuthentToken token) {
		//获取
		Key _key = keyStore.get(token.getKey());
		if(_key==null){
			return false;
		}
		return keyCreator.validtor(_key, token);
	}

	public IKeyStore getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(IKeyStore keyStore) {
		this.keyStore = keyStore;
	}

	public IKeyCreator getKeyCreator() {
		return keyCreator;
	}

	public void setKeyCreator(IKeyCreator keyCreator) {
		this.keyCreator = keyCreator;
	}

	
	
}
