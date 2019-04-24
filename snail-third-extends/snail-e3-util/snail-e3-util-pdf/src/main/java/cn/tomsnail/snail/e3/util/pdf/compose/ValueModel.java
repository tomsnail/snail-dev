package cn.tomsnail.snail.e3.util.pdf.compose;

/**
 *        值模型，如果字段属性为空，值使用ComposeModel中的textModel，如果ComposeModel的textModel也为空，则使用默认字段属性DEFAULT_TEXT_MODEL
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 下午2:32:14
 * @see 
 */
public class ValueModel {
	/**
	 * 值
	 */
	private String value;
	/**
	 * 字段属性
	 */
	private TextModel textModel;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TextModel getTextModel() {
		return textModel;
	}

	public void setTextModel(TextModel textModel) {
		this.textModel = textModel;
	}

	/**
	 * @param value 值
	 */
	public ValueModel(String value) {
		this.value = value;
	}
	
	/**
	 * @param value 值
	 * @param textModel 字段属性
	 */
	public ValueModel(String value,TextModel textModel) {
		this.value = value;
		this.textModel = textModel;
	}
	
	/**
	 * 
	 */
	public ValueModel() {
		
	}
	
}
