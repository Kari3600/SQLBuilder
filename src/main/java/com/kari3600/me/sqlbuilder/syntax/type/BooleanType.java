package com.kari3600.me.sqlbuilder.syntax.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BooleanType implements SQLType<Boolean> {
    @Override
    public void setValue(PreparedStatement preparedStatement, int index, Boolean value) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.BOOLEAN);
        } else {
            preparedStatement.setBoolean(index, value);
        }
    }

    @Override
    public Boolean getValue(ResultSet resultSet, int index) throws SQLException {
        boolean value = resultSet.getBoolean(index);
        return resultSet.wasNull() ? null : value;
    }
}
