package com.java.exercise.service.config;

import java.util.Properties;

public class ApplicationProperties {

    private static final String DB_URL = "db.url";
    private final String dbURL;

    public ApplicationProperties(Properties properties) {
        dbURL = properties.getProperty(DB_URL);
    }

    public String getDbURL() {
        return dbURL;
    }

}
