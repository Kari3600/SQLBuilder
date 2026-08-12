package com.kari3600.me.sqlbuilder.syntax.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class IntegerType implements SQLType<Integer> {
    @Override
    public void setValue(PreparedStatement preparedStatement, int index, Integer value) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.INTEGER);
        } else {
            preparedStatement.setInt(index, value);
        }
    }

    @Override
    public Integer getValue(ResultSet resultSet, int index) throws SQLException {
        int value = resultSet.getInt(index);
        return resultSet.wasNull() ? null : value;
    }
}
