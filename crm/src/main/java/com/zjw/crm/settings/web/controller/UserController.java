package com.zjw.crm.settings.web.controller;

import com.zjw.crm.settings.domain.User;
import com.zjw.crm.settings.service.UserService;
import com.zjw.crm.settings.service.impl.UserServiceImpl;
import com.zjw.crm.util.MD5Util;
import com.zjw.crm.util.PrintJson;
import com.zjw.crm.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这是controller");
        String path=request.getServletPath();//获取。do的地址
        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("controller的login方法");
       //从前端获取用户名和密码
        String loginAct=request.getParameter("loginAct");
        String loginPwd=request.getParameter("loginPwd");
        //将密码通过工具来解密成数据库中存储的数据
        loginPwd= MD5Util.getMD5(loginPwd);
        //接收ip地址
        String ip=request.getRemoteAddr();
        System.out.println("ip地址---"+ip);
        //获取service的代理对象
        UserService service= (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{
          User user=  service.login(loginAct,loginPwd,ip);
          request.getSession().setAttribute("user",user);
          /*程序如果执行到此处，说明没有前面没有异常抛出，表示登录成功*/
            PrintJson.printJsonFlag(response,true);//通知前端登录成功
        }catch (Exception e){
            /*一旦执行到此处，说明有异常抛出，登录失败*/
            e.printStackTrace();
            String msg=e.getMessage();
            /*通过map接收success的值同时接收打印的信息，但是其实也是可以使用vo的方式，但此处只使用一次，为此声明出一个类会比较浪费*/
            Map<String,Object> map=new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }

}
