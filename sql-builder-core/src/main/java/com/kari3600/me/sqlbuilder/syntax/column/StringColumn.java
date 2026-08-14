package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.supplier.SQLStringSupplier;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;
import com.kari3600.me.sqlbuilder.syntax.type.StringType;

public class StringColumn extends AbstractColumn<String> implements SQLStringSupplier {
    public StringColumn(Table table, String name) {
        super(SQLType.stringType(), table, name);
    }
    @Override
    public StringType getType() {
        return SQLType.stringType();
    }
}
