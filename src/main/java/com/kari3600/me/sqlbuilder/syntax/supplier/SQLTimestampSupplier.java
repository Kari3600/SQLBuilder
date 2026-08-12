package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;
import com.kari3600.me.sqlbuilder.syntax.type.TimestampType;

import java.sql.Timestamp;

public interface SQLTimestampSupplier extends SQLSupplier<Timestamp> {
    @Override
    default TimestampType getType() {
        return SQLType.timestampType();
    }

    default SQLPredicate isAfter(Timestamp value) {
        return isAfter(getType().createConstant(value));
    }

    default SQLPredicate isAfter(SQLSupplier<Timestamp> other) {
        return builder -> builder.append(SQLTimestampSupplier.this).append(" > ").append(other);
    }
}
