package com.kari3600.me.sqlbuilder.builders.stage.update;

import com.kari3600.me.sqlbuilder.syntax.PairsElement;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;

public class UpdateBuilder extends WhereBuilder {
    public static UpdateBuilder update(PairsElement properties, DBTable table) {
        UpdateBuilder b = new UpdateBuilder();
        b.builder.append("UPDATE ").append(table).append(" SET ").append(properties);
        return b;
    }
}
