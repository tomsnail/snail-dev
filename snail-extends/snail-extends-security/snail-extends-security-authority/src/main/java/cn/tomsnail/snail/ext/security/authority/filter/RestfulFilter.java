package cn.tomsnail.snail.ext.security.authority.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface RestfulFilter {


 public boolean filter(HttpServletRequest request, HttpServletResponse response, Object...args) throws RestfulFilterException;

}
