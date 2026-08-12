package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.supplier.SupplierBase;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.WrappedType;
import com.kari3600.me.utils.translator.Translator;

public class WrappedColumn<K, T> extends SupplierBase<K> implements TableColumn<K> {
    private final AbstractColumn<T> wrapped;

    protected WrappedColumn(AbstractColumn<T> wrapped, Translator<K, T> translator) {
        super(new WrappedType<>(wrapped.getType(), translator));
        this.wrapped = wrapped;
    }

    @Override
    public SQLSyntaxElement toDefinition() {
        return wrapped.toDefinition();
    }

    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public void toSQL(SQLBuilder<?> builder) {
        wrapped.toSQL(builder);
    }

    @Override
    public Table getTable() {
        return wrapped.getTable();
    }
}
