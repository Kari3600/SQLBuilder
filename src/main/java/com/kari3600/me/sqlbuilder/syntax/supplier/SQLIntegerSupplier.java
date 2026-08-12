package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.type.IntegerType;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public interface SQLIntegerSupplier extends SQLNumberSupplier<Integer> {
    @Override
    default IntegerType getType() {
        return SQLType.integerType();
    }
}
