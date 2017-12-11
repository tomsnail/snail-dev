package cn.tomsnail.http.client.invoker;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;
import cn.tomsnail.http.client.filter.ChainFilter;
import cn.tomsnail.http.client.mapper.IParseMapper;

/**
 *        基本执行器，不实现IInvoker，用于调度IInvoker
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午2:44:41
 * @see 
 */
@Component
@ComponentScan(basePackages="cn.tomsnail.http.client.filter,cn.tomsnail.http.client.mapper")
public class BasicInvoker {
	
	/**
	 * 执行器列表
	 */
	@Resource
	private List<IInvoker> invokers;
	
	/**
	 * 请求过滤链
	 */
	@Resource
	private ChainFilter chainFilter;
	
	/**
	 * 转换映射
	 */
	@Resource
	private IParseMapper mapper;

	/**
	 *        执行请求
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:24:40
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void invoker(Request request, Response response) {
		if(request==null||response==null){
			return ;
		}
		if(invokers!=null){
			for(IInvoker invoker:invokers){
				if(!invoker.invoker(request, response)){
					return;
				}
			}
		}
		chainFilter.doFilter(request, response);
		if(response.getStatus()==200){
			mapper.parse(response, response.getBody().getClass());
		}
	}

	public List<IInvoker> getInvokers() {
		return invokers;
	}

	public void setInvokers(List<IInvoker> invokers) {
		this.invokers = invokers;
	}

	public ChainFilter getChainFilter() {
		return chainFilter;
	}

	public void setChainFilter(ChainFilter chainFilter) {
		this.chainFilter = chainFilter;
	}

	public IParseMapper getMapper() {
		return mapper;
	}

	public void setMapper(IParseMapper mapper) {
		this.mapper = mapper;
	}
	
	

}
