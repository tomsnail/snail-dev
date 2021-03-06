package cn.tomsnail.snail.example.core.framework.service;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.core.obj.base.BaseTransactionComponent;
import cn.tomsnail.snail.core.util.date.Jdk8DateTimeUtil;
import cn.tomsnail.snail.core.util.uuid.UuidUtil;
import cn.tomsnail.snail.example.core.framework.dao.DispatchStrategyDao;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import cn.tomsnail.snail.example.core.framework.model.DispatchStrategyDto;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service(protocol = "dubbo",registry = "zk")
public class DemoServiceImpl extends BaseTransactionComponent implements DemoService {
	
	int count = 1;
	
	@Autowired
	private DispatchStrategyDao dispatchStrategyDao;
	
    @Override
    @LogPoint
    public String hello(String name) {
    	
    	
    	
    	DispatchStrategyDto dispatchStrategy = new DispatchStrategyDto();
    	dispatchStrategy.setId(UuidUtil.getNUUID());
    	dispatchStrategy.setStrategyCode("0");
    	dispatchStrategy.setStrategyCondition("0");
    	dispatchStrategy.setStrategyContext("0");
    	dispatchStrategy.setStrategyName("0");
    	dispatchStrategy.setStrategyStatus("0");
    	dispatchStrategy.setStrategyType("0");
    	dispatchStrategy.setDelFlag("0");
    	dispatchStrategy.setCreateDate(Jdk8DateTimeUtil.getNowDateTimeStr());
    	
    	dispatchStrategyDao.insert(dispatchStrategy);
    	
    	if (count++%2==1) {
    		throw new RuntimeException("事务测试");
    	}
    	
        return "hello "+name+ " "+dispatchStrategyDao.findAllList().size();
    }
    
    
}
