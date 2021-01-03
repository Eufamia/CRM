package com.zjw.crm.settings.dao;

import com.zjw.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String, Object> map);
}
