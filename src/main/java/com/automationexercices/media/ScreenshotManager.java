package com.automationexercices.media;

import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.utils.report.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotManager {
    public static final String Screenshot_Path="test-output/screenshots/";

    public static void takeFullPageScreenShot(WebDriver driver, String ScreenshotName){
    try {
        // capture screenshot as a file
        File screenshotSrc=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Save a screenshot to a file if needed
        File screenshotDest=new File(Screenshot_Path + ScreenshotName +"-" + TimeManager.getTimestamp() +".png");
        FileUtils.copyFile(screenshotSrc,screenshotDest);
        // TODO: attach the screenshot to a report (e.g., Allure)
        AllureAttachmentManager.attachScreenshot(ScreenshotName, screenshotDest.getAbsolutePath());
        LogsManager.info("Screenshot taken: " , screenshotDest.getAbsolutePath());

    }catch (Exception e){
    LogsManager.error("Error taking screenshot: ",e.getMessage());
    }
}
// take element screenshot
public static void takeElementcreenShot(WebDriver driver ,By elementLocator){
    try {
        // capture screenshot as a file
        String areaName=driver.findElement(elementLocator).getAccessibleName();
        File src=driver.findElement(elementLocator).getScreenshotAs(OutputType.FILE);
        // Save a screenshot to a file if needed
        File dest=new File(Screenshot_Path + areaName + "-" + TimeManager.getTimestamp() +".png");
        FileUtils.copyFile(src,dest);
        // TODO: attach the screenshot to a report (e.g., Allure)
        // AllureUtils.attachscreenShotToAllure(ScreenshotName, dest.getPath());
        LogsManager.info("Screenshot taken: " , dest.getAbsolutePath());

    }catch (Exception e){
        LogsManager.error("Error taking screenshot: "+ e.getMessage());
    }
}
}

