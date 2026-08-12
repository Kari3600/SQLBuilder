package com.kari3600.me.sqlbuilder.builders;

import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.SQLVariable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class SQLBuilder<T extends SQLBuilder<T>> {
    protected final StringBuilder b = new StringBuilder();
    private final List<SQLVariable<?>> variables = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public T append(String syntax) {
        b.append(syntax);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T append(SQLSyntaxElement syntax) {
        syntax.toSQL(this);
        if (syntax instanceof SQLVariable<?>) {
            variables.add((SQLVariable<?>) syntax);
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T append(SQLBuilder<?> syntax) {
        b.append(syntax.b);
        variables.addAll(syntax.variables);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T appendSeparated(Iterable<? extends SQLSyntaxElement> elements, String separator) {
        Iterator<? extends SQLSyntaxElement> it = elements.iterator();
        if (!it.hasNext()) return (T) this;
        append(it.next());
        while (it.hasNext()) {
            append(separator);
            append(it.next());
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <V> T appendSeparated(Iterable<V> elements, BiConsumer<SQLBuilder<T>, V> appender, String separator) {
        Iterator<V> it = elements.iterator();
        if (!it.hasNext()) return (T) this;
        appender.accept(this, it.next());
        while (it.hasNext()) {
            append(separator);
            appender.accept(this, it.next());
        }
        return (T) this;
    }

    protected void setVariables(PreparedStatement ps) throws SQLException {
        int idx = 0;
        for (SQLVariable<?> variable : variables) {
            variable.writeValue(ps, idx++);
        }
    }

    public PreparedStatement build(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(b.toString());
        setVariables(statement);
        return statement;
    }

    @Override
    public String toString() {
        return b.toString();
    }
}
