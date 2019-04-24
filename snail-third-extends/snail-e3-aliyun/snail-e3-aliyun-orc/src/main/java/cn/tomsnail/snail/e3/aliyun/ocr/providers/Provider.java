package cn.tomsnail.snail.e3.aliyun.ocr.providers;

import cn.tomsnail.snail.e3.aliyun.ocr.OrcContent;
import cn.tomsnail.snail.e3.aliyun.ocr.OrcResult;

public interface Provider {
	
	
	
	public OrcResult doOrc(OrcContent orcContent);
	
	
	
	public boolean ready();

}
