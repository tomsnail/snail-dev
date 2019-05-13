package cn.tomsnail.snail.ext.security.core.filter.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import cn.tomsnail.snail.ext.security.core.util.RequestUtil;




/**
 *        springmvc httpservlet body 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午6:29:34
 * @see 
 */
public class BodyReaderHttpServletRequestWrapper  extends HttpServletRequestWrapper{

	private final String body;   
    
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)    throws IOException {   
        super(request);   
        body = RequestUtil.getBodyString(request.getReader());   
    }   
  
    @Override  
    public BufferedReader getReader() throws IOException {   
        return new BufferedReader(new InputStreamReader(getInputStream()));   
    }   
  
    @Override  
    public ServletInputStream getInputStream() throws IOException {   
        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());   
        return new ServletInputStream() {   
  
            @Override  
            public int read() throws IOException {   
                return bais.read();   
            }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {
				// TODO Auto-generated method stub
				
			}   
        };   
    }   
    
    public String getBodyStr(){
    	return this.body;
    }
}
