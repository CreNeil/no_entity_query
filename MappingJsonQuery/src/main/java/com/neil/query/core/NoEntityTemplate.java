package com.neil.query.core;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class NoEntityTemplate extends JdbcTemplate {

    public static void main(String[] args) {
        String[] a = new String[]{"a", "b", "c"};
        String sql = "";
        for (String item : a) {
            sql = String.join(",", sql, item);
        }
        sql = sql.replaceFirst(",", "");
        System.out.println(sql);
    }

    public List<Map<String, Object>> queryByNoEntityForList(SQLBuilder sqlBuilder) throws DataAccessException {
        String sql = combine(sqlBuilder);
        if (!CollectionUtils.isEmpty(sqlBuilder.getSqlParameters().getPredicateParts())) {
            Object[] args = new Object[sqlBuilder.getSqlParameters().getPredicateParts().size()];
            for (int i = 0; i < args.length; i++) {
                args[i] = sqlBuilder.getSqlParameters().getPredicateParts().get(i).getValue();
            }
            return queryForList(sql, args);
        }

        return queryForList(sql);
    }

    private String combine(SQLBuilder sqlBuilder) {
        StringBuilder sql = new StringBuilder();
        SQLParameters sqlParameters = sqlBuilder.getSqlParameters();
        String selectFields = "";
        for (String item : sqlParameters.getFields()) {
            selectFields = String.join(",", selectFields, item);
        }
        selectFields = selectFields.replaceFirst(",", "");
        sql.append("select ").append(selectFields).append(" from ").append(sqlParameters.getTable()).append(" ");
        if (!CollectionUtils.isEmpty(sqlParameters.getPredicateParts())) {
            sql.append(" where ");
            List<PredicatePart> predicateParts = sqlParameters.getPredicateParts();
            String condition = "";
            condition = String.join(" ", predicateParts.get(0).getPredicate());

            for (int i = 1; i < predicateParts.size(); i++) {
                condition = String.join(" ", predicateParts.get(i).getType(), predicateParts.get(i).getPredicate());
            }
            sql.append(condition);
        }
        return sql.toString();
    }
}
