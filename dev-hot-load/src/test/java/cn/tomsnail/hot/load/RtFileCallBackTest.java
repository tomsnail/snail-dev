package cn.tomsnail.hot.load;

import java.io.File;

import cn.tomsnail.hot.load.file.FileChangeType;
import cn.tomsnail.hot.load.file.RtFileCallBack;
import cn.tomsnail.hot.load.file.RtFileListener;

public class RtFileCallBackTest implements RtFileCallBack{

	@Override
	public void call(File file, FileChangeType fileChangeType) {
		System.out.println(file!=null?file.getName():"null");
	}
	
	public static void main(String[] args) {
		try {
			new RtFileListener("C:\\Users\\yangsong\\Desktop\\hotload",1000,new RtFileCallBackTest()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
