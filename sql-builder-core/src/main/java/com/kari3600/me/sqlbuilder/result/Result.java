package com.kari3600.me.sqlbuilder.result;

import com.kari3600.me.sqlbuilder.syntax.column.Column;
import com.kari3600.me.utils.function.ThrowingFunction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Result {
    private final ResultSet rs;
    private final Map<Column<?>, Integer> indexMap = new HashMap<>();

    public Result(ResultSet rs) {
        this.rs = rs;
    }

    public boolean next() throws SQLException {
        return rs.next();
    }

    private int findColumnIndex(Column<?> column) throws SQLException {
        return rs.findColumn(column.getName());
    }

    private int getColumnIndex(Column<?> column) throws SQLException {
        Integer index = indexMap.get(column);
        if (index == null) {
            index = findColumnIndex(column);
            indexMap.put(column, index);
        }
        return index;
    }

    public <T> T get(Column<T> column) throws SQLException {
        return column.getType().getValue(rs, getColumnIndex(column));
    }

    public <T> List<T> toList(Column<T> column) throws SQLException {
        List<T> list = new ArrayList<>();
        while (next()) {
            list.add(get(column));
        }
        return list;
    }

    public <T> List<T> toList(ThrowingFunction<Result, T, SQLException> function) throws SQLException {
        List<T> list = new ArrayList<>();
        while (next()) {
            list.add(function.apply(this));
        }
        return list;
    }

    public <K, V> Map<K, V> toMap(Column<K> keyColumn, Column<V> valueColumn) throws SQLException {
        Map<K, V> map = new HashMap<>();
        while (next()) {
            map.put(get(keyColumn), get(valueColumn));
        }
        return map;
    }

    public List<Map<Column<?>, Object>> toVariableMap(Collection<Column<?>> columns) throws SQLException {
        List<Map<Column<?>, Object>> maps = new ArrayList<>();
        while (next()) {
            Map<Column<?>, Object> map = new HashMap<>();
            for (Column<?> column : columns) {
                map.put(column, get(column));
            }
            maps.add(map);
        }
        return maps;
    }
}
