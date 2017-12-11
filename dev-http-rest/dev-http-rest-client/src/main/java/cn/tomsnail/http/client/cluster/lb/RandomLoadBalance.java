package cn.tomsnail.http.client.cluster.lb;

import java.util.ArrayList;
import java.util.List;

import cn.tomsnail.http.client.cluster.ClusterRequestObject;
import cn.tomsnail.http.client.core.Request;
import cn.tomsnail.http.register.core.JsonServerRegisterObject;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月28日 下午5:58:01
 * @see 
 */
public class RandomLoadBalance implements ILoadBalance{
	
	@Override
	public Request getServiceRequest(String serviceName, List<ClusterRequestObject> requests) {
		long[] indexs = new long[requests.size()+1];
		indexs[0] = 0;
		long t = 0;
		for(int i=0;i<requests.size();i++){
			t = t + requests.get(i).getJsonServerRegisterObject().getWeight();
			indexs[i+1] = indexs[i]+ requests.get(i).getJsonServerRegisterObject().getWeight();
		}
		indexs[requests.size()] = t;
		long r = (long) (Math.random()*t);
		for(int i=1;i<indexs.length;i++){
			if(indexs[i]>r){
				try {
					return requests.get(i-1).getRequest().clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		List<ClusterRequestObject> requests = new ArrayList<ClusterRequestObject>();
		ClusterRequestObject clusterRequestObject1 = new ClusterRequestObject();
		JsonServerRegisterObject jsonServerRegisterObject1 = new JsonServerRegisterObject();
		jsonServerRegisterObject1.setWeight(100);
		clusterRequestObject1.setJsonServerRegisterObject(jsonServerRegisterObject1);

		ClusterRequestObject clusterRequestObject2 = new ClusterRequestObject();
		JsonServerRegisterObject jsonServerRegisterObject2 = new JsonServerRegisterObject();
		jsonServerRegisterObject2.setWeight(200);
		clusterRequestObject2.setJsonServerRegisterObject(jsonServerRegisterObject2);

		ClusterRequestObject clusterRequestObject3 = new ClusterRequestObject();
		JsonServerRegisterObject jsonServerRegisterObject3 = new JsonServerRegisterObject();
		jsonServerRegisterObject3.setWeight(300);
		clusterRequestObject3.setJsonServerRegisterObject(jsonServerRegisterObject3);

		ClusterRequestObject clusterRequestObject4 = new ClusterRequestObject();
		JsonServerRegisterObject jsonServerRegisterObject4 = new JsonServerRegisterObject();
		jsonServerRegisterObject4.setWeight(100);
		clusterRequestObject4.setJsonServerRegisterObject(jsonServerRegisterObject4);

		ClusterRequestObject clusterRequestObject5 = new ClusterRequestObject();
		JsonServerRegisterObject jsonServerRegisterObject5 = new JsonServerRegisterObject();
		jsonServerRegisterObject5.setWeight(200);
		clusterRequestObject5.setJsonServerRegisterObject(jsonServerRegisterObject5);
		requests.add(clusterRequestObject1);
		requests.add(clusterRequestObject2);
		requests.add(clusterRequestObject3);
		requests.add(clusterRequestObject4);
		requests.add(clusterRequestObject5);
		long[] indexs = new long[requests.size()+1];
		long t = 0;
		for(int i=0;i<requests.size();i++){
			t = t + requests.get(i).getJsonServerRegisterObject().getWeight();
			indexs[i+1] = indexs[i]+ requests.get(i).getJsonServerRegisterObject().getWeight();
		}
		indexs[0] = 0;
		indexs[requests.size()] = t;
		int[] cs = new int[5];
		
		for(int k=0;k<10000;k++){
			long r = (long) (Math.random()*t);
			for(int i=1;i<indexs.length;i++){
				if(indexs[i]>r){
					cs[i-1]= cs[i-1]+1;
					break;
				}
			}
		}
		for(int i=0;i<cs.length;i++){
			System.out.println(cs[i]/10000d);
		}
		
	}
}
