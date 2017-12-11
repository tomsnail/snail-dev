package cn.tomsnail.http.client.filter;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.client.core.Response;
import cn.tomsnail.http.client.request.IRequest;

/**
 * 		基础请求过滤链
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月23日 下午2:53:27
 * @see
 */
@Component
@ComponentScan(basePackages="cn.tomsnail.http.client.request")
public class BasicChainFilter implements ChainFilter{

	/**
	 * 过滤列表
	 */
	@Resource
	private List<IFilter> filters;

	/**
	 * 请求
	 */
	@Resource
	private IRequest request;

	/**
	 * 过滤索引
	 */
	private int _index = -1;

	/**
	 * 排序
	 */
	private int sort = 1;

	@Override
	public void doFilter(Request request, Response response) {
		if (filters != null) {
			int index = countIndex(sort);
			if (index == this.filters.size()) {
				response.cloneObject(this.request.request(request));
				sort = -1;
				index = countIndex(sort);
			}
			if(index>=0&&index<this.filters.size())
				filters.get(index).doFilter(request, response, this);
		} else {
			response.cloneObject(this.request.request(request));
		}
	}

	/**
	 *        计数
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:22:56
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public int countIndex(int sort) {
		return _index = _index + sort;

	}

	public List<IFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<IFilter> filters) {
		this.filters = filters;
	}

	public int get_index() {
		return _index;
	}

	public void set_index(int _index) {
		this._index = _index;
	}

	public IRequest getRequest() {
		return request;
	}

	public void setRequest(IRequest request) {
		this.request = request;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	

}
