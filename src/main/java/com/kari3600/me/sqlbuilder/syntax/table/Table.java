package com.kari3600.me.sqlbuilder.syntax.table;

import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.column.*;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public interface Table extends SQLSyntaxElement {
    default <T> TableColumn<T> column(String name, SQLType<T> type) {
        return new AbstractColumn<>(type, this, name);
    }

    default IntegerColumn integerColumn(String name) {
        return new IntegerColumn(this, name);
    }

    default StringColumn stringColumn(String name) {
        return new StringColumn(this, name);
    }

    default BooleanColumn booleanColumn(String name) {
        return new BooleanColumn(this, name);
    }

    default TimestampColumn timestampColumn(String name) {
        return new TimestampColumn(this, name);
    }

    default LongColumn longColumn(String name) {
        return new LongColumn(this, name);
    }
}
