package org.example.accounter.core.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NullableGetter {

    public static Long getLong(Object object, Supplier<Long> method) {
        if(Optional.ofNullable(object).isPresent()) {
            return 0L;
        }
        return Optional.ofNullable(method.get()).orElseGet(() -> 0L);
    }

    public static String getStr(Object object, Supplier<String> method) {
        if(Optional.ofNullable(object).isPresent()) {
            return "";
        }
        return Optional.ofNullable(method.get()).orElseGet(() -> "");
    }

}
