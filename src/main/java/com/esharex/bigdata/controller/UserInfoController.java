package com.esharex.bigdata.controller;

import com.esharex.bigdata.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: bigdata
 * @description: 用户索引
 * @author: pengxk
 * @create: 2018-07-25 10:59
 **/
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping("/add")
    @ResponseBody
    protected  Boolean addUserIndex(String tableName){
        return userInfoService.addUserIndex(tableName);
    }

}
