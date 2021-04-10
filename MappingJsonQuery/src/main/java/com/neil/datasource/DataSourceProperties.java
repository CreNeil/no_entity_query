package com.neil.datasource;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataSourceProperties {

    private String jdbcUrl;

    private String user;

    private String password;
}
