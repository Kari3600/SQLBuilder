package com.kari3600.me.sqlbuilder.builders;

import com.kari3600.me.sqlbuilder.SQLExecutor;
import com.kari3600.me.sqlbuilder.syntax.PairsElement;
import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.ValuesElement;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;
import com.kari3600.me.sqlbuilder.result.Result;
import com.kari3600.me.utils.function.ThrowingFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class UpdateBuilder extends StatementBuilder<UpdateBuilder> {
    @Override
    public UpdateBuilder append(String sql) {
        super.append(sql);
        return this;
    }

    @Override
    public UpdateBuilder append(SQLSyntaxElement syntaxElement) {
        super.append(syntaxElement);
        return this;
    }

    public static UpdateBuilder insert(ValuesElement properties, DBTable table) {
        return new UpdateBuilder().append("INSERT INTO ").append(table).append(" ").append(properties);
    }

    public static UpdateBuilder update(PairsElement properties, DBTable table) {
        return new UpdateBuilder().append("UPDATE ").append(table).append(" SET ").append(properties);
    }

    public static UpdateBuilder delete(DBTable table) {
        return new UpdateBuilder().append("DELETE FROM ").append(table);
    }

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

    public PreparedStatement buildWithReturnGeneratedKeys(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(b.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
        setVariables(statement);
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
