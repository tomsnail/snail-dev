package cn.tomsnail.ocr.providers;

import cn.tomsnail.ocr.OrcContent;
import cn.tomsnail.ocr.OrcResult;

public interface Provider {
	
	
	
	public OrcResult doOrc(OrcContent orcContent);
	
	
	
	public boolean ready();

}
