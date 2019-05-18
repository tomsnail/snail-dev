package cn.tomsnail.snail.ext.pilot.core.server;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.tomsnail.snail.core.util.configfile.ProgramPathHelper;
import cn.tomsnail.snail.core.util.zkclient.ZkHelper;
import cn.tomsnail.snail.core.util.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.snail.ext.pilot.core.model.Consts;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceKeeper;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceProcess;


public class ProxyServerKeeper implements IServer{
	
	private List<ServiceKeeper> keepers = new ArrayList<ServiceKeeper>();
	
	private String file;
	
	private static final ZkHelper zkHelper =  ZkHelper.getInstance(ZooKeeperAddress.ZK_ADDRESS);

	
	public List<ServiceProcess> getService(String node, String service) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		Document doc;
		SAXReader reader = new SAXReader();
		FileReader fr = null;
		try {
			String dir = System.getProperty("user.dir");
			if (!dir.endsWith("/")) {
				dir += "/";
			}
			if(this.file==null){
				this.file = ProgramPathHelper.getProgramPath(this.getClass())+File.separator+"ServiceKeepers.xml";
			}
			fr = new FileReader(this.file);
			doc = reader.read(fr);
			Element rootElement = doc.getRootElement();
			
			List<Element> processElementList = rootElement.elements();
			for (Element row : processElementList) {
				List<Element> elementList = row.elements();
				ServiceKeeper userProcess = new ServiceKeeper();
				for (Element e : elementList) {
					String MethodName = "set"
							+ e.getName().substring(0, 1).toUpperCase()
							+ e.getName().substring(1);
					Method method = userProcess.getClass().getMethod(
							MethodName, new Class[] { String.class });
					method.invoke(userProcess, e.getText());
				}
				keepers.add(userProcess);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(fr!=null){
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!zkHelper.isExtisPath(Consts.ZK_SERVICE_KEEPER)){
			zkHelper.createPathWithParent(Consts.ZK_SERVICE_KEEPER, true);
		}
		
	}

	
	public void start() {
		init();
		for(ServiceKeeper serviceKeeper:this.keepers){
			String path = Consts.ZK_SERVICE_KEEPER+"/"+serviceKeeper.getName();
			if(zkHelper.isExtisPath(path)){
				return;
			}else{
				zkHelper.createPathWithParent(path, true);
			}
			zkHelper.writeNode(path, serviceKeeper);
		}
	}

	
	public void stop() {
		
	}

	
	public void notifly(String path, int type) {
		
	}

	
	public void addCallback(ICallback callback) {
		
	}

}
