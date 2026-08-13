package com.kari3600.me.sqlbuilder.syntax;

public abstract class IdentifierBase implements Identifier {
    protected final String name;

    protected IdentifierBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
