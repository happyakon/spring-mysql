package com.akon.spring.mysql.service;

import com.akon.spring.mysql.entity.UserInfo;

public interface UserInfoService {

    String insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    String updateByPrimaryKey(UserInfo record);

    String update1(UserInfo record);
    String update2(UserInfo record);
    String update3(UserInfo record);

    UserInfo select(Integer userId);
}
