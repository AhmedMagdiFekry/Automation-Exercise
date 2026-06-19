package com.automationexercices.utils.actions;

import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    /**
     * Switch to a frame by its name or ID.
     *
     * @param index The name or ID of the frame to switch to.
     */
    public void switchToFrameByIndex(int index) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(index);
                LogsManager.info("Switched to frame with index: " + index);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    /**
     * Switch to a frame by its name or ID.
     *
     * @param nameOrId The name or ID of the frame to switch to.
     */
    public void switchToFrameByNameOrId(String nameOrId) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(nameOrId);
                LogsManager.info("Switched to frame with name or ID: " + nameOrId);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    /**
     * Switch to a frame by its WebElement.
     *
     * @param frameLocator The WebElement of the frame to switch to.
     */
    public void switchToFrameByElement(By frameLocator) {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().frame(d.findElement(frameLocator));
                LogsManager.info("Switched to frame with locator: " + frameLocator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    /**
     * Switch back to the default content from any frame.
     */
    public void switchToDefaultContent() {
        waitManager.fluentWait().until(d -> {
            try {
                d.switchTo().defaultContent();
                LogsManager.info("Switched back to default content");
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}