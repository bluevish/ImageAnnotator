package com.vs.ImageAnnotatorServer.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RestResponse {

    private int status;
    private String message;
    private Map<String, Object> data;
    private String timestamp;

    public RestResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = new HashMap<>();
        this.timestamp = LocalDateTime.now().toString();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void addData(String key, Object value){
        this.data.put(key, value);
    }

}
