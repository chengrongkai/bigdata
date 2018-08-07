package com.esharex.bigdata.dao;

import com.esharex.bigdata.model.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: bigdata
 * @description: 用户信息数据库操作类
 * @author: pengxk
 * @create: 2018-07-26 09:55
 **/
public interface UserInfoRepository    extends JpaRepository<DeviceInfo, Long> {
}
