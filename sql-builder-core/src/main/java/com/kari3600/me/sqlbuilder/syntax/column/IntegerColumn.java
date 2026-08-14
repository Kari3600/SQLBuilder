package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.supplier.SQLIntegerSupplier;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.IntegerType;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public class IntegerColumn extends AbstractColumn<Integer> implements SQLIntegerSupplier {
    public IntegerColumn(Table table, String name) {
        super(SQLType.integerType(), table, name);
    }
    @Override
    public IntegerType getType() {
        return SQLType.integerType();
    }
}
