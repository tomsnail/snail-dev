package cn.tomsnail.pdf;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfSignatureAppearance.RenderingMode;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import cn.tomsnail.pdf.compose.ComposeModel;
import cn.tomsnail.pdf.compose.DefaultPdfComposeService;
import cn.tomsnail.pdf.compose.TextModel;
import cn.tomsnail.pdf.compose.ValueModel;
import cn.tomsnail.pdf.sign.DefaultPdfSignService;
import cn.tomsnail.pdf.sign.PdfSignService;
import cn.tomsnail.pdf.sign.SignModel;

public class PdfTest {

	public static void main(String[] args) {

		
		//testSign(testCompose());
		//testSign3(testCompose3());
		testComposeAndSign();
		//testCompose3();
	}
	
	
	/**
	 *        要在设置模板的时候允许字段多行显示
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 下午3:54:12
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static FieldPosition testCompose3(){
		ComposeModel composeModel = new ComposeModel("C:/Users/yangsong/Desktop/1.pdf", "C:/Users/yangsong/Desktop/11.pdf");
		TextModel tm = new TextModel(10);
		composeModel.addValue("TR_NO", new ValueModel("北京市朝阳区红军营南路15号瑞普大厦B座1102北京市朝阳区红军营南路15号瑞普大厦B座1102",tm));
		composeModel.setEdited(false);
		return new DefaultPdfComposeService().compose(composeModel);
	}
	
	public static void testSign3(FieldPosition fieldPosition){
		PdfSignService pdfServiceImpl = new DefaultPdfSignService();
		SignModel signModel = new SignModel();
		signModel.setTarget("C:/Users/yangsong/Desktop/111.pdf");
		signModel.setSrc("C:/Users/yangsong/Desktop/11.pdf");
		signModel.setFieldPosition(fieldPosition);
		signModel.setKeyPass("123456zkrpf");
		signModel.setKeyStorePass("123456zkrpf");
		signModel.setImageurl("G:/QQFiles/files/hetong.png");
		signModel.setPage(1);
		//signModel.setFieldName("STAMP");
		pdfServiceImpl.signDataImage(signModel);
	}
	
	/**
	 *        要在设置模板的时候允许字段多行显示
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 下午3:54:12
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static FieldPosition testCompose(){
		ComposeModel composeModel = new ComposeModel("C:/Users/yangsong/Desktop/1.pdf", "C:/Users/yangsong/Desktop/11.pdf");
		TextModel tm = new TextModel(10);
		composeModel.addValue("fpdm", new ValueModel("011001605111",tm));
		composeModel.addValue("fphm", new ValueModel("52743717",tm));
		composeModel.addValue("n", new ValueModel("2016",tm));
		composeModel.addValue("y", new ValueModel("10",tm));
		composeModel.addValue("r", new ValueModel("21",tm));
		composeModel.addValue("jqbm", new ValueModel("661545422818",tm));
		composeModel.addValue("jym", new ValueModel("70313 07938 13055 71850",new TextModel(9)));
		composeModel.addValue("gmc", new ValueModel("北京京东世纪信息技术有限公司",TextModel.NULL_TEXT_MODEL));
		composeModel.addValue("gnsrsbh", new ValueModel("  110192562134916",tm));
		composeModel.addValue("gdzdh", new ValueModel("北京市北京经济技术开发区科创十四街99号2号楼B168室 010-56754036",TextModel.NULL_TEXT_MODEL));
		composeModel.addValue("gkhhjzh", new ValueModel("交通银行股份有限公司北京海淀支行 110060576018150059588",TextModel.NULL_TEXT_MODEL));
		composeModel.addValue("mmq", new ValueModel("0-944931/2/-*->+420+49-3801\n>6-*>5351+-*/60+/007182>75-\n+<797-3+0870/2/>42++5+4879+\n3*97>94*>5351+-*/60+/0018-6",TextModel.NULL_TEXT_MODEL));
		composeModel.addValue("sp", new ValueModel("技术服务费",new TextModel(9)));
		composeModel.addValue("ggxh", new ValueModel("",tm));
		composeModel.addValue("dw", new ValueModel("",tm));
		composeModel.addValue("sl", new ValueModel("1",tm));
		composeModel.addValue("dj", new ValueModel("2477.78",new TextModel(10)));
		composeModel.addValue("je", new ValueModel("2477.78",new TextModel(10)));
		composeModel.addValue("suol", new ValueModel("17%",new TextModel(10)));
		composeModel.addValue("se", new ValueModel("421.22",new TextModel(10)));
		composeModel.addValue("jehj", new ValueModel("¥2477.78",new TextModel(10)));
		composeModel.addValue("slhj", new ValueModel("¥421.22",new TextModel(10)));
		composeModel.addValue("dx", new ValueModel("贰仟捌佰玖拾玖圆整",new TextModel(null,null,10)));
		composeModel.addValue("xx", new ValueModel("2899.00",new TextModel(null,null,10)));
		composeModel.addValue("xmc", new ValueModel("北京中科京盾科技有限公司",TextModel.NULL_TEXT_MODEL));
		composeModel.addValue("xnsrsbh", new ValueModel("  91110106573183174K",tm));
		composeModel.addValue("xdzdh", new ValueModel("北京市丰台区中关村高科技园区丰台园航丰路13号崇新大厦B座7层（园区） 010-84365245",TextModel.NULL_TEXT_MODEL));
		composeModel.addValue("xkhhjzh", new ValueModel("中国民生银行北京总部基地支行 0150014170000844",TextModel.NULL_TEXT_MODEL));
		composeModel.addValue("bz", new ValueModel("订单编号：YZD20160921028888 到款日期：20160913 \n付款人：张家港保税区荣源国际贸易有限公司",new TextModel(9)));
		composeModel.addValue("skr", new ValueModel("刘建颖",new TextModel(8)));
		composeModel.addValue("fhr", new ValueModel("乐粤红",new TextModel(8)));
		composeModel.addValue("kpr", new ValueModel("乐粤红",new TextModel(8)));
		composeModel.setEdited(false);
		composeModel.setCcHeight(60);
		composeModel.setCcWitdh(60);
		composeModel.setCcX(34);
		composeModel.setCcY(780);
		composeModel.setCodeContent("01,10,011001600111,30391244,100.47,20160806,45171463883559055316,9B53");
		return new DefaultPdfComposeService().compose(composeModel);
	}
	
	public static void testSign(FieldPosition fieldPosition){
		PdfSignService pdfServiceImpl = new DefaultPdfSignService();
		SignModel signModel = new SignModel();
		signModel.setTarget("C:/Users/yangsong/Desktop/FAPI_DEMO_SIGN.pdf");
		signModel.setSrc("C:/Users/yangsong/Desktop/FAPI_DEMO.pdf");
		signModel.setFieldPosition(fieldPosition);
		signModel.setKeyPass("123456zkrpf");
		signModel.setKeyStorePass("123456zkrpf");
		signModel.setImageurl("G:/QQFiles/files/zhang.png");
		pdfServiceImpl.signDataImage(signModel);
	}
	
	public static void testComposeAndSign(){
		ComposeModel composeModel = new ComposeModel("C:/Users/yangsong/Desktop/1.pdf", "C:/Users/yangsong/Desktop/111.pdf");
		TextModel tm = new TextModel(null,null,8);
		composeModel.addValue("LICENSE_NO", new ValueModel("1232",tm));
		composeModel.setEdited(false);
		
		
		FieldPosition fieldPosition = new DefaultPdfComposeService().compose(composeModel);
		
		
		SignModel signModel = new SignModel();
		signModel.setX(180);
		signModel.setY(480);
		signModel.setWidth(120);
		signModel.setHeight(120);
		
		
		signModel.setTarget("C:/Users/yangsong/Desktop/1111.pdf");
		signModel.setSrc("C:/Users/yangsong/Desktop/111.pdf");
		signModel.setFieldPosition(fieldPosition);
		signModel.setKeyPass("123456");
		signModel.setKeyStorePass("123456");
		signModel.setPage(1);
		
		File file = new File("G:/QQFiles/files/1.png");
		signDataImage(signModel,file);
		
		
		
	}
	
	
	public static String signDataImage(SignModel signModel,File file) {
		
		if(signModel==null||!signModel.isVliad()){
			return null;
		}
		
		if(file==null||!file.exists()){
			return null;
		}
		
		PdfReader reader = null;
		FileOutputStream os = null;
		FileInputStream is = null;
		try {
			String alias ="zkrpf";
			String dest = signModel.getTarget() ;
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			is = new FileInputStream("C:/Users/yangsong/tomcat.keystore");
			ks.load(is, signModel.getKeyStorePass().toCharArray());
			if (alias == null || alias.length() == 0)
				alias = (String) ks.aliases().nextElement();
			Certificate[] chain = ks.getCertificateChain(alias);
			PrivateKey key = (PrivateKey) ks.getKey(alias,
					signModel.getKeyPass().toCharArray());

			X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
			Signature s = Signature.getInstance("SHA1withRSA");
			s.initVerify(ks.getCertificate(alias));
			try {
				cert.checkValidity();
			} catch (Exception e) {
				return null;
			}
			reader = new PdfReader(signModel.getSrc());
			os = new FileOutputStream(dest);
			PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
			addImage(stamper);
//			stamper.setEncryption(true, null, null,
//							  PdfWriter.ALLOW_PRINTING
//							| PdfWriter.ALLOW_COPY);
			HashMap<String, String> info = reader.getInfo();
			info.put("Creator","1" );
			stamper.setMoreInfo(info);
			PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
			appearance.setReason("2");
			appearance.setLocation("3");
			appearance.setContact("4");
			appearance.setCrypto(key, chain, null,PdfSignatureAppearance.SELF_SIGNED);
			Image image = Image.getInstance(IOUtils.toByteArray(new FileInputStream(file)));
			//appearance.setImage(image);
			appearance.setSignatureGraphic(image);
			appearance.setAcro6Layers(true);
			appearance.setRenderingMode(RenderingMode.GRAPHIC);//APSHandler PPKLiteHandler
			if(StringUtils.isBlank(signModel.getFieldName())){
				appearance.setVisibleSignature(new Rectangle(signModel.getX(), signModel.getY(), signModel.getX()+signModel.getWidth(), signModel.getY()+signModel.getHeight()),signModel.getPage(), "ImageZkr"); // 300和600 是对应x轴和y轴坐标
			}else{
				appearance.setVisibleSignature(signModel.getFieldName());
			}
			stamper.close();
			
			File srcdf = new File(signModel.getTarget()); 
		    File mydest = new File(dest); 
		    if(mydest.renameTo(srcdf)){
		    	
		    }
		    return dest;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(os!=null)
					os.close();
				if(reader!=null)
					reader.close();
				if(is!=null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected static void addImage(PdfStamper stamper)
			throws BadElementException, DocumentException {
		try {
			Image image = Image.getInstance("E:/02_workspace/35_npcmis/npc-dc/npc-dc-basic/npc-dc-basic-webapi/stamp/cec98faa4f4c679619f6965cf87f80b9.png");
			 // 获取操作的页面
	        PdfContentByte under = stamper.getOverContent(1);
	        // 根据域的大小缩放图片
	        image.scaleToFit(100, 100);
	       
	        // 添加图片
	        image.setAbsolutePosition(180, 480);
	        under.addImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
