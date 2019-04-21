package cn.tomsnail.snail.core.config.client;


/**
 *        默认客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 下午12:15:01
 * @see 
 */
public class DefaultConfigClient extends AConfigCilent{
	

	public DefaultConfigClient(AConfigCilent configCilent) {
			this.configCilent = configCilent;
	}

	public DefaultConfigClient() {
		this.inited = true;
	}

	@Override
	public String getConfig(String key) {
		return null;
	}

	@Override
	protected boolean isDo() {
		return true;
	}
	
	@Override
	protected String getName() {
		return "default";
	}
}
