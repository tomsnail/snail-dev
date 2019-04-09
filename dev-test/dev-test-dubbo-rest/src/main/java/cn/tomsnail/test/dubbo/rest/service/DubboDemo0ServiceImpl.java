package cn.tomsnail.test.dubbo.rest.service;

import org.springframework.stereotype.Service;

import cn.tomsnail.util.date.Jdk8DateTimeUtil;

@Service("dubboDemo0Service")
public class DubboDemo0ServiceImpl implements DubboDemo0Service{
	
	

	@Override
	public String getNowTime() {
		return Jdk8DateTimeUtil.getNowDateTimeStr();
	}

}
