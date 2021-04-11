package com.neil.config;

import com.neil.datasource.DataSourceProperties;
import com.neil.query.core.NoEntityTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class BaseConfig {

    @Bean
    public Map<DataSourceProperties, NoEntityTemplate> jdbcTemplateMap() {
        return new ConcurrentHashMap<>();
    }
}
