package cn.tomsnail.snail.e3.util.pdf.sign;

import org.apache.commons.lang.StringUtils;

import com.itextpdf.text.pdf.AcroFields.FieldPosition;

/**
 *        签名模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 上午11:00:48
 * @see 
 */
public class SignModel {
	
	/**
	 * 图片签名
	 */
	public static final String SIGN_IMAGE = "SIGN_IMAGE";
	
	/**
	 * 数据签名
	 */
	public static final String SIGN_DATA = "SIGN_DATA";
	
	/**
	 * 默认证书密码
	 */
	private static final String DEFAULT_PASS = "123456";
	
	/**
	 * 要签名的pdf文件路径
	 */
	private String src;
	/**
	 * 签名后的文件名称
	 */
	private String target;
	/**
	 * 证书存储文件的密码
	 */
	private String keyStorePass = DEFAULT_PASS;
	/**
	 * 证书对应的信息密码
	 */
	private String keyPass = DEFAULT_PASS;
	/**
	 * 图片签名x位置
	 */
	private float x = 300;
	/**
	 * 图片签名y位置
	 */
	private float y = 300;
	
	/**
	 * 图片签名宽度
	 */
	private float width = 100;
	/**
	 * 图片签名高度
	 */
	private float height = 100;
	/**
	 * 图片的url
	 */
	private String imageurl = "http://192.168.169.156/gongan.jpg";
	
	/**
	 * 签名类型：SIGN_IMAGE和SIGN_DATA
	 */
	private String type = SIGN_IMAGE;
	
	/**
	 * 签名替换字段
	 */
	private String fieldName = null;
	
	/**
	 * 在第几页签名
	 */
	private int page = 1;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getKeyStorePass() {
		return keyStorePass;
	}

	public void setKeyStorePass(String keyStorePass) {
		this.keyStorePass = keyStorePass;
	}

	public String getKeyPass() {
		return keyPass;
	}

	public void setKeyPass(String keyPass) {
		this.keyPass = keyPass;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public static String getSignImage() {
		return SIGN_IMAGE;
	}

	public static String getSignData() {
		return SIGN_DATA;
	}
	
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isVliad(){
		
		if(StringUtils.isBlank(this.src)){
			return false;
		}
		if(StringUtils.isBlank(this.target)){
			return false;
		}
		if(StringUtils.isBlank(this.type)){
			return false;
		}
		
		return true;
	}
	
	public void setFieldPosition(FieldPosition fieldPosition){
		if(fieldPosition==null||fieldPosition.position==null) return;
		this.x = fieldPosition.position.getLeft();
		this.y = fieldPosition.position.getBottom();
		this.width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
		this.height = fieldPosition.position.getTop() - fieldPosition.position.getBottom();
	}
	
}
