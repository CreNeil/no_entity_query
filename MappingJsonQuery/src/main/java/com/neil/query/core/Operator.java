package com.neil.query.core;

import lombok.Getter;

@Getter
public enum Operator {
    EQ("="),
    NE("!="),
    GT(">"),
    LT("<"),
    LIKE("LIKE");

    private String ops;

    Operator(String ops) {
        this.ops = ops;
    }
}
