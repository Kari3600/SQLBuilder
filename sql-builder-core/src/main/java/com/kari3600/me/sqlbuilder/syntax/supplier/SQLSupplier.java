package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.syntax.type.SQLType;
import com.kari3600.me.sqlbuilder.syntax.SQLSyntaxElement;
import com.kari3600.me.sqlbuilder.syntax.column.Column;
import com.kari3600.me.sqlbuilder.syntax.column.RenamedColumn;
import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;

public interface SQLSupplier<T> extends SQLSyntaxElement {
    SQLType<T> getType();

    default SQLPredicate isEqualTo(T object) {
        return isEqualTo(getType().createConstant(object));
    }

    default SQLPredicate isEqualTo(SQLSupplier<T> other) {
        return builder -> {
            builder.append(this).append(" = ").append(other);
        };
    }

    default Column<T> asColumn(String columnName) {
        return new RenamedColumn<>(this, columnName);
    }
}
