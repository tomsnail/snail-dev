package cn.tomsnail.snail.e3.util.pdf.compose;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;

/**
 * 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 下午12:43:26
 * @see
 */
public class DefaultPdfComposeService implements PdfComposeService {

	@Override
	public boolean compose(String template, String target,
			Map<String, String> fieldValue, boolean isEdited) {

		if (fieldValue == null || fieldValue.size() == 0) {
			return false;
		}

		String templatePath = template;
		// 生成的新文件路径
		String newPDFPath = target;
		PdfReader reader = null;
		FileOutputStream out = null;
		ByteArrayOutputStream bos = null;
		PdfStamper stamper = null;
		try {
			out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			BaseFont bf = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			java.util.Iterator<String> it = fieldValue.keySet().iterator();
			while (it.hasNext()) {
				String name = it.next().toString();
				String value = fieldValue.get(name);
				form.setFieldProperty(name, "textfont", bf, null);
				if (value == null) {
					form.setFieldRichValue(name, "");
				} else {
					form.setFieldRichValue(name, value);
				}
			}
			stamper.setFormFlattening(isEdited);
			stamper.close();

			createTargetPDF(out, bos,reader);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				reader.close();
			}
		}
		return false;
	}

	@Override
	public boolean compose(String template, String target, String[] values,
			boolean isEdited) {

		String templatePath = template;
		// 生成的新文件路径
		String newPDFPath = target;
		PdfReader reader = null;
		FileOutputStream out = null;
		ByteArrayOutputStream bos = null;
		PdfStamper stamper = null;
		try {
			out = new FileOutputStream(newPDFPath);// 输出流
			reader = new PdfReader(templatePath);// 读取pdf模板
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = stamper.getAcroFields();
			BaseFont bf = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			int i = 0;
			java.util.Iterator<String> it = form.getFields().keySet()
					.iterator();
			while (it.hasNext()) {
				String name = it.next().toString();
				form.setFieldProperty(name, "textfont", bf, null);
				if (i > values.length) {
					form.setField(name, "");
				} else {
					String v = values[i++];
					if (v == null) {
						form.setFieldRichValue(name, "");
					} else {
						form.setFieldRichValue(name, v);
					}
				}
			}
			stamper.setFormFlattening(isEdited);
			stamper.close();

			createTargetPDF(out, bos,reader);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				reader.close();
			}
		}
		return false;
	}

	@Override
	public FieldPosition compose(ComposeModel composeModel) {
		if (composeModel == null || composeModel.getFieldValue() == null
				|| composeModel.getTarget() == null
				|| composeModel.getTemplate() == null) {
			return null;
		}

		String templatePath = composeModel.getTemplate();
		String newPDFPath = composeModel.getTarget();
		PdfReader reader = null;
		FileOutputStream out = null;
		ByteArrayOutputStream bos = null;
		PdfStamper stamper = null;
		try {
			out = new FileOutputStream(newPDFPath);
			reader = new PdfReader(templatePath);
			bos = new ByteArrayOutputStream();
			stamper = new PdfStamper(reader, bos);
			AcroFields form = createPdfContent(composeModel, stamper);
			FieldPosition fieldPosition = createSign(form);
			createCode(composeModel, stamper);
			stamper.setFormFlattening(!composeModel.isEdited());
			stamper.close();
			createTargetPDF(out, bos,reader);
			return fieldPosition;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				reader.close();
			}
		}
		return null;
	}

	/**
	 *        返回签名位置，如果没有，返回一个空位置
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月21日 下午3:14:14
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	protected FieldPosition createSign(AcroFields form) {
		FieldPosition fieldPosition = null;
		if( form.getFieldPositions("SignPoint")!=null){
			fieldPosition = form.getFieldPositions("SignPoint").get(0);
		}
		if(fieldPosition==null){
			fieldPosition = new FieldPosition();
		}
		return fieldPosition;
	}

	/**
	 *        生成pdf内容
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月21日 下午3:13:24
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	protected AcroFields createPdfContent(ComposeModel composeModel,
			PdfStamper stamper) throws IOException, DocumentException {
		AcroFields form = stamper.getAcroFields();
		java.util.Iterator<String> it = composeModel.getFieldValue()
				.keySet().iterator();
		while (it.hasNext()) {
			String name = it.next().toString();
			String value = composeModel.getFieldValue().get(name)
					.getValue();
			TextModel tm = composeModel.getFieldValue().get(name)
					.getTextModel() == null ? composeModel.getTextModel()
					: composeModel.getFieldValue().get(name).getTextModel();
			if (tm == null) {
				TextModel.DEFAULT_TEXT_MODEL.setFieldProperty(form, name);
			} else {
				tm.setFieldProperty(form, name);
			}
			if (value == null) {
				form.setField(name, "");
			} else {
				form.setField(name, value);
			}
			
		}
		return form;
	}

	/**
	 *        生成目标文档
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月21日 下午3:12:20
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	protected void createTargetPDF(FileOutputStream out,
			ByteArrayOutputStream bos,PdfReader reader) throws DocumentException, IOException,
			BadPdfFormatException {
		Document doc = new Document();
		PdfCopy copy = new PdfCopy(doc, out);
		doc.open();
		for(int i=1;i<=reader.getNumberOfPages();i++){
			PdfImportedPage importPage = copy
					.getImportedPage(new PdfReader(bos.toByteArray()),
							i);
			copy.addPage(importPage);
		}
	
		doc.close();
	}

	/**
	 *        生成二维码
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月21日 下午3:11:28
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	protected void createCode(ComposeModel composeModel, PdfStamper stamper)
			throws BadElementException, DocumentException {
		if(!StringUtils.isBlank(composeModel.getCodeContent())){
			PdfContentByte over = stamper.getOverContent(1);
			Map<EncodeHintType,Object> hints=new HashMap<EncodeHintType,Object>();  
	        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);   
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");   
			BarcodeQRCode qrcode = new BarcodeQRCode(composeModel.getCodeContent(), 10, 10, hints);
		    Image img = qrcode.getImage();
		    img.setAlignment(1);
		    img.scaleAbsolute(composeModel.getCcWitdh(), composeModel.getCcHeight());
		    img.setAbsolutePosition(composeModel.getCcX(), composeModel.getCcY());
		    over.addImage(img);
		}
	}

	@Override
	public void pdf2Image(String src,String distFile) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(src);
        PDDocument doc = null;
        try {
        	doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            BufferedImage image = renderer.renderImageWithDPI(0, 300); // Windows native DPI
            ImageIO.write(image, "jpg", new File(distFile));
            
        } catch (IOException e) {
           e.printStackTrace();
        }finally {
			if(doc!=null) {
				try {
					doc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
	
	

	
}
