package com.akon.spring.mysql.service.impl;

import com.akon.spring.mysql.entity.UserInfo;
import com.akon.spring.mysql.model.UserInfoMapper;
import com.akon.spring.mysql.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper mapper;

    @Override
    public String insert(UserInfo record) {
        int insert = mapper.insert(record);
        return insert>0?"success":"fail";
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer userId) {
        return mapper.selectByPrimaryKey(userId);
    }

    @Override
    public String updateByPrimaryKey(UserInfo record) {
        int update = mapper.updateByPrimaryKey(record);
        return update>0?"success":"fail";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String update1(UserInfo record) {
        int update=0;
        try {
            Thread.sleep(2000l);
            update=mapper.updateByPrimaryKey(record);
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return update>0?"success":"fail";
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String update2(UserInfo record) {
        int update=0;
        try {
            Thread.sleep(3000l);
            update=mapper.updateByPrimaryKey(record);
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return update>0?"success":"fail";
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String update3(UserInfo record) {
        int update=0;
        try {
            Thread.sleep(6000l);
            update=mapper.updateByPrimaryKey(record);
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return update>0?"success":"fail";
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public UserInfo select(Integer userId) {
        //幻读
        UserInfo userInfo=null;
        try {
            userInfo = mapper.selectByPrimaryKey(userId);
            log.info("init userInfo:"+userInfo);
            Thread.sleep(3000l);
            UserInfo record=new UserInfo();
            record.setUserId(1);
            record.setUserName("马六");
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            mapper.updateByPrimaryKey(record);
            Thread.sleep(4500l);
            userInfo = mapper.selectByPrimaryKey(userId);
            log.info("first userInfo:"+userInfo);
            Thread.sleep(3000l);
             userInfo = mapper.selectByPrimaryKey(userId);
            log.info("two userInfo:"+userInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public String insert1(UserInfo record) {
        int insert= 0;
        try {
            UserInfo userInfo = mapper.selectByPrimaryKey(record.getUserId());
            log.info(Thread.currentThread().getName()+" userInfo:"+userInfo);
            Thread.sleep(3000l);
            if(userInfo==null)
            insert= mapper.insert(record);
            log.info(Thread.currentThread().getName()+" 更新成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return insert>0?"success":"fail";
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public String insert2(UserInfo record) {
        int insert= 0;
        try {
            Thread.sleep(2000l);
            UserInfo userInfo = mapper.selectByPrimaryKey(record.getUserId());
            log.info(Thread.currentThread().getName()+" userInfo:"+userInfo);
            if(userInfo==null)
            insert= mapper.insert(record);
            log.info(Thread.currentThread().getName()+" 更新成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return insert>0?"success":"fail";
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public String update4(UserInfo record) {
        int update= 0;
        try {
            UserInfo userInfo = mapper.selectByPrimaryKey(record.getUserId());
            log.info(Thread.currentThread().getName()+" userInfo:"+userInfo);
            Thread.sleep(3000l);
            update= mapper.updateByPrimaryKey(record);
            log.info(Thread.currentThread().getName()+" 更新成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return update>0?"success":"fail";
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public String update5(UserInfo record) {
        int update= 0;
        try {
            Thread.sleep(2000l);
            UserInfo userInfo = mapper.selectByPrimaryKey(record.getUserId());
            log.info(Thread.currentThread().getName()+" userInfo:"+userInfo);
            update= mapper.updateByPrimaryKey(record);
            log.info(Thread.currentThread().getName()+" 更新成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return update>0?"success":"fail";
    }
}
