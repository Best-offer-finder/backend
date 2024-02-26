package com.example.backend.common;

import java.util.Collection;

public class Collections {

    public static boolean isNullOrEmpty(Collection< ? > collection) {
        return (collection == null || collection.isEmpty());
    }

}
