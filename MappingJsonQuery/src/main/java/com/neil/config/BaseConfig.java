package com.neil.config;

import com.neil.datasource.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class BaseConfig {

    @Bean
    public Map<DataSourceProperties, JdbcTemplate> jdbcTemplateMap() {
        return new ConcurrentHashMap<>();
    }
}
