package com.automationexercices.drivers;

import com.automationexercices.FileUtils;
import com.automationexercices.utils.dataReader.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.io.File;

public abstract class AbstractDriver {
    protected final String remoteHost = PropertyReader.getProperty("remoteHost");
    protected final String remotePort = PropertyReader.getProperty("remotePort");
    public abstract WebDriver createDriver();
}
