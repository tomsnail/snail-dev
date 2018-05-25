package cn.tomsnail.fs.fastdfs;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.github.tobato.fastdfs.conn.TrackerConnectionManager;
import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.domain.StorageNode;
import com.github.tobato.fastdfs.domain.StorageNodeInfo;
import com.github.tobato.fastdfs.domain.StorageState;
import com.github.tobato.fastdfs.proto.tracker.TrackerDeleteStorageCommand;
import com.github.tobato.fastdfs.proto.tracker.TrackerGetFetchStorageCommand;
import com.github.tobato.fastdfs.proto.tracker.TrackerGetStoreStorageCommand;
import com.github.tobato.fastdfs.proto.tracker.TrackerListGroupsCommand;
import com.github.tobato.fastdfs.proto.tracker.TrackerListStoragesCommand;
import com.github.tobato.fastdfs.service.TrackerClient;

public class DefaultTrackerClient implements TrackerClient{
	
	  	@Resource
	    private TrackerConnectionManager trackerConnectionManager;

	    /**
	     * 鑾峰彇瀛樺偍鑺傜偣
	     */
	    @Override
	    public StorageNode getStoreStorage() {
	        TrackerGetStoreStorageCommand command = new TrackerGetStoreStorageCommand();
	        return trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

	    /**
	     * 鎸夌粍鑾峰彇瀛樺偍鑺傜偣
	     */
	    @Override
	    public StorageNode getStoreStorage(String groupName) {
	        TrackerGetStoreStorageCommand command;
	        if (StringUtils.isBlank(groupName)) {
	            command = new TrackerGetStoreStorageCommand();
	        } else {
	            command = new TrackerGetStoreStorageCommand(groupName);
	        }

	        return trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

	    /**
	     * 鑾峰彇婧愭湇鍔″櫒
	     */
	    @Override
	    public StorageNodeInfo getFetchStorage(String groupName, String filename) {
	        TrackerGetFetchStorageCommand command = new TrackerGetFetchStorageCommand(groupName, filename, false);
	        return trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

	    /**
	     * 鑾峰彇鏇存柊鏈嶅姟鍣�
	     */
	    @Override
	    public StorageNodeInfo getUpdateStorage(String groupName, String filename) {
	        TrackerGetFetchStorageCommand command = new TrackerGetFetchStorageCommand(groupName, filename, true);
	        return trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

	    /**
	     * 鍒楀嚭缁�
	     */
	    @Override
	    public List<GroupState> listGroups() {
	        TrackerListGroupsCommand command = new TrackerListGroupsCommand();
	        return trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

	    /**
	     * 鎸夌粍鍒楀嚭瀛樺偍鐘舵��
	     */
	    @Override
	    public List<StorageState> listStorages(String groupName) {
	        TrackerListStoragesCommand command = new TrackerListStoragesCommand(groupName);
	        return trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

	    /**
	     * 鎸塱p鍒楀嚭瀛樺偍鐘舵��
	     */
	    @Override
	    public List<StorageState> listStorages(String groupName, String storageIpAddr) {
	        TrackerListStoragesCommand command = new TrackerListStoragesCommand(groupName, storageIpAddr);
	        return trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

	    /**
	     * 鍒犻櫎瀛樺偍鑺傜偣
	     */
	    @Override
	    public void deleteStorage(String groupName, String storageIpAddr) {
	        TrackerDeleteStorageCommand command = new TrackerDeleteStorageCommand(groupName, storageIpAddr);
	        trackerConnectionManager.executeFdfsTrackerCmd(command);
	    }

		public DefaultTrackerClient(
				TrackerConnectionManager trackerConnectionManager) {
			super();
			this.trackerConnectionManager = trackerConnectionManager;
		}
	    
	    

}
