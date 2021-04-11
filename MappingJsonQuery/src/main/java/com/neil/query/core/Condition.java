package com.neil.query.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {
    private String key;

    private Operator operator;

    private Object value;
}
