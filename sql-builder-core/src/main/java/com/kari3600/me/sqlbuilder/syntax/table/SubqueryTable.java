package com.kari3600.me.sqlbuilder.syntax.table;

import com.kari3600.me.sqlbuilder.syntax.IdentifierBase;
import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.DefinedElement;
import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;

public class SubqueryTable extends IdentifierBase implements Table, DefinedElement {
    private final SQLBuilder sqlBuilder;

    public SubqueryTable(SQLBuilder sqlBuilder, String name) {
        super(name);
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public SQLSyntaxElement toDefinition() {
        return builder -> builder.append(sqlBuilder);
    }
}
