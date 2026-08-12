package com.kari3600.me;

import com.kari3600.me.sqlbuilder.builders.QueryBuilder;
import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.ColumnSetElement;
import com.kari3600.me.sqlbuilder.syntax.column.Column;
import com.kari3600.me.sqlbuilder.syntax.column.TableColumn;
import com.kari3600.me.sqlbuilder.syntax.supplier.IndexSupplier;
import com.kari3600.me.sqlbuilder.syntax.table.DBTable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @ParameterizedTest
    @MethodSource("getTests")
    public void test(SQLBuilder<?> builder, String expected) {
        assertEquals(expected, builder.toString());
    }

    private static Stream<Arguments> getTests() {
        DBTable table = new DBTable("test");
        boolean desc = true;
        TableColumn<String> keyColumn = Column.stringColumn("key");
        TableColumn<Integer> valueColumn = Column.integerColumn("value");
        Column<Long> innerIndexColumn = new IndexSupplier(valueColumn, desc).asColumn("index");

        QueryBuilder.SubqueryTable subquery = QueryBuilder.select(
                ColumnSetElement.of(
                        keyColumn,
                        innerIndexColumn
                ),
                table
        ).toTable("subquery");

        Column<Long> indexColumn = subquery.longColumn("index");

        return Map.of(
                QueryBuilder.select(
                        ColumnSetElement.of(
                                table.stringColumn("username")
                        ),
                        table
                ).where(
                        table.integerColumn("money").isGreaterThan(0)
                ),
                "SELECT `test`.`username` FROM `test` WHERE `test`.`money` > ?",

                QueryBuilder.select(
                        ColumnSetElement.of(keyColumn, valueColumn),
                        table
                ).order(
                        valueColumn,
                        desc
                ).limit(
                        15
                ),
                "SELECT `key`, `value` FROM `test` ORDER BY `value` DESC LIMIT 15",

                QueryBuilder.selectWith(
                        subquery,
                        ColumnSetElement.of(
                                keyColumn,
                                indexColumn
                        )
                ).where(
                        keyColumn.inSet(Set.of("A", "B", "C"))
                ),
                "WITH `subquery` AS (SELECT `key`, ROW_NUMBER() OVER (ORDER BY `value` DESC) AS `index` FROM `test`) SELECT `key`, `subquery`.`index` FROM `subquery` WHERE `key` IN (?, ?, ?)"
        ).entrySet().stream().map(e -> Arguments.of(e.getKey(), e.getValue()));
    }
}
