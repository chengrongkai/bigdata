package com.esharex.bigdata.service;

import com.esharex.bigdata.dao.UserIndexRepository;
import com.esharex.bigdata.model.UserIndex;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: bigdata
 * @description: 用户索引服务
 * @author: pengxk
 * @create: 2018-07-25 11:01
 **/
@Service
@Scope("prototype")
public class UserIndexService {

    @Autowired
    UserIndexRepository userIndexRepository;
    public UserIndex addUserIndex(UserIndex userIndex){
        return userIndexRepository.save(userIndex);
    }
    public Iterable<UserIndex> search(String keyWords){
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(keyWords);
        Pageable pageable = PageRequest.of(0, 50);
        return userIndexRepository.search(builder,pageable);
    }
    public Boolean batchInserUserIndex(List<UserIndex> indexList){
        try {
            userIndexRepository.saveAll(indexList);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }
}
