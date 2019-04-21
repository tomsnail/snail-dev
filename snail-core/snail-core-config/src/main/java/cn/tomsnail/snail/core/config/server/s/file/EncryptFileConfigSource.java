package cn.tomsnail.snail.core.config.server.s.file;

import cn.tomsnail.snail.core.util.math.encrypt.DESUtil;

public class EncryptFileConfigSource extends DefaultFileConfigSource{
	
	private static final String key = "ZKJD_20160620";
	
	@Override
	public String parse(String value){
		try {
			return DESUtil.decrypt(value, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
