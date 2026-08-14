package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.SQLVariable;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConstantSupplier<T> extends SupplierBase<T> implements SQLVariable<T> {
    protected final T value;

    public ConstantSupplier(SQLType<T> type, T value) {
        super(type);
        this.value = value;
    }

    @Override
    public void writeValue(PreparedStatement statement, int index) throws SQLException {
        type.setValue(statement, index, value);
    }
}
