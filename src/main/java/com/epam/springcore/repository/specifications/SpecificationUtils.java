package com.epam.springcore.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import java.util.function.Function;

public class SpecificationUtils {

    public static <T, U> Specification<T> byField(Function<U, Specification<T>> specFunction, U value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return specFunction.apply(value).toPredicate(root, query, cb);
        };
    }
}