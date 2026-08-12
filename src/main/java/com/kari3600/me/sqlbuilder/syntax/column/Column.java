package com.kari3600.me.sqlbuilder.syntax.column;

import com.kari3600.me.sqlbuilder.syntax.DefinedElement;
import com.kari3600.me.sqlbuilder.syntax.Identifier;
import com.kari3600.me.sqlbuilder.syntax.predicate.PropertyInSetDatabasePredicate;
import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;
import com.kari3600.me.sqlbuilder.syntax.supplier.ConstantSupplier;
import com.kari3600.me.sqlbuilder.syntax.supplier.SQLSupplier;
import com.kari3600.me.sqlbuilder.syntax.table.Table;
import com.kari3600.me.sqlbuilder.syntax.type.SQLType;
import com.kari3600.me.utils.object.key.Key;

import java.util.Set;
import java.util.stream.Collectors;

public interface Column<T> extends DefinedElement, SQLSupplier<T>, Identifier, Key<T> {

    default TableColumn<T> inTable(Table table) {
        return new AbstractColumn<>(this.getType(), table, this.getName());
    }

    default SQLPredicate inSet(Set<? extends T> set) {
        return this.inSetOfSuppliers(set.stream().map(v -> getType().createConstant(v)).collect(Collectors.toSet()));
    }

    default SQLPredicate inSetOfSuppliers(Set<ConstantSupplier<T>> set) {
        return new PropertyInSetDatabasePredicate<>(this, set);
    }

    @Deprecated
    static <T> TableColumn<T> column(String name, SQLType<T> type) {
        return new AbstractColumn<>(type, null, name);
    }

    static StringColumn stringColumn(String name) {
        return new StringColumn(null, name);
    }


    static IntegerColumn integerColumn(String name) {
        return new IntegerColumn(null, name);
    }

    static LongColumn longColumn(String name) {
        return new LongColumn(null, name);
    }

    static BooleanColumn booleanColumn(String name) {
        return new BooleanColumn(null, name);
    }

    static TimestampColumn timestampColumn(String name) {
        return new TimestampColumn(null, name);
    }
}
