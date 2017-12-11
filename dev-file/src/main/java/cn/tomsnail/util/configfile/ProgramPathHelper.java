package cn.tomsnail.util.configfile;

import java.net.URL;
import java.net.URLDecoder;

public class ProgramPathHelper {

	public static String getProgramPath() {
		Class<ProgramPathHelper> cls = ProgramPathHelper.class;
		ClassLoader loader = cls.getClassLoader();
		//
		// Get the full name of the class.
		//
		String clsName = cls.getName() + ".class";
		//
		// Get the package that include the class.
		//
		Package pack = cls.getPackage();
		String path = "";
		//
		// Transform package name to path.
		//
		if (pack != null) {
			String packName = pack.getName();
			//
			// Get the class's file name.
			//
			clsName = clsName.substring(packName.length() + 1);
			//
			// If package is simple transform package name to path directly,
			// else transform package name to path by package name's
			// constituent.
			//
			path = packName;
			if (path.indexOf(".") > 0) {
				path = path.replace(".", "/");
			}
			path = path + "/";
		}

		URL url = loader.getResource(path + clsName);
		//
		// Get path information form the instance of URL.
		//
		String retPath = url.getPath();
		//
		// Delete protocol name "file:" form path information.
		//
		try {
			int pos = retPath.indexOf("file:");
			if (pos > -1) {
				retPath = retPath.substring(pos + 5);
			}
			//
			// Delete the information of class file from the information of
			// path.
			//
			pos = retPath.indexOf(path + clsName);
			retPath = retPath.substring(0, pos - 1);
			//
			// If the class file was packageed into JAR e.g. file, delete the
			// file name of the corresponding JAR e.g..
			//
			if (retPath.endsWith("!")) {
				retPath = retPath.substring(0, retPath.lastIndexOf("/"));
			}

			retPath = URLDecoder.decode(retPath, "utf-8");
		} catch (Exception e) {
			retPath = null;
			System.out.println(e.toString());
		}

		return retPath;
	}
	
	public static String getLocalProgramPath() {
	
		String retPath = null;
		try {
			Class<ProgramPathHelper> cls = ProgramPathHelper.class;
			ClassLoader loader = cls.getClassLoader();			
			URL url = loader.getResource("/");
			retPath = url.getPath();
			retPath = URLDecoder.decode(retPath, "utf-8");
		} catch (Exception e) {
			retPath = null;
			System.out.println(e.toString());
		}
		return retPath;
	}
	
	public static String getProgramPath(Class clazz) {
		Class cls = clazz;
		ClassLoader loader = cls.getClassLoader();
		//
		// Get the full name of the class.
		//
		String clsName = cls.getName() + ".class";
		//
		// Get the package that include the class.
		//
		Package pack = cls.getPackage();
		String path = "";
		//
		// Transform package name to path.
		//
		if (pack != null) {
			String packName = pack.getName();
			//
			// Get the class's file name.
			//
			clsName = clsName.substring(packName.length() + 1);
			//
			// If package is simple transform package name to path directly,
			// else transform package name to path by package name's
			// constituent.
			//
			path = packName;
			if (path.indexOf(".") > 0) {
				path = path.replace(".", "/");
			}
			path = path + "/";
		}

		URL url = loader.getResource(path + clsName);
		//
		// Get path information form the instance of URL.
		//
		String retPath = url.getPath();
		//
		// Delete protocol name "file:" form path information.
		//
		try {
			int pos = retPath.indexOf("file:");
			if (pos > -1) {
				retPath = retPath.substring(pos + 5);
			}
			//
			// Delete the information of class file from the information of
			// path.
			//
			pos = retPath.indexOf(path + clsName);
			retPath = retPath.substring(0, pos - 1);
			//
			// If the class file was packageed into JAR e.g. file, delete the
			// file name of the corresponding JAR e.g..
			//
			if (retPath.endsWith("!")) {
				retPath = retPath.substring(0, retPath.lastIndexOf("/"));
			}

			retPath = URLDecoder.decode(retPath, "utf-8");
		} catch (Exception e) {
			retPath = null;
			System.out.println(e.toString());
		}

		return retPath;
	}

}
