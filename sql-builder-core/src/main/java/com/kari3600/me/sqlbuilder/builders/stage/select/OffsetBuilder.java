package com.kari3600.me.sqlbuilder.builders.stage.select;

public abstract class OffsetBuilder extends FinalBuilder {
    public FinalBuilder offset(int offset) {
        return this;
    }
}
