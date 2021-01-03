package com.zjw.crm.web.Filter;

import com.zjw.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       //获取http的请求对象和响应对象
        HttpServletResponse response= (HttpServletResponse) resp;
        HttpServletRequest request= (HttpServletRequest) req;
        HttpSession session=request.getSession();
        //获取用户需要访问的资源路径
        String path=request.getServletPath();
        User user=(User)session.getAttribute("user");
        /*如果访问的是登录页或者是登录验证页或者已验证过，则放行*/
        if("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)||user!=null){
            chain.doFilter(request, response);
        }
        else {
            /*重定向到登录页
            * 在实际的项目中，对于路径的使用，不论是前端还是后端，应该一律使用绝对路径
            * 如果使用请求转发，使用的是一种特殊绝对路径的方式，这种方式前面不加项目名，这种路径也被成为内部路径
            * 如果使用重定向，使用的是传统绝对路径，前面必须以/项目名开头，后面跟具体的资源路径
            *
            * 由于需要在使用之后在地址栏显示地址，所以建议此处使用重定向的方式*/

            /*动态获取项目名称*/
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
