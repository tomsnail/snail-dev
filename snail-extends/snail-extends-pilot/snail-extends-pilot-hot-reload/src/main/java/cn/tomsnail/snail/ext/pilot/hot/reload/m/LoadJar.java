package cn.tomsnail.snail.ext.pilot.hot.reload.m;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadJar {
	
	private ClassLoader _classLoader;
	
	public LoadJar(ClassLoader classLoader) {
		super();
		this._classLoader = classLoader;
	}



	public void load(String path) throws Exception{
		// 系统类库路径
		File libPath = new File(path);
		// 获取所有的.jar和.zip文件
		File[] jarFiles = libPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return true;
			}
		});

		if (jarFiles != null) {
			// 从URLClassLoader类中获取类所在文件夹的方法
			// 对于jar文件，可以理解为一个存放class文件的文件夹
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			boolean accessible = method.isAccessible();		// 获取方法的访问权限
			try {
				if (accessible == false) {
					method.setAccessible(true);		// 设置方法的访问权限
				}
				// 获取系统类加载器
				URLClassLoader classLoader = (URLClassLoader) _classLoader.getSystemClassLoader();
				for (File file : jarFiles) {
					URL url = file.toURI().toURL();
					try {
						method.invoke(classLoader, url);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} finally {
				method.setAccessible(accessible);
			}
			
		}
	}
	
	public void loadFile(File file) throws Exception{
		

		if (file != null) {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			boolean accessible = method.isAccessible();		// 获取方法的访问权限
			try {
				if (accessible == false) {
					method.setAccessible(true);		// 设置方法的访问权限
				}
				// 获取系统类加载器
				URLClassLoader classLoader = (URLClassLoader) _classLoader.getSystemClassLoader();
				URL url = file.toURI().toURL();
				//URLClassLoader _classLoader = classLoader.newInstance(new URL[]{url},classLoader);

				try {
					System.out.println("addUrl:"+url.toString());
					method.invoke(classLoader, url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} finally {
				method.setAccessible(accessible);
			}
			
		}
	}
	
}
