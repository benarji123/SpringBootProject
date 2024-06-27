package com.techmojo.web.automation.constants;

public enum BrowserEnums {

    CHROME("CHROME"), FIREFOX("FIREFOX"), SAFARI("SAFARI"), INTERNET_EXPLORER("INTERNET_EXPLORER"), OPERA("OPERA");

    /** The browser. */
    private String browserName;

    /**
     * Instantiates a browser enum.
     *
     * @param emailTemplateEvent the email template event
     */
    BrowserEnums(String browserName) {
        this.browserName = browserName;
    }

    /**
     * Gets the event.
     *
     * @return the event
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * Gets the enum by string.
     *
     * @param eventName the event name
     * @return the enum by string
     */
    public static BrowserEnums getEnumByString(String browserName) {
        for (BrowserEnums e : BrowserEnums.values()) {
            if (browserName.equals(e.getBrowserName()))
                return e;
        }
        return null;
    }

}
