package com.kari3600.me.sqlbuilder.syntax.type;

import com.kari3600.me.sqlbuilder.syntax.supplier.ConstantSupplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLType<T> {
    default ConstantSupplier<T> createConstant(T value) {
        return new ConstantSupplier<>(this, value);
    }

    void setValue(PreparedStatement preparedStatement, int index, T value) throws SQLException;

    T getValue(ResultSet resultSet, int index) throws SQLException;

    static BooleanType booleanType() {
        return new BooleanType();
    }

    static IntegerType integerType() {
        return new IntegerType();
    }

    static LongType longType() {
        return new LongType();
    }

    static StringType stringType() {
        return new StringType();
    }

    static TimestampType timestampType() {
        return new TimestampType();
    }
}
