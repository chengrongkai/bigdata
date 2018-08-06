package com.esharex.bigdata.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.UUID;

/**
 * @program: bigdata
 * @description: 用户索引信息
 * @author: pengxk
 * @create: 2018-07-25 11:02
 **/
@Data
@Document(indexName="userinfo",type="userIndex",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class UserIndex implements Serializable {
    @Id
    private UUID id;
    /**
     * 手机imei
     */
    private String imei;
    /**
     * 电话号码
     */
    private String phone;
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
    private String platForm;
}
