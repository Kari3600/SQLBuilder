package com.kari3600.me.sqlbuilder.builders.stage;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class CoreBuilder {
    protected final SQLBuilder builder = new SQLBuilder();

    @Override
    public String toString() {
        return builder.toString();
    }

    public PreparedStatement build(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(builder.toString());
        builder.setVariables(statement);
        return statement;
    }
}
