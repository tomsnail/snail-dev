package cn.tomsnail.pdf;



import com.itextpdf.text.pdf.AcroFields.FieldPosition;

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
		
		testCompose3();
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
		ComposeModel composeModel = new ComposeModel("E:/02_workspace/35_npcmis/npc-dc/npc-dc-basic/npc-dc-basic-webapi/a5ae45fcb3fe805f8396469392cd55ae.pdf", "E:/02_workspace/35_npcmis/npc-dc/npc-dc-basic/npc-dc-basic-webapi/1.pdf");
		TextModel tm = new TextModel(10);
		composeModel.addValue("buy_ei_name", new ValueModel("北京京东世纪信息技术有限公司",new TextModel()));
		composeModel.addValue("buy_address", new ValueModel("北京市北京经济技术开发区科创十四街99号2号楼B168室",new TextModel()));
		composeModel.addValue("mark", new ValueModel("BJN157",new TextModel()));
		composeModel.setEdited(false);
		return new DefaultPdfComposeService().compose(composeModel);
	}
	
	public static void testSign3(FieldPosition fieldPosition){
		PdfSignService pdfServiceImpl = new DefaultPdfSignService();
		SignModel signModel = new SignModel();
		signModel.setTarget("C:/Users/yangsong/Desktop/pdf/入网协议161101PDF_SIGN.pdf");
		signModel.setSrc("C:/Users/yangsong/Desktop/pdf/入网协议161101PDF_DEMO.pdf");
		signModel.setFieldPosition(fieldPosition);
		signModel.setKeyPass("123456zkrpf");
		signModel.setKeyStorePass("123456zkrpf");
		signModel.setImageurl("G:/QQFiles/files/hetong.png");
		signModel.setPage(2);
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
		ComposeModel composeModel = new ComposeModel("C:/Users/yangsong/Desktop/fptd.pdf", "C:/Users/yangsong/Desktop/FAPI_DEMO.pdf");
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
		ComposeModel composeModel = new ComposeModel("C:/Users/yangsong/Desktop/demo1.pdf", "C:/Users/yangsong/Desktop/demo1test.pdf");
		TextModel tm = new TextModel(null,null,8);
		composeModel.addValue("fill_3", new ValueModel("北京市朝阳区红军营南路15号瑞普大厦B座1102北京市朝阳区红军营南路15号瑞普大厦B座1102",tm));
		composeModel.setEdited(false);
		SignModel signModel = new SignModel();
		signModel.setFieldName("sign2");
		
		new DefaultPdfComposeService().compose(composeModel);
	}
	
}
