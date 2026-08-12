package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;

public interface Identifier extends SQLSyntaxElement {
    String getName();

    @Override
    default void toSQL(SQLBuilder<?> builder) {
        builder.append("`").append(getName()).append("`");
    }
}
