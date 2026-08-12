package com.kari3600.me.sqlbuilder.syntax.type;

import com.kari3600.me.utils.translator.Translator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WrappedType<I, O> implements SQLType<I> {
    private final SQLType<O> wrapped;
    private final Translator<I, O> translator;

    public WrappedType(SQLType<O> wrapped, Translator<I, O> translator) {
        this.wrapped = wrapped;
        this.translator = translator;
    }

    @Override
    public void setValue(PreparedStatement preparedStatement, int index, I value) throws SQLException {
        wrapped.setValue(preparedStatement, index, translator.encode(value));
    }

    @Override
    public I getValue(ResultSet resultSet, int index) throws SQLException {
        return translator.decode(wrapped.getValue(resultSet, index));
    }
}
