package com.neil.query.core;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class SQLBuilder {

    private SQLParameters sqlParameters;

    public SQLBuilder() {
        sqlParameters = new SQLParameters();
    }

    public SQLBuilder select(String... fields) {
        sqlParameters.setFields(fields);
        return this;
    }

    public SQLBuilder from(String table) {
        sqlParameters.setTable(table);
        return this;
    }

    public SQLBuilder where(PredicateBuilder predicateBuilder) {
        sqlParameters.setPredicateParts(predicateBuilder.getKeyList());
        return this;
    }

}
