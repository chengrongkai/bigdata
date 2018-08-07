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
public class DeviceInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String deviceid ;
    private String imei ;
    private String devidshort;
    private String androidid ;
    private String wlanmac;
    private String btmac ;
    private String phonenumber;
    private String simtype;
    private String devicetype ;
    private String ip;
    private String create_time;
    private String update_time;
}
