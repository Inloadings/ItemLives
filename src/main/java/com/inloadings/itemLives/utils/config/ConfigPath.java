package com.inloadings.itemLives.utils.config;

import java.util.List;

public enum ConfigPath {
    LIVES_USAGE("usage.main", List.class),
    LIVES_SET_USAGE("usage.set", String.class),
    LIVES_ADD_USAGE("usage.add", String.class);

    private final String path;
    private final Class<?> type;

    ConfigPath(String path, Class<?> type) {
        this.path = path;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public <T> T castToType(Object value) {
        return (T) type.cast(value);
    }
}

