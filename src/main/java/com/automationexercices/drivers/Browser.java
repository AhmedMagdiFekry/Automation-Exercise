package com.automationexercices.drivers;

public enum Browser {
    // Open/Closed Principle
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeFactory();
        }
    },
    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return new EdgeFactory();
        }
    },
    FIREFOX {
        @Override
        public AbstractDriver getDriverFactory() {
            return new FirefoxFactory();
        }
    };
    public abstract AbstractDriver getDriverFactory();
}
