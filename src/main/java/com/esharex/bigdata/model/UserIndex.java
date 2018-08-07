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
@Document(indexName="userindex",type="deivice",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class UserIndex implements Serializable {
    /**
     * 设备id （忆享内部编码） 对应游戏业务库中的deviceid
     */
    @Id
    private String id;
    /**
     * 手机imei
     */
    private String imei;
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
