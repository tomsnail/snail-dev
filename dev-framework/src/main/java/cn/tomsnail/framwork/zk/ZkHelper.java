package cn.tomsnail.framwork.zk;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年5月24日 下午1:28:24
 * @see 
 */
public class ZkHelper {
	
	private ZookeeperClient client;
	
	private static volatile ZkHelper instance;
	
	public ZkHelper(String address){
		client = new ZookeeperClient();
		client.connect(address);
	}
	

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:26
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static ZkHelper getInstance(String address){
		if(instance==null){
			synchronized (ZkHelper.class) {
				if(instance==null){
					instance = new ZkHelper(address);
				}
			}
		}
		return instance;
	}

	/**
	 *        创建路径
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:23
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean createPath(String path,boolean isTemp){
		
		if(isTemp){
			client.getZkClient().createEphemeral(path);
		}else{
			client.getZkClient().createPersistent(path);
		}
		return true;
	}
	
	/**
	 *        创建路径，如果父路径不存在，同时创建
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:30
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean createPathWithParent(String path,boolean createParent){
		client.getZkClient().createPersistent(path,createParent);
		return true ;
	}
	
	/**
	 *        创建顺序节点
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:33
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String createNodeSequential(String path,Object data,boolean isTemp){
		String cpath = null;
		if(isTemp){
			cpath = client.getZkClient().createEphemeralSequential(path, data);
		}else{
			cpath = client.getZkClient().createPersistentSequential(path, data);
		}
		return cpath ;
	}
	
	/**
	 *        创建数据节点
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean createNode(String path,Object data,boolean isTemp){
		if(isTemp){
			client.getZkClient().createEphemeral(path, data);
		}else{
			client.getZkClient().createPersistent(path, data);
		}
		return true ;
	}
	
	/**
	 *        创建持久路径
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:38
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean createPath(String path){
		return createPath(path,false);
	}
	
	/**
	 *        路径是否存在
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:41
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean isExtisPath(String path){
		return client.getZkClient().exists(path);
	}
	
	/**
	 *        删除路径
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:43
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean deletePath(String path){
		return client.getZkClient().delete(path);
	}
	
	/**
	 *        查询路径
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:46
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public List<String> getPath(String path){
		return client.getZkClient().getChildren(path);
	}
	
	/**
	 *        读数据
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:49
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public Object readNode(String path){
		return client.getZkClient().readData(path);
	}
	
	/**
	 *        写数据
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:51
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void writeNode(String path,Object data){
		client.getZkClient().writeData(path, data);
	}
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年5月24日 下午2:35:56
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public ZkClient getZkClient(){
		return client.getZkClient();
	}
	
	public ZookeeperClient getClient() {
		return client;
	}

	public void setClient(ZookeeperClient client) {
		this.client = client;
	}

}
