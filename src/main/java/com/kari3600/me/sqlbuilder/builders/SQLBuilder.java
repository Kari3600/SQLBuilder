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

public class SQLBuilder {
    protected final StringBuilder b = new StringBuilder();
    private final List<SQLVariable<?>> variables = new ArrayList<>();

    public SQLBuilder append(String syntax) {
        b.append(syntax);
        return this;
    }

    public SQLBuilder append(SQLSyntaxElement syntax) {
        syntax.toSQL(this);
        if (syntax instanceof SQLVariable<?>) {
            variables.add((SQLVariable<?>) syntax);
        }
        return this;
    }

    public SQLBuilder append(SQLBuilder syntax) {
        b.append(syntax.b);
        variables.addAll(syntax.variables);
        return this;
    }

    public SQLBuilder appendSeparated(Iterable<? extends SQLSyntaxElement> elements, String separator) {
        Iterator<? extends SQLSyntaxElement> it = elements.iterator();
        if (!it.hasNext()) return this;
        append(it.next());
        while (it.hasNext()) {
            append(separator);
            append(it.next());
        }
        return this;
    }

    public <V> SQLBuilder appendSeparated(Iterable<V> elements, BiConsumer<SQLBuilder, V> appender, String separator) {
        Iterator<V> it = elements.iterator();
        if (!it.hasNext()) return this;
        appender.accept(this, it.next());
        while (it.hasNext()) {
            append(separator);
            appender.accept(this, it.next());
        }
        return this;
    }

    public void setVariables(PreparedStatement ps) throws SQLException {
        int idx = 0;
        for (SQLVariable<?> variable : variables) {
            variable.writeValue(ps, idx++);
        }
    }

    @Override
    public String toString() {
        return b.toString();
    }
}
