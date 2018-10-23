package cn.tomsnail.ocr;

public enum OrcType {

	IDCARD(10),IDCARD_FACE(11),IDCARD_BACK(12),DRIVING(20),VEHICLE(30),BUSINESS(40),BANKCARD(50),PASSPORT(60),RESIDENCE(70);
	
	private int type;

	OrcType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	
}
