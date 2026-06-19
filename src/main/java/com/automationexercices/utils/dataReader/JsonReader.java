package com.automationexercices.utils.dataReader;

import com.automationexercices.utils.logs.LogsManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {
    private static final String JSON_FILE_PATH = "src/test/resources/test-data/";
    String jsonReader;
    String jsonFileName;
    public JsonReader(String jsonFileName){
        this.jsonFileName=jsonFileName;
        try{
            JSONObject data=(JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH+jsonFileName+".json"));
            jsonReader=data.toJSONString();
        }
        catch(Exception e){
        LogsManager.error("Error reading JSON file "+jsonFileName, e.getMessage());
        jsonReader= "{}";// to avoid null pointer exception
            }
    }
    public String getJsonReader(String jsonPath) {
        try{
            return JsonPath.read(jsonReader, jsonPath);
        }
        catch(Exception e){
            LogsManager.error("Error reading JSON file "+jsonFileName+" with jsonPath ",jsonPath, e.getMessage());
            return "";
        }
    }

}
