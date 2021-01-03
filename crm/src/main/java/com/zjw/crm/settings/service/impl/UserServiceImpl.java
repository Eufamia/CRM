package com.zjw.crm.settings.service.impl;

import com.zjw.crm.exception.LoginException;
import com.zjw.crm.settings.dao.UserDao;
import com.zjw.crm.settings.domain.User;
import com.zjw.crm.settings.service.UserService;
import com.zjw.crm.util.DateTimeUtil;
import com.zjw.crm.util.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    //获取userDao的对象
    private UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        //将获取的用户名和密码封装到map中
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
      User user=  userDao.login(map);
        if(user==null){
            throw new LoginException("用户名或者密码错误");
        }
        /*执行到这里表明账号密码正确*/
        String expireTime=user.getExpireTime();
        String currentTime= DateTimeUtil.getSysTime();//获取系统当前时间
        if(expireTime.compareTo(currentTime)<0) {
            throw new LoginException("账号已失效！");
        }
        String lockstate=user.getLockState();
        if ("0".equals(lockstate)){
            throw new LoginException("账户已被锁定");
        }
        String allowsIp=user.getAllowIps();
        System.out.println("允许的ip地址"+allowsIp);
        if(allowsIp.equals(ip)){
            throw new LoginException("ip地址受限");
        }
        return user;
    }

}
