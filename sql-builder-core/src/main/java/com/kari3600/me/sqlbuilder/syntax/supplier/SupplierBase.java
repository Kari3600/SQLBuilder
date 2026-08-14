package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public abstract class SupplierBase<T> implements SQLSupplier<T> {
    protected final SQLType<T> type;

    protected SupplierBase(SQLType<T> type) {
        this.type = type;
    }

    @Override
    public SQLType<T> getType() {
        return type;
    }
}
