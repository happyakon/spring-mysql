package com.akon.spring.mysql.rest;

import com.akon.spring.mysql.entity.UserInfo;
import com.akon.spring.mysql.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoRest {

    @Autowired
    UserInfoService service;

    @RequestMapping("/insert")
    public String insertUser(@RequestBody UserInfo record){
        return service.insert(record);
    }

    @RequestMapping("/queryUser")
    public UserInfo selectByPrimaryKey(Integer userId) {
        return service.selectByPrimaryKey(userId);
    }

    @RequestMapping("/updateUser")
    public String updateByPrimaryKey(@RequestBody UserInfo record) {
        return service.updateByPrimaryKey(record);
    }
}
