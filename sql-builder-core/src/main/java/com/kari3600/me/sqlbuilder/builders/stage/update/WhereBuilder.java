package com.kari3600.me.sqlbuilder.builders.stage.update;

import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;

public abstract class WhereBuilder extends FinalBuilder {
    public FinalBuilder where(SQLPredicate predicate) {
        builder.append(" WHERE ").append(predicate);
        return this;
    }
}
