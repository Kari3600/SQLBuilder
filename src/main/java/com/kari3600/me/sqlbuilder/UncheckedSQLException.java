package com.kari3600.me.sqlbuilder;

import java.sql.SQLException;

public class UncheckedSQLException extends RuntimeException {
    public UncheckedSQLException(SQLException cause) {
        super(cause);
    }
}
