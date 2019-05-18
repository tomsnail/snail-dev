package cn.tomsnail.snail.ext.pilot.core.server.source;

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
import cn.tomsnail.snail.ext.pilot.core.model.ProxyInfo;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceProcess;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月7日 上午11:30:45
 * @see 
 */
public class XmlSource extends FileSource{
	
	private Element rootElement;

	public XmlSource() {
		super("XML");
	}

	@Override
	public void initFile() {
		Document doc;
		SAXReader reader = new SAXReader();
		FileReader fr = null;
		try {
			String dir = System.getProperty("user.dir");
			if (!dir.endsWith("/")) {
				dir += "/";
			}
			System.out.println(ProgramPathHelper.getProgramPath(this.getClass()));
			if(this.file==null){
				this.file = ProgramPathHelper.getProgramPath(this.getClass())+File.separator+"ServiceProcess.xml";
			}
			fr = new FileReader(this.file);
			doc = reader.read(fr);
			rootElement = doc.getRootElement();
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
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initMap() {
		try {
			initFile();
			List<Element> processElementList = rootElement.elements();
			for (Element row : processElementList) {
				List<Element> elementList = row.elements();
				ServiceProcess userProcess = new ServiceProcess();
				for (Element e : elementList) {
					String MethodName = "set"
							+ e.getName().substring(0, 1).toUpperCase()
							+ e.getName().substring(1);
					Method method = userProcess.getClass().getMethod(
							MethodName, new Class[] { String.class });
					method.invoke(userProcess, e.getText());
				}
				if(this.map.containsKey(userProcess.getNode())){
					this.map.get(userProcess.getNode()).getCommandInfo().add(userProcess);
				}else{
					List<ServiceProcess> processes = new ArrayList<ServiceProcess>();
					processes.add(userProcess);
					ProxyInfo proxyInfo = new ProxyInfo();
					proxyInfo.setCommandInfo(processes);
					this.map.put(userProcess.getNode(), proxyInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
