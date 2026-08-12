package com.kari3600.me.sqlbuilder.builders;

import com.kari3600.me.sqlbuilder.IdentifierBase;
import com.kari3600.me.sqlbuilder.SQLExecutor;
import com.kari3600.me.sqlbuilder.syntax.DefinedElement;
import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.ColumnSetElement;
import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;
import com.kari3600.me.sqlbuilder.result.Result;
import com.kari3600.me.utils.function.ThrowingFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class QueryBuilder extends StatementBuilder<QueryBuilder> {
    public static QueryBuilder select(ColumnSetElement set, DBTable table) {
        return new QueryBuilder().append("SELECT ").append(set).append(" FROM ").append(table);
    }

    public static QueryBuilder selectWith(SubqueryTable subqueryTable, ColumnSetElement set) {
        return selectWith(subqueryTable, set, subqueryTable);
    }

    public static QueryBuilder selectWith(SubqueryTable subqueryTable, ColumnSetElement set, Table table) {
        return new QueryBuilder().append("WITH ").append(subqueryTable).append(" AS (").append(subqueryTable.toDefinition()).append(") SELECT ").append(set).append(" FROM ").append(table);
    }

    public QueryBuilder order(TableColumn<?> column, boolean desc) {
        append(" ORDER BY ").append(column);
        if (desc) {
            append(" DESC");
        }
        return this;
    }

    public QueryBuilder limit(int limit) {
        append(" LIMIT ").append(String.valueOf(limit));
        return this;
    }

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
        return new SubqueryTable(name);
    }

    public class SubqueryTable extends IdentifierBase implements Table, DefinedElement {
        public SubqueryTable(String name) {
            super(name);
        }

        @Override
        public SQLSyntaxElement toDefinition() {
            return builder -> builder.append(QueryBuilder.this);
        }
    }
}
