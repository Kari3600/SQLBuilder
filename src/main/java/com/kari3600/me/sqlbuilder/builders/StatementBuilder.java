package com.kari3600.me.sqlbuilder.builders;

import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;

public abstract class StatementBuilder<T extends StatementBuilder<T>> extends SQLBuilder<T> {

    @SuppressWarnings("unchecked")
    public T where(SQLPredicate predicate) {
        append(" WHERE ").append(predicate);
        return (T) this;
    }
}
