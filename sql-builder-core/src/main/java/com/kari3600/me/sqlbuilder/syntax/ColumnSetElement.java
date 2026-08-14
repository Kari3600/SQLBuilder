package com.kari3600.me.sqlbuilder.syntax;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.column.Column;

import java.util.*;

public class ColumnSetElement implements SQLSyntaxElement {

    private final SequencedSet<Column<?>> columns;

    @Override
    public void toSQL(SQLBuilder builder) {
        builder.appendSeparated(columns, (b, column) -> b.append(column.toDefinition()), ", ");
    }

    public SequencedSet<Column<?>> getColumns() {
        return columns;
    }

    public ColumnSetElement(SequencedSet<Column<?>> propertiesNames) {
        this.columns = propertiesNames;
    }

    public static ColumnSetElement of(Column<?>... propertiesNames) {
        return new ColumnSetElement(new LinkedHashSet<>(List.of(propertiesNames)));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SequencedSet<Column<?>> properties = new LinkedHashSet<>();

        public Builder add(Column<?> property) {
            properties.add(property);
            return this;
        }

        public ColumnSetElement build() {
            return new ColumnSetElement(properties);
        }
    }
}
