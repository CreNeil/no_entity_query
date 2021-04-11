package com.neil.query.core;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PredicateBuilder {

    private final List<PredicatePart> keyList = new ArrayList<>();

    public PredicateBuilder() {
    }

    public PredicateBuilder and(Condition condition) {
        PredicatePart and = new PredicatePart();
        and.setPredicate(String.join(" ", condition.getKey(), condition.getOperator().getOps(), "?"));
        and.setValue(condition.getValue());
        and.setType("and");
        keyList.add(0, and);
        return this;
    }

    public PredicateBuilder or(Condition condition) {
        PredicatePart or = new PredicatePart();
        or.setPredicate(String.join(" ", condition.getKey(), condition.getOperator().getOps(), "?"));
        or.setValue(condition.getValue());
        or.setType("or");
        keyList.add(or);
        return this;
    }
}
