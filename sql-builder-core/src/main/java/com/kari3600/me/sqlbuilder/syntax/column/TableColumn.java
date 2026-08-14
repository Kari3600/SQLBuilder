package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.table.Table;

public interface TableColumn<T> extends Column<T> {
    Table getTable();
}
