package com.automationexercices.utils.actions;

import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {
    private final WebDriver driver;


    public BrowserActions(WebDriver driver) {

        this.driver = driver;

    }

    // maximize the window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // navigate to a url
    public void navigateTo(String url) {
        driver.get(url);
        LogsManager.info("Navigated to: ", url);
    }

    // get the current url
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LogsManager.info("Current URL: ", url);
        return url;
    }

    // close the current window
    public void closeWindow() {
        driver.close();
    }

    // refresh the page
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // open a new window
    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }
    //close extension tab
    //close extension tab
//    public void closeExtensionTab() {
//        String currentWindowHandle = driver.getWindowHandle(); //0 1
//        new WaitManager(driver).fluentWait().until(
//                d ->
//                {
//                    return d.getWindowHandles().size() > 1; //wait until extension tab is opened
//                }
//        );
//        for (String windowHandle : driver.getWindowHandles()) //extension tab is opened
//        {
//            if (!windowHandle.equals(currentWindowHandle))
//                driver.switchTo().window(windowHandle).close(); //close the extension tab
//        }
//        driver.switchTo().window(currentWindowHandle); //switch back to the main window
//        LogsManager.info("Extension tab closed");
//}}
}
