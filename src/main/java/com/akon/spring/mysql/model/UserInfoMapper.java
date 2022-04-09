package com.akon.spring.mysql.model;

import com.akon.spring.mysql.entity.UserInfo;
import org.apache.ibatis.annotations.Options;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}