package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.supplier.SQLTimestampSupplier;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;
import com.kari3600.me.sqlbuilder.syntax.type.TimestampType;

import java.sql.Timestamp;

public class TimestampColumn extends AbstractColumn<Timestamp> implements SQLTimestampSupplier {
    public TimestampColumn(Table table, String name) {
        super(SQLType.timestampType(), table, name);
    }
    @Override
    public TimestampType getType() {
        return SQLType.timestampType();
    }
}
