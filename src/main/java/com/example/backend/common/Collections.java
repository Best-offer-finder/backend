package com.example.backend.common;

import java.util.*;

public class Collections {

    public static boolean isNullOrEmpty(Collection< ? > collection) {
        return (collection == null || collection.isEmpty());
    }

    public static String toString(Set<Long> lista) {
        if (lista == null || lista.isEmpty()) {
            return "";
        }

        StringJoiner joiner = new StringJoiner(",");
        for (Long element : lista) {
            joiner.add(element.toString());
        }

        return joiner.toString();
    }

}
