package com.neil.event;

import com.neil.datasource.DataSourceProperties;
import com.neil.query.core.NoEntityTemplate;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class DataSourceRegisterListener implements ApplicationListener<DataSourceRegisterEvent> {

    @Resource
    private Map<DataSourceProperties, NoEntityTemplate> jdbcTemplateMap;

    /***
     * refresh datasource config data
     */
    @Override
    public void onApplicationEvent(DataSourceRegisterEvent dataSourceRegisterEvent) {
        DataSourceProperties dataSourceProperties = (DataSourceProperties) dataSourceRegisterEvent.getSource();
        NoEntityTemplate jdbcTemplate = new NoEntityTemplate();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceProperties.getJdbcUrl());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setUsername(dataSourceProperties.getUser());
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplateMap.put(dataSourceProperties, jdbcTemplate);
    }

}
