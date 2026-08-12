package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;
import com.kari3600.me.sqlbuilder.syntax.supplier.SQLSupplier;

import java.util.SequencedMap;
import java.util.function.Function;

public class ValuesElement extends OrderedPropertiesElement {

    @Override
    public void toSQL(SQLBuilder<?> builder) {
        builder.append("(").appendSeparated(orderedProperties.keySet(), ", ").append(") VALUES (").appendSeparated(orderedProperties.values(), ", ").append(")");
    }

    private ValuesElement(SequencedMap<TableColumn<?>, SQLSupplier<?>> properties) {
        super(properties);
    }

    public static ValuesElement of(SequencedMap<TableColumn<?>, SQLSupplier<?>> properties) {
        return new ValuesElement(properties);
    }

    public static ValuesElement build(Function<PropertiesMapBuilder, PropertiesMapBuilder> function) {
        PropertiesMapBuilder builder = new PropertiesMapBuilder();
        builder = function.apply(builder);
        return new ValuesElement(builder.build());
    }
}
