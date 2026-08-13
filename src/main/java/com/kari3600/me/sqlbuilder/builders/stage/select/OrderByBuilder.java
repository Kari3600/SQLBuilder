package com.kari3600.me.sqlbuilder.builders.stage.select;

import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;

public abstract class OrderByBuilder extends LimitBuilder {
    public LimitBuilder orderBy(TableColumn<?> column, boolean desc) {
        builder.append(" ORDER BY ").append(column);
        if (desc) {
            builder.append(" DESC");
        }
        return this;
    }
}
