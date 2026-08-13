package com.kari3600.me.sqlbuilder.syntax.supplier;

import com.kari3600.me.sqlbuilder.builders.SQLBuilder;
import com.kari3600.me.sqlbuilder.syntax.predicate.SQLPredicate;

public interface SQLNumberSupplier<T extends Number> extends SQLSupplier<T> {

    default SQLPredicate isGreaterThan(T other) {
        return isGreaterThan(getType().createConstant(other));
    }

    default SQLPredicate isGreaterThan(SQLSupplier<T> other) {
        return builder -> builder.append(this).append(" > ").append(other);
    }

    default SQLPredicate isLessThan(T other) {
        return isLessThan(getType().createConstant(other));
    }

    default SQLPredicate isLessThan(SQLSupplier<T> other) {
        return builder -> builder.append(this).append(" < ").append(other);
    }

    default SQLPredicate isGreaterThanOrEqualTo(T other) {
        return isGreaterThanOrEqualTo(getType().createConstant(other));
    }

    default SQLPredicate isGreaterThanOrEqualTo(SQLSupplier<T> other) {
        return builder -> builder.append(this).append(" >= ").append(other);
    }

    default SQLPredicate isLessThanOrEqualTo(T other) {
        return isLessThanOrEqualTo(getType().createConstant(other));
    }

    default SQLPredicate isLessThanOrEqualTo(SQLSupplier<T> other) {
        return builder -> builder.append(this).append(" <= ").append(other);
    }

    default SQLSupplier<T> add(T other) {
        return add(getType().createConstant(other));
    }

    default SQLSupplier<T> add(SQLSupplier<T> other) {
        return new SupplierBase<>(SQLNumberSupplier.this.getType()) {
            @Override
            public void toSQL(SQLBuilder builder) {
                builder.append(SQLNumberSupplier.this).append(" + ").append(other);
            }
        };
    }

    default SQLSupplier<T> subtract(T other) {
        return subtract(getType().createConstant(other));
    }

    default SQLSupplier<T> subtract(SQLSupplier<T> other) {
        return new SupplierBase<>(SQLNumberSupplier.this.getType()) {
            @Override
            public void toSQL(SQLBuilder builder) {
                builder.append(SQLNumberSupplier.this).append(" - ").append(other);
            }
        };
    }

    default SQLSupplier<T> multiply(T other) {
        return multiply(getType().createConstant(other));
    }

    default SQLSupplier<T> multiply(SQLSupplier<T> other) {
        return new SupplierBase<>(SQLNumberSupplier.this.getType()) {
            @Override
            public void toSQL(SQLBuilder builder) {
                builder.append(SQLNumberSupplier.this).append(" * ").append(other);
            }
        };
    }

    default SQLSupplier<T> divide(T other) {
        return divide(getType().createConstant(other));
    }

    default SQLSupplier<T> divide(SQLSupplier<T> other) {
        return new SupplierBase<>(SQLNumberSupplier.this.getType()) {
            @Override
            public void toSQL(SQLBuilder builder) {
                builder.append(SQLNumberSupplier.this).append(" / ").append(other);
            }
        };
    }

    default SQLSupplier<T> mod(T other) {
        return modulo(getType().createConstant(other));
    }

    default SQLSupplier<T> modulo(SQLSupplier<T> other) {
        return new SupplierBase<>(SQLNumberSupplier.this.getType()) {
            @Override
            public void toSQL(SQLBuilder builder) {
                builder.append(SQLNumberSupplier.this).append(" % ").append(other);
            }
        };
    }
}
