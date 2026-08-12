package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;
import com.kari3600.me.sqlbuilder.syntax.supplier.SQLSupplier;

import java.util.LinkedHashMap;
import java.util.SequencedMap;

public class PropertiesMapBuilder {
    private final SequencedMap<TableColumn<?>, SQLSupplier<?>> map = new LinkedHashMap<>();

    public <T> PropertiesMapBuilder put(TableColumn<T> column, SQLSupplier<T> supplier) {
        map.put(column, supplier);
        return this;
    }

    public <T> PropertiesMapBuilder put(TableColumn<T> column, T value) {
        put(column, column.getType().createConstant(value));
        return this;
    }

    public SequencedMap<TableColumn<?>, SQLSupplier<?>> build() {
        return map;
    }
}
