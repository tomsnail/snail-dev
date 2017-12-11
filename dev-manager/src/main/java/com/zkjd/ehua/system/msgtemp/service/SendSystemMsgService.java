package com.zkjd.ehua.system.msgtemp.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.zkjd.ehua.system.msg.dao.SystemMessageDao;
import com.zkjd.ehua.system.msg.entity.SystemMessage;
import com.zkjd.ehua.system.msgtemp.dao.MsgTemplateDao;
import com.zkjd.ehua.system.msgtemp.entity.MsgTemplate;

@Service
public class SendSystemMsgService {

	@Autowired
	private MsgTemplateDao msgTemplateDao;
	
	@Autowired
	private SystemMessageDao systemMessageDao;
	
	  


	
	
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年3月25日 下午5:40:39
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	@Transactional(readOnly=false)
	public boolean sendSystemMsg(String type,Map<String,String> paramMap){
		
		if(paramMap==null||paramMap.size()==0||StringUtils.isBlank(type)){
			return false;
		}
		
		MsgTemplate msgTemplate = new MsgTemplate();
		msgTemplate.setTemplType(type);
		List<MsgTemplate> msgTemplates =  msgTemplateDao.findList(msgTemplate);
		if(msgTemplates==null||msgTemplates.size()!=1){
			return false;
		}
		MsgTemplate template = msgTemplates.get(0);
		String context = template.getTemplContext();
		if(StringUtils.isBlank(context)){
			return false;
		}
		
		Iterator<String> it = paramMap.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			context = context.replace("${"+key+"}", paramMap.get(key));
		}
		
		if(context.contains("${")){
			return false;
		}
		
		String userId = paramMap.get("userId");
		if(StringUtils.isBlank(userId)){
			return false;
		}
		
		
		SystemMessage systemMessage = new SystemMessage();
		Date d = new Date();
		systemMessage.setId(IdGen.uuid());
		systemMessage.setCreateBy(UserUtils.getUser());
		systemMessage.setCreateDate(d);
		systemMessage.setIsMsgMulti("0");
		systemMessage.setMsgContent(context);
		systemMessage.setMsgDate(d);
		systemMessage.setMsgSys("1");
		systemMessage.setMsgTitle(template.getTemplName());
		systemMessage.setMsgType(type);
		systemMessage.setMsgUrl("");
		//systemMessage.setReadUser(readUser);
		systemMessage.setReceivePerson(userId);
		systemMessage.setSendPerson("999999");
		systemMessage.setStatus("1");
		systemMessage.setUpdateBy(UserUtils.getUser());
		systemMessage.setUpdateDate(d);
		systemMessageDao.insert(systemMessage);
		
		return true;
	}
	
}
