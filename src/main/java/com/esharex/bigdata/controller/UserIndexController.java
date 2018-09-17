package com.esharex.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.esharex.bigdata.model.UserIndex;
import com.esharex.bigdata.service.UserIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

/**
 * @program: bigdata
 * @description: 用户索引
 * @author: pengxk
 * @create: 2018-07-25 10:59
 **/
@Controller
@RequestMapping("/userIndex")
public class UserIndexController {
    @Autowired
    UserIndexService userIndexService;

    @GetMapping("/add")
    @ResponseBody
    protected UserIndex addUserIndex() {
        UserIndex userIndex = new UserIndex();
        String jstr = "{\"id\":\"11e4561aef081e0bd6cd28ba428e5065\",\"imei\":\"test\",\"devidshort\":\"357416916426248\",\"androidid\":\"3106435043d8724c\",\"wlanmac\":\"e0:dd:c0:5b:d3:c8\",\"btmac\":\"E0:DD:C0:5B:D3:C7\",\"phonenumber\":null,\"simtype\":null,\"devicetype\":\"vivo+V3Max+A\",\"ip\":\"116.7.218.216\",\"create_time\":\"2017-11-01 00:23:08.0\",\"update_time\":\"2017-11-01 02:37:08.0\"}";
        userIndex = JSON.parseObject(jstr, UserIndex.class);
        return userIndexService.addUserIndex(userIndex);
    }

    /**
     * @return
     */
    @GetMapping("/search")
    @ResponseBody
    protected JSONArray search(String keyWords) {
        Iterable<UserIndex> list = userIndexService.search(keyWords);
        JSONArray jsonArray = new JSONArray();
        Iterator<UserIndex> it = list.iterator();
        while (it.hasNext()) {
            jsonArray.add(it.next());
        }
        return jsonArray;
    }

    /**
     * @return
     */
    @GetMapping("/updateByDeviceId")
    @ResponseBody
    protected List<UserIndex> updateByDeviceId(String deviceId) {
        return userIndexService.updateByDeviceId(deviceId);
    }


    /**
     * @return
     */
    @GetMapping("/findByScroll")
    @ResponseBody
    protected void updateByDeviceId() {
        userIndexService.findByScroll();
    }

}
