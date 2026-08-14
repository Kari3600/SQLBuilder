package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;
import com.kari3600.me.sqlbuilder.syntax.supplier.SQLSupplier;

import java.util.SequencedMap;

public abstract class OrderedPropertiesElement implements SQLSyntaxElement {
    protected SequencedMap<TableColumn<?>, SQLSupplier<?>> orderedProperties;

    public OrderedPropertiesElement(SequencedMap<TableColumn<?>, SQLSupplier<?>> properties) {
        orderedProperties = properties;
    }
}
