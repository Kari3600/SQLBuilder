package com.kari3600.me.sqlbuilder.builders.stage.select;

public abstract class LimitBuilder extends OffsetBuilder {
    public OffsetBuilder limit(int limit) {
        builder.append(" LIMIT ").append(String.valueOf(limit));
        return this;
    }
}
