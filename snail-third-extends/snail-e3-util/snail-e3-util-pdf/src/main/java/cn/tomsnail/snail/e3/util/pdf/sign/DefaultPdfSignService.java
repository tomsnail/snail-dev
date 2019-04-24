package cn.tomsnail.snail.e3.util.pdf.sign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPKCS7;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignature;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfSignatureAppearance.RenderingMode;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 上午10:59:52
 * @see 
 */
public class DefaultPdfSignService implements PdfSignService{

	/**
	 * 证书别名
	 */
	private String alias = "zkr";

	/**
	 * 证书路径
	 */
	private  String path = "d:/zkrpf.keystore";

	/**
	 * 证书作用
	 */
	private  String Reason = "数字签名，不可否认，客户端认证，电子邮件保护";

	/**
	 * 
	 */
	private  String Location = "";

	/**
	 * 联系方式
	 */
	private  String Contact = "www.zkr.com";
	
	/**
	 * 创建者
	 */
	private  String Creator = "ZKR PDF V0.1 ";


	@Override
	public String signDataImage(SignModel signModel) {
		
		if(signModel==null||!signModel.isVliad()){
			return null;
		}
		PdfReader reader = null;
		FileOutputStream os = null;
		try {
			if (signModel.getImageurl() == null) {
				signModel.setImageurl("http://192.168.169.156/gongan.jpg");
			}
			String dest = signModel.getTarget() ;
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(new FileInputStream(path), signModel.getKeyStorePass().toCharArray());
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
			stamper.setEncryption(true, null, null,
					PdfWriter.ALLOW_MODIFY_CONTENTS
							| PdfWriter.ALLOW_MODIFY_ANNOTATIONS
							| PdfWriter.ALLOW_PRINTING
							| PdfWriter.ALLOW_SCREENREADERS
							| PdfWriter.ALLOW_COPY);
			HashMap<String, String> info = reader.getInfo();
			info.put("Creator",Creator );
			stamper.setMoreInfo(info);
			PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
			appearance.setReason(Reason);
			appearance.setLocation(Location);
			appearance.setContact(Contact);
			appearance.setCrypto(key, chain, null,PdfSignatureAppearance.SELF_SIGNED);
			Image image = Image.getInstance(signModel.getImageurl()); // 使用png格式透明图片
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
		    mydest.renameTo(srcdf);
		    return dest;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(os!=null)
					os.close();
				if(reader!=null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String signData(SignModel signModel) {
		
		if(signModel==null||!signModel.isVliad()){
			return null;
		}
		PdfReader reader = null;
		FileOutputStream os = null;
		try {
			addImage(signModel.getSrc(),signModel.getTarget());
			String dest = signModel.getTarget();
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(new FileInputStream(path), signModel.getKeyStorePass().toCharArray());
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
			reader = new PdfReader(signModel.getTarget()+".temp");
			os = new FileOutputStream(dest);
			PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
			stamper.setEncryption(true, null, null,
					PdfWriter.ALLOW_MODIFY_CONTENTS
							| PdfWriter.ALLOW_MODIFY_ANNOTATIONS
							| PdfWriter.ALLOW_PRINTING
							| PdfWriter.ALLOW_SCREENREADERS
							| PdfWriter.ALLOW_COPY);
			HashMap<String, String> info = reader.getInfo();
			info.put("Creator",Creator );
			stamper.setMoreInfo(info);
			PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
			appearance.setReason(Reason);
			appearance.setLocation(Location);
			appearance.setContact(Contact);
			appearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
			// / ts + ocsp
			PdfSignature dic = new PdfSignature(PdfName.ADOBE_PPKLITE,new PdfName("adbe.pkcs7.detached"));
			dic.setReason(appearance.getReason());
			dic.setLocation(appearance.getLocation());
			dic.setContact(appearance.getContact());
			dic.setDate(new PdfDate(appearance.getSignDate()));
			appearance.setCryptoDictionary(dic);
			byte[] ocsp = null;
			int contentEstimated = 15000;
			HashMap<PdfName, Integer> exc = new HashMap<PdfName, Integer>();
			exc.put(PdfName.CONTENTS, new Integer(contentEstimated * 2 + 2));
			appearance.preClose(exc);
			InputStream data = appearance.getRangeStream();
			MessageDigest mdig = MessageDigest.getInstance("SHA1");
			byte buf[] = new byte[8192];
			int n;
			while ((n = data.read(buf)) > 0) {
				mdig.update(buf, 0, n);
			}
			byte hash[] = mdig.digest();
			Calendar cal = Calendar.getInstance();
			PdfPKCS7 sgn = new PdfPKCS7(key, chain, null, "SHA1", null,
					false);
			byte sh[] = sgn.getAuthenticatedAttributeBytes(hash, cal, ocsp);
			sgn.update(sh, 0, sh.length);
			byte[] encodedSig = sgn.getEncodedPKCS7(hash, cal);
			if (contentEstimated + 2 < encodedSig.length)
				throw new Exception("Not enough space");
			byte[] paddedSig = new byte[contentEstimated];
			System.arraycopy(encodedSig, 0, paddedSig, 0,
					encodedSig.length);
			PdfDictionary dic2 = new PdfDictionary();
			dic2.put(PdfName.CONTENTS,
					new PdfString(paddedSig).setHexWriting(true));
			appearance.close(dic2);
			File srcdf = new File(signModel.getTarget()+".temp");
			srcdf.deleteOnExit();
			 return dest;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(os!=null)
					os.close();
				if(reader!=null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	private void addImage(String src, String target){
		try {
			String dest = target+".temp";
			PdfReader reader = new PdfReader(src);
			FileOutputStream os = new FileOutputStream(dest);
			PdfStamper stamper = new PdfStamper(reader, os);
//			int n1 = reader.getNumberOfPages(); 
//			Image img = Image.getInstance("http://192.168.169.156/gongan.jpg"); 
//			img.setAbsolutePosition(300, 300); 
//			PdfContentByte over = stamper.getOverContent(n1); 
//			over.addImage(img); 
//			stamper.addJavaScript(" app.alert(security.getHandler(security.PPKLiteHandler));\r");
			stamper.close();
			os.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isVliad(){
		return true;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getCreator() {
		return Creator;
	}

	public void setCreator(String creator) {
		Creator = creator;
	}
	
}
