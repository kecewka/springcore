package com.epam.springcore.exception;

import java.util.Map;

public class ReturnMessageList {
    private Map<String,String> info;

    public ReturnMessageList() {
    }

    public Map<String,String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

}
