package com.kari3600.me.sqlbuilder.builders.stage.select;

import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;

public abstract class WhereBuilder extends OrderByBuilder {
    public OrderByBuilder where(SQLPredicate predicate) {
        builder.append(" WHERE ").append(predicate);
        return this;
    }
}
