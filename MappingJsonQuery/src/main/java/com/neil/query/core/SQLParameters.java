package com.neil.query.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SQLParameters {

    private String table;

    private String[] fields;

    private List<PredicatePart> predicateParts;
}
