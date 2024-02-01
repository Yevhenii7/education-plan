package org.example.enums;

public enum PageOpeningStrategy {

    BY_URL("BY_URL"),

    BY_ELEMENT("BY_ELEMENT"),

    BY_URL_AND_ELEMENT("BY_URL_AND_ELEMENT");

    private String value;

    PageOpeningStrategy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
