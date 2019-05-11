package cn.tomsnail.snail.e3.util.pdf.compose;

import java.util.HashMap;
import java.util.Map;

/**
 *        组合模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 下午2:30:45
 * @see 
 */
public class ComposeModel {
	
	
	public static final String QRCODE_CONTEXT = "QRCODE_CONTEXT";
	
	public static final String QRCODE_WITDH = "QRCODE_WITDH";
	
	public static final String QRCODE_HEIGHT = "QRCODE_HEIGHT";
	
	public static final String QRCODE_X = "QRCODE_X";

	public static final String QRCODE_Y = "QRCODE_Y";

	
	/**
	 * 模板pdf路径
	 */
	private String template;
	/**
	 * 模板pdf路径
	 */
	private String target;
	/**
	 * 值
	 */
	private Map<String,ValueModel> fieldValue;
	/**
	 * 是否可编辑
	 */
	private boolean isEdited = true;
	/**
	 * 适用于该组合需求所有字段的字段属性，如果字段不单独设置，将默认使用，如果没有设置该值，使用默认的DEFAULT_TEXT_MODEL
	 */
	private TextModel textModel;
	
	/**
	 * 导出页数
	 */
	private int pageNumber = 1;
	
	/**
	 * 二维码内容，如果为null则不生成二维码
	 */
	private String codeContent = null;
	/**
	 * 二维码宽度
	 */
	private float ccWitdh = 72;
	/**
	 * 二维码高度
	 */
	private float ccHeight = 72;
	/**
	 * 二维码X位置
	 */
	private float ccX = 72;
	/**
	 * 二维码Y位置
	 */
	private float ccY = 720;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Map<String, ValueModel> getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Map<String, ValueModel> fieldValue) {
		this.fieldValue = fieldValue;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	public TextModel getTextModel() {
		return textModel;
	}

	public void setTextModel(TextModel textModel) {
		this.textModel = textModel;
	}
	
	
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	

	public String getCodeContent() {
		return codeContent;
	}

	public void setCodeContent(String codeContent) {
		this.codeContent = codeContent;
	}

	public float getCcWitdh() {
		return ccWitdh;
	}

	public void setCcWitdh(float ccWitdh) {
		this.ccWitdh = ccWitdh;
	}

	public float getCcHeight() {
		return ccHeight;
	}

	public void setCcHeight(float ccHeight) {
		this.ccHeight = ccHeight;
	}

	public float getCcX() {
		return ccX;
	}

	public void setCcX(float ccX) {
		this.ccX = ccX;
	}

	public float getCcY() {
		return ccY;
	}

	public void setCcY(float ccY) {
		this.ccY = ccY;
	}

	/**
	 *        添加字段值
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 下午3:07:36
	 * @see 
	 * @param      name  字段名
	 * @param        valueModel       值模型
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void addValue(String name,ValueModel valueModel){
		if(this.fieldValue!=null){
			fieldValue.put(name, valueModel);
		}
	}
	
	/**
	 * @param template 模板pdf路径
	 * @param target 目标pdf路径
	 * @param fieldValue 值
	 * @param isEdited 是否编辑
	 */
	public ComposeModel(String template, String target,
			Map<String, ValueModel> fieldValue, boolean isEdited) {
		this.template = template;
		this.target = target;
		this.fieldValue = fieldValue;
		this.isEdited = isEdited;
	}
	
	/**
	 * @param template 模板pdf路径
	 * @param target 目标pdf路径
	 * @param isEdited 是否编辑
	 */
	public ComposeModel(String template, String target,boolean isEdited) {
		this.template = template;
		this.target = target;
		this.fieldValue = new HashMap<String, ValueModel>();
		this.isEdited = isEdited;
	}
	
	/**
	 * @param template 模板pdf路径
	 * @param target 目标pdf路径
	 * 	@param isEdited 默认为true
	 */
	public ComposeModel(String template, String target) {
		this.template = template;
		this.target = target;
		this.fieldValue = new HashMap<String, ValueModel>();
	}
	
	/**
	 * 
	 */
	public ComposeModel() {
		
	}
	
}
