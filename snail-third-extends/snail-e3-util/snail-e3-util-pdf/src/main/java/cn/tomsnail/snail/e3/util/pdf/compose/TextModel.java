package cn.tomsnail.snail.e3.util.pdf.compose;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;

/**
 *        字段属性
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 下午3:02:46
 * @see 
 */
public class TextModel {
	
	/**
	 * 默认字段属性
	 */
	public static TextModel DEFAULT_TEXT_MODEL = new TextModel();
	
	public static TextModel NULL_TEXT_MODEL = new TextModel(0f);
	
	/**
	 * 字体，默认为宋体
	 */
	private BaseFont bf = null;
	/**
	 * 字体颜色
	 */
	private BaseColor textcolor;
	/**
	 * 文字大小，默认10
	 */
	private float textsize = 10;
	/**
	 * 背景颜色
	 */
	private BaseColor bgcolor = null;
	/**
	 * 边框颜色
	 */
	private BaseColor bordercolor = null;
	
	/**
	 * 默认宋体
	 */
	public TextModel(){
		try {
			bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bf 字体，如果为空默认为宋体
	 */
	public TextModel(BaseFont bf) {
		if(bf!=null) {
			this.bf = bf;
		}else {
			 try {
					bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	

	/**
	 * @param textsize 大小
	 */
	public TextModel(float textsize) {
		this.textsize = textsize;
	}

	/**
	 * @param textcolor 文字颜色
	 */
	public TextModel(BaseColor textcolor) {
		this.textcolor = textcolor;
	}
	
	

	/**
	 * @param bf 字体，如果为空默认为宋体
	 * @param textcolor 文字颜色
	 * @param textsize 文字大小
	 */
	public TextModel(BaseFont bf, BaseColor textcolor, float textsize) {
		if(bf!=null) {
			this.bf = bf;
		}else {
			 try {
					bf = BaseFont.createFont();
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		this.textcolor = textcolor;
		if(textsize>0){
			this.textsize = textsize;
		}
	}

	public BaseFont getBf() {
		return bf;
	}

	public void setBf(BaseFont bf) {
		this.bf = bf;
	}

	public BaseColor getTextcolor() {
		return textcolor;
	}

	public void setTextcolor(BaseColor textcolor) {
		this.textcolor = textcolor;
	}

	public float getTextsize() {
		return textsize;
	}

	public void setTextsize(float textsize) {
		this.textsize = textsize;
	}

	public BaseColor getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(BaseColor bgcolor) {
		this.bgcolor = bgcolor;
	}

	public BaseColor getBordercolor() {
		return bordercolor;
	}

	public void setBordercolor(BaseColor bordercolor) {
		this.bordercolor = bordercolor;
	}
	
	public void setFieldProperty(AcroFields from,String name){
		if(from==null||name==null){
			return;
		}
		if(bf!=null) from.setFieldProperty(name, "textfont", bf, null);
		if(textsize!=0f) from.setFieldProperty(name, "textsize", textsize, null);
		if(bordercolor!=null) from.setFieldProperty(name, "bordercolor", bordercolor, null);
		if(textcolor!=null) from.setFieldProperty(name, "textcolor", textcolor, null);
		if(bgcolor!=null) from.setFieldProperty(name, "bgcolor", bgcolor, null);
	}


}
