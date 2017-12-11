package com.zkjd.ehua.common.utils;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.zkjd.ehua.system.config.entity.SysConfig;

public class MathUtils {

	public static String getRandom(int size){

		String str = "";

		str += (int)(Math.random()*9+1);

		for(int i = 0; i < size; i++){

			str += (int)(Math.random()*10);

		}


		return str;

	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE	, -30);
		System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyyMMdd"));
	}
	
}
