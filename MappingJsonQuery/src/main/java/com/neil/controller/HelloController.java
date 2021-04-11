package com.neil.controller;

import com.neil.datasource.DataSourceProperties;
import com.neil.event.DataSourceRegisterEvent;
import com.neil.query.QueryParameters;
import com.neil.query.core.Condition;
import com.neil.query.core.NoEntityTemplate;
import com.neil.query.core.PredicateBuilder;
import com.neil.query.core.SQLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private ApplicationContext applicationContext;

    @Resource
    private Map<DataSourceProperties, NoEntityTemplate> jdbcTemplateMap;

    @PostMapping("/post")
    public List<Map<String, Object>> getHello(@RequestBody QueryParameters queryParameters) {
        NoEntityTemplate jdbcTemplate;
        if (!jdbcTemplateMap.containsKey(queryParameters.getDataSourceProperties())) {
            applicationContext.publishEvent(new DataSourceRegisterEvent(queryParameters.getDataSourceProperties()));
        }
        jdbcTemplate = jdbcTemplateMap.get(queryParameters.getDataSourceProperties());
        PredicateBuilder predicateBuilder = new PredicateBuilder();
        for (Condition andItem : queryParameters.getAndCondition()) {
            predicateBuilder.and(andItem);
        }
        for (Condition andItem : queryParameters.getOrCondition()) {
            predicateBuilder.or(andItem);
        }

        SQLBuilder sqlBuilder = new SQLBuilder();
        sqlBuilder.select(queryParameters.getFields()).from(queryParameters.getTable()).where(predicateBuilder);

        List<Map<String, Object>> list = jdbcTemplate.queryByNoEntityForList(sqlBuilder);
        return list;
    }


}
