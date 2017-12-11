package cn.tomsnail.http.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.http.client.cluster.IClusterService;
import cn.tomsnail.http.client.filter.BasicChainFilter;
import cn.tomsnail.http.client.filter.IFilter;
import cn.tomsnail.http.client.invoker.BasicInvoker;
import cn.tomsnail.http.client.invoker.IInvoker;
import cn.tomsnail.http.client.mapper.IParseMapper;
import cn.tomsnail.http.client.request.IRequest;
import cn.tomsnail.framwork.spi.DefaultSpiCoreContextHolder;
import cn.tomsnail.framwork.spi.SpiCoreContextHolder;

/**
 *        默认客户端服务工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 下午4:34:19
 * @see 
 */
public class DefaultServiceFactory implements ServiceFactory{
	
	/**
	 * 客户端服务集合
	 */
	private static final Map<String,IService> SERVICE_MAP = new HashMap<String, IService>();
	
	/**
	 * 集群服务集合
	 */
	private static final Map<String,IClusterService> ADDRESS_MAP = new HashMap<String, IClusterService>();
	
	/**
	 * 过滤器集合
	 */
	protected List<IFilter> filters;
	
	/**
	 * 执行器集合
	 */
	protected List<IInvoker> invokers;
	
	/**
	 * 请求
	 */
	protected IRequest request = null;
	
	/**
	 * 转换
	 */
	protected IParseMapper parseMapper;
	
	private static final SpiCoreContextHolder spiCoreContextHolder = new DefaultSpiCoreContextHolder();

	
	public DefaultServiceFactory(){
		init();
	}
	
	protected void init(){
		request = (IRequest) spiCoreContextHolder.getSpiObjectFromSpiObject("client.request", "cn.tomsnail.http.client.request.DefaultRequest");
		parseMapper = (IParseMapper) spiCoreContextHolder.getSpiObjectFromSpiObject("client.parsemapper", "cn.tomsnail.http.client.mapper.DefaultParseMapper");
		String invokersClasss = spiCoreContextHolder.getSpiClass("client.invokers","");
		if(!StringUtils.isBlank(invokersClasss)){
			String[] _invokerStrs = invokersClasss.split(",");
			invokers = new ArrayList<IInvoker>();
			for(String clazz:_invokerStrs){
				invokers.add((IInvoker) spiCoreContextHolder.getSpiObject(clazz));
			}
		}
		String filtersClasss = spiCoreContextHolder.getSpiClass("client.filters","");
		if(!StringUtils.isBlank(filtersClasss)){
			String[] _filtersStrs = invokersClasss.split(",");
			filters = new ArrayList<IFilter>();
			for(String clazz:_filtersStrs){
				filters.add((IFilter) spiCoreContextHolder.getSpiObject(clazz));
			}
		}
	}
	
	

	@Override
	public IService getService(String servcieName,String address) throws Exception{
		if(SERVICE_MAP.containsKey(servcieName)){
		}else{
			IClusterService clusterService = getClusterService(address);
			CustomBasicService basicService = new CustomBasicService(servcieName);
			BasicInvoker invoker = new BasicInvoker();
			basicService.setClusterService(ADDRESS_MAP.get(address));
			BasicChainFilter chainFilter = new BasicChainFilter();
			invoker.setChainFilter(chainFilter);
			clusterService.setInvoker(invoker);
			if(filters!=null){
				chainFilter.setFilters(filters);
			}
			invoker.setMapper(parseMapper);
			if(invokers!=null){
				invoker.setInvokers(invokers);
			}
			chainFilter.setRequest(request);
			SERVICE_MAP.put(servcieName, basicService);

		}
		return SERVICE_MAP.get(servcieName);
	}


	protected IClusterService getClusterService(String address)
			throws Exception {
		IClusterService clusterService = null;
		if(ADDRESS_MAP.containsKey(address)){
			clusterService = ADDRESS_MAP.get(address);
		}else{
			//clusterService = new FailClusterService(address);
			String className = spiCoreContextHolder.getSpiClass("client.cluster","cn.tomsnail.http.client.cluster.FailClusterService");
			clusterService = (IClusterService) spiCoreContextHolder.getSpiObject(className, new Object[]{address}, new Class[]{String.class});
			ADDRESS_MAP.put(address, clusterService);
		}
		return clusterService;
	}

	public List<IFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<IFilter> filters) {
		this.filters = filters;
	}

	public List<IInvoker> getInvokers() {
		return invokers;
	}

	public void setInvokers(List<IInvoker> invokers) {
		this.invokers = invokers;
	}

	public IRequest getRequest() {
		return request;
	}

	public void setRequest(IRequest request) {
		this.request = request;
	}

	public IParseMapper getParseMapper() {
		return parseMapper;
	}

	public void setParseMapper(IParseMapper parseMapper) {
		this.parseMapper = parseMapper;
	}
	
}
