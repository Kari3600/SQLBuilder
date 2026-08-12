package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.supplier.SQLBooleanSupplier;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.BooleanType;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public class BooleanColumn extends AbstractColumn<Boolean> implements SQLBooleanSupplier {
    public BooleanColumn(Table table, String name) {
        super(SQLType.booleanType(), table, name);
    }
    @Override
    public BooleanType getType() {
        return SQLType.booleanType();
    }
}
