package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.supplier.SQLSupplier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLVariable<T> extends SQLSupplier<T> {
    @Override
    default void toSQL(SQLBuilder<?> builder) {
        builder.append("?");
    }
    void writeValue(PreparedStatement statement, int index) throws SQLException;
}
