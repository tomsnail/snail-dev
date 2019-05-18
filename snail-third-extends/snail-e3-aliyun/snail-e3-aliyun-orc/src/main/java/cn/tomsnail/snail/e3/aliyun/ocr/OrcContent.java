package cn.tomsnail.snail.e3.aliyun.ocr;

public class OrcContent {

	private String imgContent;
	
	private OrcType type;
	
	private OrcType subType;

	public String getImgContent() {
		return imgContent;
	}

	public void setImgContent(String imgContent) {
		this.imgContent = imgContent;
	}

	public OrcType getType() {
		return type;
	}

	public void setType(OrcType type) {
		this.type = type;
	}

	public OrcType getSubType() {
		return subType;
	}

	public void setSubType(OrcType subType) {
		this.subType = subType;
	}

	public OrcContent() {
	}

	public OrcContent(String imgContent, OrcType type, OrcType subType) {
		super();
		this.imgContent = imgContent;
		this.type = type;
		this.subType = subType;
	}
	public OrcContent(String imgContent, OrcType type) {
		this.imgContent = imgContent;
		this.type = type;
	}
	
	public boolean face(){
		if(subType==null||(subType.getType()%2!=0)){
			return true;
		}
		return false;
	}
	
	
}
