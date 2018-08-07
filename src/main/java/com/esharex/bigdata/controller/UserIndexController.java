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
import java.util.UUID;

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
    protected  UserIndex addUserIndex(){
        UserIndex userIndex = new UserIndex();

        return userIndexService.addUserIndex(userIndex);
    }

    /**
     * @return
     */
    @GetMapping("/search")
    @ResponseBody
    protected  String  search(String keyWords){
        Iterable<UserIndex> list=userIndexService.search(keyWords);
        JSONArray jsonArray=new JSONArray();
        Iterator<UserIndex> it=list.iterator();
        while (it.hasNext()) {
                jsonArray.add(JSON.toJSONString(it.next()));
        }
        return jsonArray.toJSONString();
    }
}
