package com.automationexercices.utils.report;

import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import com.google.common.collect.ImmutableMap;

import java.io.File;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnvironmentManager {
    public static void setAllureEnvironmentVariables(){
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", PropertyReader.getProperty("os.name"))
                        .put("JDK Version", PropertyReader.getProperty("java.version"))
                        .put("Browser", PropertyReader.getProperty("browserType"))
                        .put("Execution Type", PropertyReader.getProperty("executionType"))
                        .put("Base URL", PropertyReader.getProperty("baseURLWeb"))
                        .build(), String.valueOf(AllureConstants.RESULTS_FOLDER)+ File.separator

        );
        LogsManager.info("Allure environment properties set in: "+ AllureConstants.RESULTS_FOLDER);
        AllureBinaryManager.downloadAndExtract();
    }
}
