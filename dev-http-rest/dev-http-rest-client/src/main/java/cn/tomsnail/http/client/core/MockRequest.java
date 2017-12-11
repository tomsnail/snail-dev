package cn.tomsnail.http.client.core;

/**
 *        mock请求
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月25日 下午1:35:14
 * @see 
 */
public class MockRequest<R,Q> extends Request<R>{

	private Q q;
	
	public  MockRequest(Q q){
		this.q = q;
	}

	public Q getQ() {
		return q;
	}

	public void setQ(Q q) {
		this.q = q;
	}
	
}
