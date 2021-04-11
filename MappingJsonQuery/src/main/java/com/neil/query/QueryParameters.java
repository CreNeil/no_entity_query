package com.neil.query;

import com.neil.datasource.DataSourceProperties;
import com.neil.query.core.Condition;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class QueryParameters {
    private DataSourceProperties dataSourceProperties;

    private Set<Condition> andCondition;

    private Set<Condition> orCondition;

    private String table;

    private String[] fields;
}
