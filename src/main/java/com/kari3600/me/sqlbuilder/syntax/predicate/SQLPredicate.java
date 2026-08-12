package com.kari3600.me.sqlbuilder.syntax.predicate;

import com.kari3600.me.sqlbuilder.syntax.supplier.SQLBooleanSupplier;

public interface SQLPredicate extends SQLBooleanSupplier {
    default SQLPredicate and(SQLPredicate element) {
        return builder -> {
            builder.append(this).append(" AND ").append(element);
        };
    }

    default SQLPredicate or(SQLPredicate element) {
        return builder -> {
            builder.append(this).append(" OR ").append(element);
        };
    }
}
