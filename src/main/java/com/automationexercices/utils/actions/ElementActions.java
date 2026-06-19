package com.automationexercices.utils.actions;

import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitManager;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    // typing action
    public void type(By locator, String text) {
        waitManager.fluentWait().until(d->{
                    try {
                        WebElement element= d.findElement(locator);
                        scrollToElementJS(locator);
                        element.clear();
                        element.sendKeys(text);
                        LogsManager.info("Typed text: "+text+" into element: "+locator);
                        return true;
                    } catch (Exception e){
                        return false;
                    }
                }
        );
    }

    // click action
    public void click(By locator) {
    waitManager.fluentWait().until(d->{
      try {
          WebElement element= d.findElement(locator);
          scrollToElementJS(locator);
          element.click();
          LogsManager.info("Clicked on element: "+ locator);
          return true;
      } catch (Exception e){
          return false;
            }
    }
    );

    }
    // get text action
    public String getText(By locator) {
      return   waitManager.fluentWait().until(d->{
                    try {
                        WebElement element= d.findElement(locator);
                        scrollToElementJS(locator);
                        String msg= element.getText();
                        LogsManager.info("Got text: "+msg+" from element: "+ locator);
                        return !msg.isEmpty() ? msg: null;
                    } catch (Exception e){
                        return null;
                    }
                }
        );
    }
    // upload file action
    public void uploadFile(By locator,String filePath){
        String absolutePath=System.getProperty("user.dir") + File.separator + filePath;
        waitManager.fluentWait().until(d->{
            try{
                WebElement element=findElement(locator);
                scrollToElementJS(locator);
                element.sendKeys(absolutePath);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }
    // find element action
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    // function to scroll to element and using js
    public void scrollToElementJS(By locator){
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("""
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(locator));
    }

    // select from dropdown by visible text
    public void selectFromDropDown(By locator, String visibleText){
        waitManager.fluentWait().until(d->{
            try{
                WebElement element=findElement(locator);
                scrollToElementJS(locator);
                Select select=new Select(element);
                select.selectByVisibleText(visibleText);
                LogsManager.info("Selected from dropdown: "+visibleText+" in element: "+ locator);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }


}
