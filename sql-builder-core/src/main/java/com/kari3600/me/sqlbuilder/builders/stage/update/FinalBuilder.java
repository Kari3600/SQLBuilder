package com.kari3600.me.sqlbuilder.builders.stage.update;

import com.kari3600.me.sqlbuilder.SQLExecutor;
import com.kari3600.me.sqlbuilder.builders.stage.CoreBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public abstract class FinalBuilder extends CoreBuilder {
    public int execute(Connection connection) throws SQLException {
        try (PreparedStatement statement = build(connection)) {
            return statement.executeUpdate();
        }
    }

    public CompletableFuture<Integer> executeAsync(SQLExecutor executor) {
        return executor.call(conn -> {
            return execute(conn);
        });
    }
}
