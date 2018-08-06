package com.esharex.bigdata.model;

import lombok.Data;
import javax.persistence.Id;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @program: bigdata
 * @description: 用户基础信息
 * @author: pengxk
 * @create: 2018-07-26 10:09
 **/
@Data
@Entity
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    /**
     * 手机imei
     */
    private String imei;
    /**
     * 电话号码
     */
    private String bind_phone;
    /**
     * QQ号
     */
    private String qq;
    /**
     * 微信号
     */
    private String webChat;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 账号
     */
    private String acount;
    /**
     * 来源平台
     */
    private String servername;
}
