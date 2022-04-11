package com.akon.spring.mysql;

import com.akon.spring.mysql.entity.UserInfo;
import com.akon.spring.mysql.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;

@SpringBootTest
class SpringMysqlApplicationTests {

    @Autowired
    private UserInfoService service;

    @Test
    public void testInsert(){
        UserInfo user=new UserInfo();
        user.setUserName("akon");
        user.setUserAge(33);
        user.setUserScore(324l);
        user.setUserSex("F");
        user.setUserSlary(12.435d);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        String result = service.insert(user);
        Assert.isTrue(true, String.valueOf("success".equals(result)));
    }

    @Test
    public void testUpdate(){
        UserInfo user1=new UserInfo();
        user1.setUserName("张三");
        user1.setUserId(1);
        user1.setUpdateTime(new Date());
        user1.setCreateTime(new Date());
        UserInfo user2=new UserInfo();
        user2.setUserName("李四");
        user2.setUserId(1);
        user2.setUpdateTime(new Date());
        user2.setCreateTime(new Date());
        UserInfo user3=new UserInfo();
        user3.setUserName("王五");
        user3.setUserId(1);
        user3.setUpdateTime(new Date());
        user3.setCreateTime(new Date());
        Thread thread1=new Thread(()->{
            service.update1(user1);
        });
        Thread thread2=new Thread(()->{
            service.update2(user2);
        });
        Thread thread3=new Thread(()->{
            service.update3(user3);
        });
        Thread thread4=new Thread(()->{
            service.select(1);
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            Thread.sleep(14000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void phantomRead(){
        UserInfo user1=new UserInfo();
        user1.setUserName("张三T2");
        user1.setUserId(18);
        user1.setUpdateTime(new Date());
        user1.setCreateTime(new Date());
        UserInfo user2=new UserInfo();
        user2.setUserName("李四T2");
        user2.setUserId(18);
        user2.setUpdateTime(new Date());
        user2.setCreateTime(new Date());
        Thread thread1=new Thread(()->
                service.insert1(user1)
                ,"t1");
        Thread thread2=new Thread(()->
                service.insert1(user2)
                ,"t2");
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(20000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void phantomReadForUpdate(){
        UserInfo user1=new UserInfo();
        user1.setUserName("张三T2");
        user1.setUserId(13);
        user1.setUpdateTime(new Date());
        user1.setCreateTime(new Date());
        UserInfo user2=new UserInfo();
        user2.setUserName("李四T2");
        user2.setUserId(13);
        user2.setUpdateTime(new Date());
        user2.setCreateTime(new Date());
        Thread thread1=new Thread(()->
                service.update4(user1)
                ,"t1");
        Thread thread2=new Thread(()->
                service.update5(user2)
                ,"t2");
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(20000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
