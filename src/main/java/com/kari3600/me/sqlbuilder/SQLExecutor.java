package com.kari3600.me.sqlbuilder;

import com.kari3600.me.utils.function.ThrowingConsumer;
import com.kari3600.me.utils.function.ThrowingFunction;
import com.kari3600.me.utils.function.ThrowingRunnable;
import com.kari3600.me.utils.function.ThrowingSupplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class SQLExecutor {
    protected final DataSource dataSource;
    protected final Executor executor;

    public SQLExecutor(DataSource dataSource, Executor executor) {
        this.dataSource = dataSource;
        this.executor = executor;
    }

    public <T> CompletableFuture<T> call(ThrowingFunction<Connection, T, SQLException> handler) {
        return supply(() -> {
            try (Connection connection = dataSource.getConnection()) {
                return handler.apply(connection);
            }
        });
    }

    public CompletableFuture<Void> call(ThrowingConsumer<Connection, SQLException> handler) {
        return call(handler.toFunction());
    }

    public <T> CompletableFuture<T> transaction(ThrowingFunction<Connection, T, SQLException> work) {
        return supply(() -> {
            try (Connection connection = dataSource.getConnection()) {
                connection.setAutoCommit(false);

                try {
                    T result = work.apply(connection);

                    connection.commit();

                    return result;
                } catch (Throwable error) {
                    try {
                        connection.rollback();
                    } catch (SQLException rollbackError) {
                        error.addSuppressed(rollbackError);
                    }

                    throw error;
                }
            }
        });
    }

    public CompletableFuture<Void> transaction(ThrowingConsumer<Connection, SQLException> work) {
        return transaction(work.toFunction());
    }

    private <T> CompletableFuture<T> supply(ThrowingSupplier<T, SQLException> work) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return work.get();
            } catch (SQLException e) {
                throw new UncheckedSQLException(e);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    private CompletableFuture<Void> supply(ThrowingRunnable<SQLException> work) {

        return CompletableFuture.runAsync(() -> {
            try {
                work.run();
            } catch (SQLException e) {
                throw new UncheckedSQLException(e);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
