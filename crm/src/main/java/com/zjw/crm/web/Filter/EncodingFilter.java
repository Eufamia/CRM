package com.zjw.crm.web.Filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置post请求参数字符集为utf-8
        request.setCharacterEncoding("utf-8");
      //过滤响应数据为utf-8
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request,response);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
