package com.kari3600.me.sqlbuilder.syntax.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class LongType implements SQLType<Long> {
    @Override
    public void setValue(PreparedStatement preparedStatement, int index, Long value) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.BIGINT);
        } else {
            preparedStatement.setLong(index, value);
        }
    }

    @Override
    public Long getValue(ResultSet resultSet, int index) throws SQLException {
        long value = resultSet.getLong(index);
        return resultSet.wasNull() ? null : value;
    }
}
