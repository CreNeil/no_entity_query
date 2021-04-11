package com.neil.query.core;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PredicatePart {
    private String type;
    private String predicate;
    private Object value;
}
