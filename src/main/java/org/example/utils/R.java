package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class R {

    private static final String RES_PATH = "src/main/resources/";
    private static final String CONFIG_PATH = RES_PATH + "config.properties";

    private static final String TEST_DATA_DEFAULT_PATH = RES_PATH + "test_data.properties";

    private static final Logger LOGGER = LogManager.getLogger(R.class);

    private static Object getFromProperties(String path, String key) {
        Properties properties = loadProperties(path);
        return properties != null ? properties.get(key) : null;
    }

    private static List<String> getKeys(String filePath) {
        Properties properties = loadProperties(filePath);
        return properties != null ? properties.keySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList()) : null;
    }

    private static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            LOGGER.error(e);
            return null;
        }
    }

    public static class CONFIG {

        public static Object get(String key) {
            return getFromProperties(CONFIG_PATH, key);
        }

        public static int getInt(String key) {
            return Integer.parseInt((String) get(key));
        }

        public static String getString(String key) {
            return (String) get(key);
        }

        public static boolean getBoolean(String key) {
            return Boolean.parseBoolean((String) get(key));
        }
    }

    public static class TEST_DATA {

        public static Object get(String key) {
            return getFromProperties(TEST_DATA_DEFAULT_PATH, key);
        }

        public static String getString(String key) {
            return (String) get(key);
        }
    }
}
