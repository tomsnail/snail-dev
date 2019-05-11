package cn.tomsnail.snail.core.util.math.str;

public enum MathDefine{
	
	RIGHT_BACKET("RIGHT_BACKET",4,0),
	LEFT_BRACKET("LEFT_BRACKET",3,1),
	MUL("MUL",2,2),
	DIV("DIV",2,3),
	ADD("ADD",1,4),
	DEL("DEL",1,5),
	DIGIT("DIGIT",0,0);
	
	private String name;
	
	private int level;
	
	private int type;
	
	MathDefine(String name,int level,int type){
		this.name = name;
		this.level = level;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
}