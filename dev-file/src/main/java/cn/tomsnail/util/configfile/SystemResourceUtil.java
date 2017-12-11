package cn.tomsnail.util.configfile;

import java.net.URL;

/**
 * 系统工具类
 * User: angryant
 * Date: 13-9-26
 * Time: 上午10:35
 */
public class SystemResourceUtil {

    public static URL findAsResource(String path) {
        URL url = null;

        // First, try to locate this resource through the current
        // context classloader.
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            url = contextClassLoader.getResource(path);
        }
        if (url != null)
            return url;
        // Next, try to locate this resource through the system classloader
        url = ClassLoader.getSystemClassLoader().getResource(path);

        // Anywhere else we should look?
        return url;
    }

}
