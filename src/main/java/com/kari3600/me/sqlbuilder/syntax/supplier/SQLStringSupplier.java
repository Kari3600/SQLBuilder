package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.type.SQLType;
import com.kari3600.me.sqlbuilder.syntax.type.StringType;

public interface SQLStringSupplier extends SQLSupplier<String> {
    @Override
    default StringType getType() {
        return SQLType.stringType();
    }
}
