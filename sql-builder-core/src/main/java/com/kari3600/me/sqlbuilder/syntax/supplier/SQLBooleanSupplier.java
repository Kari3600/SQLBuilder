package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.type.BooleanType;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public interface SQLBooleanSupplier extends SQLSupplier<Boolean> {
    @Override
    default BooleanType getType() {
        return SQLType.booleanType();
    }
}
