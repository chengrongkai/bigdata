package com.esharex.bigdata.dao;

import com.esharex.bigdata.model.UserIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

/**
 * @program: bigdata
 * @description: 用户索引Dao层
 * @author: pengxk
 * @create: 2018-07-25 11:49
 **/
public interface UserIndexRepository extends ElasticsearchRepository<UserIndex, String> {

}
