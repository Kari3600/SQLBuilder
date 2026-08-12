package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.supplier.SQLLongSupplier;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.LongType;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public class LongColumn extends AbstractColumn<Long> implements SQLLongSupplier {
    public LongColumn(Table table, String name) {
        super(SQLType.longType(), table, name);
    }
    @Override
    public LongType getType() {
        return SQLType.longType();
    }
}
