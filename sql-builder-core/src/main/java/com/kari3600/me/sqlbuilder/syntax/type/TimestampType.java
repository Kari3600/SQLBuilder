package com.kari3600.me.sqlbuilder.syntax.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TimestampType implements SQLType<Timestamp> {
    @Override
    public void setValue(PreparedStatement preparedStatement, int index, Timestamp value) throws SQLException {
        preparedStatement.setTimestamp(index, value);
    }

    @Override
    public Timestamp getValue(ResultSet resultSet, int index) throws SQLException {
        return null;
    }
}
