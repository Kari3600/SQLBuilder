package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;

public interface SQLSyntaxElement {
    void toSQL(SQLBuilder<?> builder);
}
