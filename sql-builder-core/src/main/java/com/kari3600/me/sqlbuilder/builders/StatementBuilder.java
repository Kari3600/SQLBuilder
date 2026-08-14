package com.kari3600.me.sqlbuilder.builders;

import com.kari3600.me.sqlbuilder.builders.stage.select.SelectBuilder;
import com.kari3600.me.sqlbuilder.builders.stage.update.*;
import com.kari3600.me.sqlbuilder.syntax.ColumnSetElement;
import com.kari3600.me.sqlbuilder.syntax.PairsElement;
import com.kari3600.me.sqlbuilder.syntax.ValuesElement;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;
import com.kari3600.me.sqlbuilder.syntax.table.SubqueryTable;
import com.kari3600.me.sqlbuilder.syntax.table.Table;

public class StatementBuilder {
    public static WhereBuilder delete(DBTable table) {
        return DeleteBuilder.delete(table);
    }

    public static WhereBuilder update(PairsElement properties, DBTable table) {
        return UpdateBuilder.update(properties, table);
    }

    public static FinalGenerateKeysBuilder insert(ValuesElement values, DBTable table) {
        return InsertBuilder.insert(values, table);
    }

    public static com.kari3600.me.sqlbuilder.builders.stage.select.WhereBuilder select(ColumnSetElement columns, DBTable table) {
        return SelectBuilder.select(columns, table);
    }

    public static com.kari3600.me.sqlbuilder.builders.stage.select.WhereBuilder selectWith(SubqueryTable subqueryTable, ColumnSetElement set) {
        return SelectBuilder.selectWith(subqueryTable, set);
    }

    public static com.kari3600.me.sqlbuilder.builders.stage.select.WhereBuilder selectWith(SubqueryTable subqueryTable, ColumnSetElement set, Table table) {
        return SelectBuilder.selectWith(subqueryTable, set, table);
    }
}
