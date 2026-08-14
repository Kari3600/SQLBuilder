package com.kari3600.me.sqlbuilder.builders.stage.update;

import com.kari3600.me.sqlbuilder.syntax.ValuesElement;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;

public class InsertBuilder extends FinalGenerateKeysBuilder {
    public static FinalGenerateKeysBuilder insert(ValuesElement properties, DBTable table) {
        InsertBuilder b = new InsertBuilder();
        b.builder.append("INSERT INTO ").append(table).append(" ").append(properties);
        return b;
    }
}
