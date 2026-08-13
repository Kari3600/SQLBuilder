package com.kari3600.me.sqlbuilder.syntax.predicate;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;

public abstract class ConstantPredicate implements SQLPredicate {
    public static final ConstantPredicate TRUE = new ConstantPredicate() {
        @Override
        public void toSQL(SQLBuilder builder) {
            builder.append("1=1");
        }
    };
    public static final ConstantPredicate FALSE = new ConstantPredicate() {
        @Override
        public void toSQL(SQLBuilder builder) {
            builder.append("1=0");
        }
    };

    public static ConstantPredicate of(boolean value) {
        return value ? TRUE : FALSE;
    }
}
