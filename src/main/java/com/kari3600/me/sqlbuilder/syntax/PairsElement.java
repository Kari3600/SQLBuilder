package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;
import com.kari3600.me.sqlbuilder.syntax.supplier.SQLSupplier;

import java.util.SequencedMap;
import java.util.function.Function;

public class PairsElement extends OrderedPropertiesElement {

    private PairsElement(SequencedMap<TableColumn<?>, SQLSupplier<?>> properties) {
        super(properties);
    }

    @Override
    public void toSQL(SQLBuilder<?> builder) {
        builder.appendSeparated(orderedProperties.entrySet(), (builder1, entry) ->
            builder1.append(entry.getKey()).append(" = ").append(entry.getValue()), ", ");
    }

    public static PairsElement of(SequencedMap<TableColumn<?>, SQLSupplier<?>> properties) {
        return new PairsElement(properties);
    }

    public static PairsElement build(Function<PropertiesMapBuilder, PropertiesMapBuilder> function) {
        PropertiesMapBuilder builder = new PropertiesMapBuilder();
        builder = function.apply(builder);
        return new PairsElement(builder.build());
    }
}
