package com.esharex.bigdata.service;

import com.esharex.bigdata.dao.UserInfoRepository;
import com.esharex.bigdata.model.DeviceInfo;
import com.esharex.bigdata.model.UserIndex;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: bigdata
 * @description: 用户索引服务
 * @author: pengxk
 * @create: 2018-07-25 11:01
 **/
@Service
public class UserInfoService {
    private final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    DataSource dataSource;
    @Autowired
    UserIndexService userIndexService;

    public boolean addUserIndex(String tableName) {
        Thread thread2 = new Thread(() -> {
            int count = 0;
            //添加所以线程
            try {
                QueryRunner run = new QueryRunner(dataSource);
                //查询表中数据总数
                String sql = "select count(*)  from " + tableName ;
                Object obj = run.query(sql, new ScalarHandler());
                int i = Integer.valueOf(obj.toString());
                logger.info("表中数据总量为：" + i);
                //分页处理表中数据
                int startNum = 0;
                int limit = 1000;
                int currentPag = 1;
                int pageCount = i % limit == 0 ? i / limit : i / limit + 1;
                List<UserIndex> indexList = new ArrayList<>();
                while (currentPag <= pageCount) {
                    try {
                        Long time = System.currentTimeMillis();
                        String listSql = "select * from " + tableName + "   limit " + (currentPag - 1) * limit + "," + limit;
                        List<DeviceInfo> list = run.query(listSql, new BeanListHandler<DeviceInfo>(DeviceInfo.class));
                        list.forEach(deviceInfo -> {
                            UserIndex index = new UserIndex();
                            index.setId(deviceInfo.getDeviceid());
                            index.setImei(deviceInfo.getImei());
                            if (deviceInfo.getPhonenumber() != null && !deviceInfo.getPhonenumber().equals("undefined")) {
                                index.setPhonenumber(deviceInfo.getPhonenumber());
                            }
                            if (deviceInfo.getSimtype() != null && !deviceInfo.getSimtype().equals("undefined")) {
                                index.setSimtype(deviceInfo.getSimtype());
                            }
                            index.setAndroidid(deviceInfo.getAndroidid());
                            if (deviceInfo.getBtmac().contains("%")) {
                                index.setBtmac(URLDecoder.decode(deviceInfo.getBtmac()));
                            } else {
                                index.setBtmac(deviceInfo.getBtmac());
                            }
                            if (deviceInfo.getWlanmac().contains("%")) {
                                index.setWlanmac(URLDecoder.decode(deviceInfo.getWlanmac()));
                            } else {
                                index.setWlanmac(deviceInfo.getWlanmac());
                            }
                            index.setIp(deviceInfo.getIp());
                            index.setDevicetype(deviceInfo.getDevicetype());
                            index.setDevidshort(deviceInfo.getDevidshort());
                            index.setCreate_time(deviceInfo.getCreate_time());
                            index.setUpdate_time(deviceInfo.getUpdate_time());
                            indexList.add(index);
                        });
                        boolean res = userIndexService.batchInserUserIndex(indexList);
                        if (res) {
                            logger.info("批量添加用户索引成功");
                        } else {
                            logger.error("批量添加用户索引失败");
                        }
                        currentPag++;
                        count += indexList.size();
                        logger.info(tableName + "批量添加用户索引：" + indexList.size() + "条，耗时" + (System.currentTimeMillis() - time) / 1000 + "秒");
                        logger.info(tableName + "总共导入：" + count + "条");
                        indexList.clear();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }, "thread2+++++" + tableName);
        thread2.start();
        return true;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "18%3A59%3A36%3A63%3A92%3A7D";
        System.out.print(s.contains("%"));
    }
}
