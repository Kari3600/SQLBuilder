package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.*;
import com.kari3600.me.utils.translator.Translator;

public class AbstractColumn<T> extends ColumnBase<T> implements TableColumn<T> {
    protected final Table table;

    public AbstractColumn(SQLType<T> type, Table table, String name) {
        super(type, name);
        this.table = table;
    }

    @Override
    public void toSQL(SQLBuilder builder) {
        if (table != null) {
            builder.append(table).append(".");
        }
        super.toSQL(builder);
    }

    @Override
    public SQLSyntaxElement toDefinition() {
        return this;
    }

    public <V> TableColumn<V> wrap(Translator<V,T> translator) {
        return new WrappedColumn<>(this, translator);
    }

    @Override
    public Table getTable() {
        return table;
    }
}
