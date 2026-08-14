package com.kari3600.me.sqlbuilder.builders.stage.select;

import com.kari3600.me.sqlbuilder.syntax.ColumnSetElement;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;
import com.kari3600.me.sqlbuilder.syntax.table.SubqueryTable;
import com.kari3600.me.sqlbuilder.syntax.table.Table;

public class SelectBuilder extends WhereBuilder {
    public static WhereBuilder select(ColumnSetElement set, DBTable table) {
        SelectBuilder b = new SelectBuilder();
        b.builder.append("SELECT ").append(set).append(" FROM ").append(table);
        return b;
    }

    public static WhereBuilder selectWith(SubqueryTable subqueryTable, ColumnSetElement set) {
        return selectWith(subqueryTable, set, subqueryTable);
    }

    public static WhereBuilder selectWith(SubqueryTable subqueryTable, ColumnSetElement set, Table table) {
        SelectBuilder b = new SelectBuilder();
        b.builder.append("WITH ").append(subqueryTable).append(" AS (").append(subqueryTable.toDefinition()).append(") SELECT ").append(set).append(" FROM ").append(table);
        return b;
    }
}
