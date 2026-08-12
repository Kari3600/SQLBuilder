package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.IdentifierBase;
import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.supplier.SQLSupplier;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;

public class RenamedColumn<T> extends IdentifierBase implements Column<T> {
    private final SQLSupplier<T> supplier;

    public RenamedColumn(SQLSupplier<T> propertySupplier, String name) {
        super(name);
        this.supplier = propertySupplier;
    }

    @Override
    public SQLSyntaxElement toDefinition() {
        return builder -> {
            builder.append(supplier).append(" AS ");
            super.toSQL(builder);
        };
    }

    @Override
    public SQLType<T> getType() {
        return supplier.getType();
    }
}
