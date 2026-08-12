package com.kari3600.me.sqlbuilder.syntax;

public interface DefinedElement extends SQLSyntaxElement {
    SQLSyntaxElement toDefinition();
}
