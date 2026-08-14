package com.kari3600.me.sqlbuilder.builders;

import com.kari3600.me.sqlbuilder.SQLExecutor;
import com.kari3600.me.sqlbuilder.result.Result;
import com.kari3600.me.sqlbuilder.syntax.ColumnSetElement;
import com.kari3600.me.sqlbuilder.syntax.PairsElement;
import com.kari3600.me.sqlbuilder.syntax.ValuesElement;
import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;
import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;
import com.kari3600.me.sqlbuilder.syntax.table.SubqueryTable;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.utils.function.ThrowingFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class StatementBuilder {
    public static UpdateWhereStage delete(DBTable table) {
        DeleteBuilder b = new DeleteBuilder();
        b.builder.append("DELETE FROM ").append(table);
        return b;
    }

    public static UpdateWhereStage update(PairsElement properties, DBTable table) {
        UpdateBuilder b = new UpdateBuilder();
        b.builder.append("UPDATE ").append(table).append(" SET ").append(properties);
        return b;
    }

    public static UpdateFinalGenerateKeysStage insert(ValuesElement properties, DBTable table) {
        InsertBuilder b = new InsertBuilder();
        b.builder.append("INSERT INTO ").append(table).append(" ").append(properties);
        return b;
    }

    public static SelectWhereStage select(ColumnSetElement set, DBTable table) {
        SelectBuilder b = new SelectBuilder();
        b.builder.append("SELECT ").append(set).append(" FROM ").append(table);
        return b;
    }

    public static SelectWhereStage selectWith(SubqueryTable subqueryTable, ColumnSetElement set) {
        return selectWith(subqueryTable, set, subqueryTable);
    }

    public static SelectWhereStage selectWith(SubqueryTable subqueryTable, ColumnSetElement set, Table table) {
        SelectBuilder b = new SelectBuilder();
        b.builder.append("WITH ").append(subqueryTable).append(" AS (").append(subqueryTable.toDefinition()).append(") SELECT ").append(set).append(" FROM ").append(table);
        return b;
    }

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

    public abstract static class LimitStage extends OffsetStage {
        public OffsetStage limit(int limit) {
            builder.append(" LIMIT ").append(String.valueOf(limit));
            return this;
        }
    }

    public abstract static class OffsetStage extends SelectFinalStage {
        public SelectFinalStage offset(int offset) {
            builder.append(" OFFSET ").append(String.valueOf(offset));
            return this;
        }
    }

    public abstract static class OrderByStage extends LimitStage {
        public LimitStage orderBy(TableColumn<?> column, boolean desc) {
            builder.append(" ORDER BY ").append(column);
            if (desc) {
                builder.append(" DESC");
            }
            return this;
        }
    }

    public static class SelectBuilder extends SelectWhereStage {

    }

    public abstract static class SelectFinalStage extends StatementBuilder {
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

    public abstract static class SelectWhereStage extends OrderByStage {
        public OrderByStage where(SQLPredicate predicate) {
            builder.append(" WHERE ").append(predicate);
            return this;
        }
    }

    public static class DeleteBuilder extends UpdateWhereStage {

    }

    public static class InsertBuilder extends UpdateFinalGenerateKeysStage {

    }

    public abstract static class UpdateFinalStage extends StatementBuilder {
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

    public static class UpdateBuilder extends UpdateWhereStage {

    }

    public abstract static class UpdateFinalGenerateKeysStage extends UpdateFinalStage {
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

    public abstract static class UpdateWhereStage extends UpdateFinalStage {
        public UpdateFinalStage where(SQLPredicate predicate) {
            builder.append(" WHERE ").append(predicate);
            return this;
        }
    }
}
