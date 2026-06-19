package com.automationexercices;

import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.logging.Logs;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyFile;

public class FileUtils {
    private static final String USER_DIR = PropertyReader.getProperty("user.dir")+ File.separator;
    private FileUtils() {
        }
    // renaming
    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
               org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
                LogsManager.info("Target File Path: \"" + oldName + "\", file was renamed to \"" + newName + "\".");
            } else {
                LogsManager.info(("Target File Path: \"" + oldName + "\", already has the desired name \"" + newName + "\"."));
            }
        } catch (IOException e) {
            LogsManager.error(e.getMessage());
        }
    }


    // creating Directory
    public static void createDirectory(String dirPath) {
        try {
            File file = new File(USER_DIR + dirPath);
            if(!file.exists()){
                file.mkdirs();
                LogsManager.info("Directory created: " + dirPath);

            }
        } catch (Exception e) {
            LogsManager.error("Error creating directory: ", dirPath, e.getMessage());
        }
    }

    // force delete
    public static void forceDelete(File file) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(file);
            LogsManager.info("File deleted: " + file.getAbsolutePath());
        } catch (Exception e) {
            LogsManager.error("Error deleting file: ", file.getAbsolutePath(), e.getMessage());
        }
    }


    // cleaning Directory
    public static void cleanDirectory(File file) {
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(file); // in use -> skip
        } catch (Exception e) {
            LogsManager.error("Error cleaning directory: ", file.getAbsolutePath(), e.getMessage());
        }
    }
}
