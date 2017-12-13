package com.f.firebaserealtimedatabase.models;

import java.util.Date;
import java.util.Map;

/**
 * Created by Furkan on 6.12.2017.
 */

public class Tweet {
    String key;
    String message;
    Map<String, String> date;
    String name;

    public Tweet(String key, String message, Map<String, String> date, String name) {
        this.key = key;
        this.message = message;
        this.date = date;
        this.name = name;
    }

    public Tweet(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getDate() {
        return date;
    }

    public void setDate(Map<String, String> date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
