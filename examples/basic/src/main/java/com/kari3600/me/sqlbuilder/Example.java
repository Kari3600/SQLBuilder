package com.kari3600.me.sqlbuilder;

import com.kari3600.me.sqlbuilder.builders.StatementBuilder;
import com.kari3600.me.sqlbuilder.syntax.ColumnSetElement;
import com.kari3600.me.sqlbuilder.syntax.PairsElement;
import com.kari3600.me.sqlbuilder.syntax.column.Column;
import com.kari3600.me.sqlbuilder.syntax.column.IntegerColumn;
import com.kari3600.me.sqlbuilder.syntax.column.StringColumn;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;

import java.util.concurrent.CompletableFuture;

public class Example {
    // Define statements executor
    private static final SQLExecutor executor = new SQLExecutor(
            null,
            null
    );

    // Define table "users" (`users`)
    private final DBTable table = new DBTable("users");

    // Define column "user_id" in table "users" (`users`.`user_id`)
    private final StringColumn userIdColumn = table.stringColumn("user_id");

    // Define column "balance" without parenting table (`balance`)
    private final IntegerColumn balanceColumn = Column.integerColumn("balance");

    public CompletableFuture<Integer> getBalance(String userId) {
        return StatementBuilder.select(
                ColumnSetElement.of(balanceColumn),
                table
        ).where(
                userIdColumn.isEqualTo(userId)
        ).executeAsync(executor, rs -> {
            if (!rs.next()) return null;
            return rs.get(balanceColumn);
        });
    }

    public CompletableFuture<Void> addBalance(String userId, int amount) {
        return StatementBuilder.update(
                PairsElement.build(b -> b
                        .put(balanceColumn, balanceColumn.add(amount))
                ),
                table
        ).where(
                userIdColumn.isEqualTo(userId)
        ).executeAsync(
                executor
        ).thenAccept(affectedRows -> {
            if (affectedRows == 0) throw new RuntimeException(String.format("User %s not found", userId));
        });
    }

    public CompletableFuture<Void> transferBalance(String fromUserId, String toUserId, int amount) {
        return executor.transaction(conn -> {
            int affectedRowsFrom = StatementBuilder.update(
                    PairsElement.build(b -> b
                            .put(balanceColumn, balanceColumn.subtract(amount))
                    ),
                    table
            ).where(
                    userIdColumn.isEqualTo(fromUserId)
            ).execute(conn);
            if (affectedRowsFrom == 0) throw new RuntimeException(String.format("User %s not found", fromUserId));
            int affectedRowsTo = StatementBuilder.update(
                    PairsElement.build(b -> b
                            .put(balanceColumn, balanceColumn.add(amount))
                    ),
                    table
            ).where(
                    userIdColumn.isEqualTo(toUserId)
            ).execute(conn);
            if (affectedRowsTo == 0) throw new RuntimeException(String.format("User %s not found", toUserId));
        });
    }
}
