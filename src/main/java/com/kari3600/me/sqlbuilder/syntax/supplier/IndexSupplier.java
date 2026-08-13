package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;

public class IndexSupplier implements SQLLongSupplier {
    private final TableColumn<?> column;
    private final boolean desc;

    public IndexSupplier(TableColumn<?> column, boolean desc) {
        this.column = column;
        this.desc = desc;
    }

    @Override
    public void toSQL(SQLBuilder builder) {
        builder.append("ROW_NUMBER() OVER (ORDER BY ").append(column).append(desc?" DESC":"").append(")");
    }
}
