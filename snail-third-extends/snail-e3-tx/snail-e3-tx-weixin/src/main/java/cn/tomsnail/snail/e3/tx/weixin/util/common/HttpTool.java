package cn.tomsnail.snail.e3.tx.weixin.util.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.nutz.http.Http;
import org.nutz.http.Request;
import org.nutz.http.Request.METHOD;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.http.sender.FilePostSender;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.log.Log;
import org.nutz.log.Logs;


// TODO Http.disableJvmHttpsCheck();
public class HttpTool {

    private static final Log log = Logs.get();

    private static final int CONNECT_TIME_OUT = 5 * 1000;
    private static final String FILE_NAME_FLAG = "filename=";

    public static String get(String url) {
        if (log.isDebugEnabled()) {
            log.debugf("Request url: %s, default timeout: %d", url, CONNECT_TIME_OUT);
        }

        try {
            Response resp = Http.get(url, CONNECT_TIME_OUT);
            if (resp.isOK()) {
                String content = resp.getContent("UTF-8");
                if (log.isInfoEnabled()) {
                    log.infof("GET Request success. Response content: %s", content);
                }
                return content;
            }

            throw Lang.wrapThrow(new RuntimeException(String.format("Get request [%s] failed. status: %d",
                                                                    url,
                                                                    resp.getStatus())));
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
    }

    public static String post(String url, String body) {
        if (log.isDebugEnabled()) {
            log.debugf("Request url: %s, post data: %s, default timeout: %d",
                       url,
                       body,
                       CONNECT_TIME_OUT);
        }

        try {
            Request req = Request.create(url, METHOD.POST);
            req.setEnc("UTF-8");
            req.setData(body);
            Response resp = Sender.create(req, CONNECT_TIME_OUT).send();
            if (resp.isOK()) {
                String content = resp.getContent();
                if (log.isInfoEnabled()) {
                    log.infof("POST Request success. Response content: %s", content);
                }
                return content;
            }

            throw Lang.wrapThrow(new RuntimeException(String.format("Post request [%s] failed. status: %d",
                                                                    url,
                                                                    resp.getStatus())));
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
    }

    public static String upload(String url, File file) {
        if (log.isDebugEnabled()) {
            log.debugf("Upload url: %s, file name: %s, default timeout: %d",
                       url,
                       file.getName(),
                       CONNECT_TIME_OUT);
        }

        try {
            Request req = Request.create(url, METHOD.POST);
            req.getParams().put("media", file);
            Response resp = new FilePostSender(req).send();
            if (resp.isOK()) {
                String content = resp.getContent();
                return content;
            }

            throw Lang.wrapThrow(new RuntimeException(String.format("Upload file [%s] failed. status: %d",
                                                                    url,
                                                                    resp.getStatus())));
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
    }

    public static Object download(String url) {
        if (log.isDebugEnabled()) {
            log.debugf("Download url: %s, default timeout: %d", url, CONNECT_TIME_OUT);
        }

        try {
            Response resp = Http.get(url);
            if (resp.isOK()) {
                String cd = resp.getHeader().get("Content-disposition");
                if (log.isInfoEnabled()) {
                    log.infof("Get download file info: %s", cd);
                }

                if (Lang.isEmpty(cd)) {
                    return resp.getContent();
                }

                cd = cd.substring(cd.indexOf(FILE_NAME_FLAG) + FILE_NAME_FLAG.length());
                String tmp = cd.startsWith("\"") ? cd.substring(1) : cd;
                tmp = tmp.endsWith("\"") ? cd.replace("\"", "") : cd;
                String filename = tmp.substring(0, tmp.lastIndexOf("."));
                String fileext = tmp.substring(tmp.lastIndexOf("."));
                if (log.isInfoEnabled()) {
                    log.infof("Download file name: %s", filename);
                    log.infof("Download file ext: %s", fileext);
                }

                File tmpfile = File.createTempFile(filename, fileext);
                InputStream is = resp.getStream();
                OutputStream os = new FileOutputStream(tmpfile);
                Streams.writeAndClose(os, is);
                return tmpfile;
            }

            throw Lang.wrapThrow(new RuntimeException(String.format("Download file [%s] failed. status: %d, content: %s",
                                                                    url,
                                                                    resp.getStatus(),
                                                                    resp.getContent())));
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
    }
    
	//把从服务器获得图片的输入流InputStream写到本地磁盘
	public static void saveImageToDisk() {

		InputStream inputStream = getInputStream();
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
		    String path = "D:\\test1.jpg";
			fileOutputStream = new FileOutputStream(path);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	// 从服务器获得一个输入流(本例是指从服务器获得一个image输入流)
	public static InputStream getInputStream() {
		InputStream inputStream = null;
		HttpURLConnection httpURLConnection = null;

		try {
			URL url = new URL("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHh8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyLXFHZU5Na0s4MFQxMDAwMDAwN0wAAgRBFm5YAwQAAAAA");
			httpURLConnection = (HttpURLConnection) url.openConnection();
			// 设置网络连接超时时间
			httpURLConnection.setConnectTimeout(3000);
			// 设置应用程序要从网络连接读取数据
			httpURLConnection.setDoInput(true);

			httpURLConnection.setRequestMethod("GET");
			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				// 从服务器返回一个输入流
				inputStream = httpURLConnection.getInputStream();

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputStream;
	}
	public static void main(String[] args) {
		saveImageToDisk();
	}
}
