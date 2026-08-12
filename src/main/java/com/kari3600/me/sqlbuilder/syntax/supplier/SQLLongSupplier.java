package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.type.LongType;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public interface SQLLongSupplier extends SQLSupplier<Long> {
    @Override
    default LongType getType() {
        return SQLType.longType();
    }
}
