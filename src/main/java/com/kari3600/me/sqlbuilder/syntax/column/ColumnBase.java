package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.supplier.SupplierBase;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public abstract class ColumnBase<T> extends SupplierBase<T> implements Column<T> {
    protected final String name;

    protected ColumnBase(SQLType<T> type, String name) {
        super(type);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
