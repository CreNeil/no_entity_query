package com.neil.event;

import com.neil.datasource.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class DataSourceUpdateListener implements ApplicationListener<DataSourceUpdateEvent> {

    @Resource
    private Map<DataSourceProperties, JdbcTemplate> jdbcTemplateMap;

    /***
     * refresh datasource config data
     */
    @Override
    public void onApplicationEvent(DataSourceUpdateEvent dataSourceUpdateEvent) {
        DataSourceProperties dataSourceProperties = (DataSourceProperties) dataSourceUpdateEvent.getSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceProperties.getJdbcUrl());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setUsername(dataSourceProperties.getUser());
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplateMap.put(dataSourceProperties, jdbcTemplate);
    }

}
