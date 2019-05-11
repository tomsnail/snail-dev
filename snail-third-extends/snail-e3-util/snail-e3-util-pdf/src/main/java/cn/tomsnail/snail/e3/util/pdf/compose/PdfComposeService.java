package cn.tomsnail.snail.e3.util.pdf.compose;

import java.util.Map;

import com.itextpdf.text.pdf.AcroFields.FieldPosition;

import cn.tomsnail.snail.e3.util.pdf.sign.SignModel;

/**
 *        PDF组合服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 下午12:41:40
 * @see 
 */
public interface PdfComposeService {
	
	/**
	 *         通过模板pdf生成目标pdf,指定字段替换
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 下午2:50:56
	 * @see 
	 * @param        template     模板pdf路径
	 * @param        target          目录pdf路径 
	 * @param        fieldValue           map集合：字段，要替换的值
	 * @param        isEdited           是否可编辑             
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean compose(String template,String target,Map<String,String> fieldValue,boolean isEdited);
	
	/**
	 *       通过模板pdf生成目标pdf,顺序替换字段
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 下午2:50:59
	 * @see 
	 * @param        template     模板pdf路径
	 * @param        target          目录pdf路径 
	 * @param        values           替换值的集合
	 * @param        isEdited           是否可编辑
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean compose(String template,String target,String[] values,boolean isEdited);
	
	/**
	 *        通过组合模型和模板pdf生成目标pdf
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 下午2:51:02
	 * @see 
	 * @param           composeModel  组合模型        
	 * @return         如果有签名位置，则返回签名位置的信息      
	 * @status 正常
	 * @exception no
	 */
	public FieldPosition compose(ComposeModel composeModel);
	
	
	public void pdf2Image(String src,String distFile) ;
	

}
