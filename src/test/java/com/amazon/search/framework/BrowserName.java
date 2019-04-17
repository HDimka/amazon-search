package com.amazon.search.framework;

public enum BrowserName {

    FIREFOX("firefox"),
    CHROME("chrome"),
    EDGE("edge");

    private String name;

    /**
     * Instantiates a new BrowserName object.
     *
     * @param name the name
     */
    BrowserName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static BrowserName fromString(String name) {
        if (name == null) {
            return null;
        }
        switch (name.toLowerCase()) {
            case "chrome":
                return BrowserName.CHROME;

            case "firefox":
                return BrowserName.FIREFOX;

            case "edge":
                return BrowserName.EDGE;

            default:
                return null;
        }

    }
}