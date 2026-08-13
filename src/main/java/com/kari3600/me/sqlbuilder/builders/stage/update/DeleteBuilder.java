package com.kari3600.me.sqlbuilder.builders.stage.update;

import com.kari3600.me.sqlbuilder.syntax.table.DBTable;

public class DeleteBuilder extends WhereBuilder {
    public static WhereBuilder delete(DBTable table) {
        DeleteBuilder b = new DeleteBuilder();
        b.builder.append("DELETE FROM ").append(table);
        return b;
    }
}
