package cn.tomsnail.log;

import org.springframework.stereotype.Component;









import cn.tomsnail.config.client.ConfigClientFactory;


import cn.tomsnail.util.string.StringUtils;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.internal.DefaultTransaction;

@Component("catService")
public class CatLogService implements LogService{

	@Override
	public void log(Log log) {
		
		String isDo = ConfigClientFactory.getInstance().getConfigClient().getConfig("system.log.cat", "true");
		
		if(StringUtils.isBlank(isDo)||!isDo.equals("true")){
			return;
		}
		Transaction cat = null;
		if(log==null||log.getContent()==null){
			return;
		}
		cat = Cat.getProducer().newTransaction("URL", log.getDesc());
		
		if(cat instanceof DefaultTransaction){
		}else{
			try {
				Long s = Long.valueOf(log.getCostTime());
				Thread.currentThread().sleep(s);
			} catch (Exception e) {
			}
		}
		
		if(log.getLevel()==Log.ERROR){
			Cat.getProducer().logError(log.getThrowable());
            cat.setStatus(Log.ERROR+"");
		}else if(log.getLevel()==Log.B_ERROR){
			Event event = Cat.getProducer().newEvent("B_ERROR", log.getDesc());
			event.setStatus(Log.B_ERROR+"");
			event.addData("msg="+log.getResults().toString());
			event.complete();
			cat.setStatus(Log.B_ERROR+"");
		}else{
			Cat.getProducer().logEvent("URL-CALL", log.getDesc(), Event.SUCCESS, "content="+log.getContent().toString());
            cat.setStatus(Transaction.SUCCESS);
		}	
		cat.complete();
		if(cat instanceof DefaultTransaction){
			try {
				DefaultTransaction defaultTransaction = (DefaultTransaction) cat;
				defaultTransaction.setDurationInMillis(Long.valueOf(log.getCostTime()));
			} catch (Exception e) {
			}
		}
	}
}
