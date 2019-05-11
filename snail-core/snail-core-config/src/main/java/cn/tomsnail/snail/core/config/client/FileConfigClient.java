package cn.tomsnail.snail.core.config.client;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

/**
 *        文件配置客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 下午12:15:01
 * @see 
 */
public class FileConfigClient extends AConfigCilent{
	
	private String fileName = "config";

	public FileConfigClient(AConfigCilent configCilent) {
			this.configCilent = configCilent;
			if(isDo()){
				this.inited = true;
			}
	}

	public FileConfigClient() {
		this.inited = true;
	}

	@Override
	public String getConfig(String key) {
		String value = ConfigHelp.getInstance(fileName).getLocalConfig(key, null);
		if(isEmpty(value)){
			value =  null;
		}
		return value;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected boolean isDo() {
		return true;
	}
	
	@Override
	protected String getName() {
		return "file";
	}

}
