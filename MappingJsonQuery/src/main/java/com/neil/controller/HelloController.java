package com.neil.controller;

import com.neil.datasource.DataSourceProperties;
import com.neil.event.DataSourceUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private ApplicationContext applicationContext;

    @Resource
    private Map<DataSourceProperties, JdbcTemplate> jdbcTemplateMap;

    @PostMapping("/post")
    public String getHello(@RequestBody DataSourceProperties dataSourceProperties) {
        JdbcTemplate jdbcTemplate;
        if (!jdbcTemplateMap.containsKey(dataSourceProperties)) {
            applicationContext.publishEvent(new DataSourceUpdateEvent(dataSourceProperties));
        }
        jdbcTemplate = jdbcTemplateMap.get(dataSourceProperties);

        String sql = "select count(*) from position.computer;";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(integer);
        return "Hello";
    }
}
