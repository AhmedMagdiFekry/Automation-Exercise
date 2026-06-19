package com.automationexercices.drivers;

import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class EdgeFactory extends AbstractDriver {

    private EdgeOptions getOptions(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");//start Google maximized
        options.addArguments("--disable-notifications");//disable notifications
        options.addArguments("--disable-popup-blocking");//disable popup blocking
        options.addArguments("--incognito");//open browser in incognito mode
        options.setAcceptInsecureCerts(true); //accept insecure certificates
        switch (PropertyReader.getProperty("executionType"))
        {
            case "LocalHeadless" -> options.addArguments("--headless=new");
            case  "Remote" ->
            {
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--headless=new");
            }
        }
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("Local")||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("Localheadless")){
            return new EdgeDriver(getOptions());
        } else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try
            {
                return new RemoteWebDriver(
                        new URI("http:" + remoteHost + ":" + remotePort +"/wd/hub").toURL(),
                        getOptions()
                );
            }catch (Exception e){
                LogsManager.error("Error creating Remote WebDriver: " + e.getMessage());
                throw new IllegalStateException("Error creating Remote WebDriver: " + e.getMessage());
            }

        }
        else {
            LogsManager.error("Execution Type not Supported: " + PropertyReader.getProperty("executionType"));
            throw new IllegalStateException("Execution Type not Supported: " + PropertyReader.getProperty("executionType"));
        }

    }
    }

