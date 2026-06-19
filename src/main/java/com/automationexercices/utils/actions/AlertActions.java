package com.automationexercices.utils.actions;

import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private final WebDriver driver;
    private final WaitManager waitManager;
    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    /** Method to accept an alert
     *
     */
    public void acceptAlert() {
        waitManager.fluentWait().until(d->{
            try {
                d.switchTo().alert().accept();
                return true;
            }catch (Exception e){
                LogsManager.error("Error accepting alert: ",e.getMessage());
                return false;
            }
        });
    }
    /**
     *  Method to dismiss an alert
     */
    public void dismissAlert() {
        waitManager.fluentWait().until(d->{
            try {
                d.switchTo().alert().dismiss();
                return true;
            }catch (Exception e){
                LogsManager.error("Error dismissing alert: ",e.getMessage());
                return false;
            }
        });
    }
    /** Method to get text from an alert
     * @return String - text from the alert
     */
    public String getAlertText() {
        return waitManager.fluentWait().until(d->{
            try {
                String text =d.switchTo().alert().getText();
                return !text.isEmpty() ? text:null;
            }catch (Exception e){
                LogsManager.error("Error getting alert text: ",e.getMessage());
                return null;
            }
        });
    }
    /** Method to send text to an alert
     * @param text String - text to be sent to the alert
     */
    public void sendTextToAlert(String text) {
     waitManager.fluentWait().until(d->{
         try {
             d.switchTo().alert().sendKeys(text);
             return true;
         }catch (Exception e){
                LogsManager.error("Error sending text to alert: ",e.getMessage());
             return false;
         }
     }) ;
    }


}
