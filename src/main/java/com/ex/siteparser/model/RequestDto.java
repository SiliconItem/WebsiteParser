package com.ex.siteparser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import java.util.Date;

public class RequestDto {

    private Date date = new Date();
    private String url;

    private String xpath;
    private String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }


}
