package cn.tomsnail.config.server.s.file;

import cn.tomsnail.framwork.encrypt.DESUtil;


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
