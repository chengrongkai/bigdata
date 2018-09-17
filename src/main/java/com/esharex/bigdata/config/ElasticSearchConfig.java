package com.esharex.bigdata.config;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @program: bigdata
 * @description: es  配置类
 * @author: pengxk
 * @create: 2018-08-20 10:25
 **/
public class ElasticSearchConfig {
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }
}
