package com.assignment.storyManagement.Utils;

public class JsonUtil {
    public static String jsonStringConverter(String stringResponse) {
        String[] parts = stringResponse.split("\\r\\n");
        String jsonString = "{\"";
        for (int i = 0; i < parts.length; i++) {
            jsonString += parts[i].replace(":", "\":\"");
            jsonString += (i < parts.length - 1) ? "\", \"" : "";
        }
        return jsonString += "\"}";
    }
}
