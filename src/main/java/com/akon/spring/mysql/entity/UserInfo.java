package com.akon.spring.mysql.entity;

import java.util.Date;

public class UserInfo {
    private Integer userId;

    private String userName;

    private Integer userAge;

    private Long userScore;

    private Double userSlary;

    private String userSex;

    private Date createTime;

    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Long getUserScore() {
        return userScore;
    }

    public void setUserScore(Long userScore) {
        this.userScore = userScore;
    }

    public Double getUserSlary() {
        return userSlary;
    }

    public void setUserSlary(Double userSlary) {
        this.userSlary = userSlary;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userScore=" + userScore +
                ", userSlary=" + userSlary +
                ", userSex='" + userSex + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}