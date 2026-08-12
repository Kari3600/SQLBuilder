package com.kari3600.me.sqlbuilder.syntax.predicate;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.column.Column;
import com.kari3600.me.sqlbuilder.syntax.supplier.ConstantSupplier;

import java.util.Set;

public class PropertyInSetDatabasePredicate<T> implements SQLPredicate {
    private final Column<T> element;
    private final Set<ConstantSupplier<T>> values;

    @Override
    public void toSQL(SQLBuilder<?> builder) {
        if (values.isEmpty()) {
            ConstantPredicate.FALSE.toSQL(builder);
        } else {
            builder.append(element).append(" IN (").appendSeparated(values, ", ").append(")");
        }
    }

    public PropertyInSetDatabasePredicate(Column<T> element, Set<ConstantSupplier<T>> values) {
        this.element = element;
        this.values = values;
    }
}
