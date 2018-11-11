package cn.tomsnail.http.register.override.extend;

import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.ExporterListenerAdapter;
import com.alibaba.fastjson.JSON;

public class ZookeeperExporterListener extends ExporterListenerAdapter{
	
	 @Override
	 public void exported(Exporter<?> exporter) throws RpcException {
		 System.out.println(JSON.toJSONString(exporter.getInvoker().getUrl()));
	 }

	 @Override
	 public void unexported(Exporter<?> exporter) throws RpcException {
	 }

}
