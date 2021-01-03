package com.zjw.crm.settings.service;

import com.zjw.crm.exception.LoginException;
import com.zjw.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
