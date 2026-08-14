package com.kari3600.me.sqlbuilder.builders.stage.select;

import com.kari3600.me.sqlbuilder.SQLExecutor;
import com.kari3600.me.sqlbuilder.builders.stage.CoreBuilder;
import com.kari3600.me.sqlbuilder.result.Result;
import com.kari3600.me.sqlbuilder.syntax.table.SubqueryTable;
import com.kari3600.me.utils.function.ThrowingFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public abstract class FinalBuilder extends CoreBuilder {
    public <T> T execute(Connection connection, ThrowingFunction<Result, T, SQLException> handler) throws SQLException {
        try (PreparedStatement statement = build(connection)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return handler.apply(new Result(resultSet));
            }
        }
    }

    public <T> CompletableFuture<T> executeAsync(SQLExecutor executor, ThrowingFunction<Result, T, SQLException> handler) {
        return executor.call(conn -> {
            return execute(conn, handler);
        });
    }

    public SubqueryTable toTable(String name) {
        return new SubqueryTable(builder, name);
    }
}
