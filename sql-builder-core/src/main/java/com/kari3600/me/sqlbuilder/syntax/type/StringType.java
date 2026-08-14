package com.kari3600.me.sqlbuilder.syntax.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringType implements SQLType<String> {
    @Override
    public void setValue(PreparedStatement preparedStatement, int index, String value) throws SQLException {
        preparedStatement.setString(index, value);
    }

    @Override
    public String getValue(ResultSet resultSet, int index) throws SQLException {
        return resultSet.getString(index);
    }
}
