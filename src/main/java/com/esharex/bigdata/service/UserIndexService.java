package com.esharex.bigdata.service;

import com.alibaba.fastjson.JSON;
import com.esharex.bigdata.dao.UserIndexRepository;
import com.esharex.bigdata.model.UserIndex;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public UserIndex addUserIndex(UserIndex userIndex) {
        return userIndexRepository.save(userIndex);
    }

    public Iterable<UserIndex> search(String keyWords) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(keyWords);
        Pageable pageable = PageRequest.of(0, 50);
        return userIndexRepository.search(builder, pageable);
    }

    public Boolean batchInserUserIndex(List<UserIndex> indexList) {
        try {
            userIndexRepository.saveAll(indexList);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    /**
     * @return
     */
    public List<UserIndex> updateByDeviceId(String deviceId) {
        //"devidshort":"357416916426248"
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(new WildcardQueryBuilder("phonenumber", "*" + deviceId + "*")).build();
        List<UserIndex> userIndexList = elasticsearchTemplate.queryForList(searchQuery, UserIndex.class);
//        if(userIndexList.size()>0){
//            UserIndex userIndex=userIndexList.get(0);
//            if(StringUtils.isNotBlank(userIndex.getPhonenumber())){
//                userIndex.setPhonenumber(userIndex.getPhonenumber()+",18628201584");
//            }else {
//                userIndex.setPhonenumber("18280456120");
//            }
//            userIndexRepository.save(userIndex);
//        }
        return userIndexList;
    }

    /**
     * @return
     */
    File outputFile = null;
    Writer output = null;
    public void findByScroll() {
        try {
            outputFile = new File("d:\\es.txt");
            output = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(new MatchAllQueryBuilder()).build();
        searchQuery.setPageable(new PageRequest(0, 1000));
        AggregatedPage<UserIndex> page=(AggregatedPage)elasticsearchTemplate.startScroll(5*3600,searchQuery,UserIndex.class);
        int i=1;
        page.getContent().forEach(userIndex -> {
           System.out.println(JSON.toJSONString(userIndex));
            try {
                output.write(JSON.toJSONString(userIndex)+"\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            Page<UserIndex> page1=elasticsearchTemplate.continueScroll(page.getScrollId(),5*3600L,UserIndex.class);
            if(page1.getContent()==null||page1.getContent().size()<1){
                break;
            }else{
                i++;
                System.out.println("第"+i+"页");
                page1.getContent().forEach(userIndex -> {
                    System.out.println(JSON.toJSONString(userIndex));
                    try {
                        output.write(JSON.toJSONString(userIndex)+"\r\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                try {
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
