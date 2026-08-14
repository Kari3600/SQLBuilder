package com.kari3600.me.sqlbuilder.builders.stage.update;

import com.kari3600.me.sqlbuilder.SQLExecutor;
import com.kari3600.me.sqlbuilder.result.Result;
import com.kari3600.me.utils.function.ThrowingFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public abstract class FinalGenerateKeysBuilder extends FinalBuilder {
    public PreparedStatement buildWithReturnGeneratedKeys(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
        builder.setVariables(statement);
        return statement;
    }

    public <T> T executeGetGeneratedKeys(Connection connection, ThrowingFunction<Result, T, SQLException> handler) throws SQLException {
        try (PreparedStatement statement = buildWithReturnGeneratedKeys(connection)) {
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                return handler.apply(new Result(rs));
            }
        }
    }

    public <T> CompletableFuture<T> executeGetGeneratedKeysAsync(SQLExecutor executor, ThrowingFunction<Result, T, SQLException> handler) {
        return executor.call(conn -> {
            return executeGetGeneratedKeys(conn, handler);
        });
    }
}
