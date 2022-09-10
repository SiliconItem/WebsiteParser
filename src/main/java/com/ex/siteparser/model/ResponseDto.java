package com.ex.siteparser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.gargoylesoftware.htmlunit.html.DomNode;

import java.util.Date;

public class ResponseDto {

    private Date date = new Date();
    private String url;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String xpath;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String fixObjectId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String fixObjectType;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String status = "success";
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int objectCount;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JacksonXmlElementWrapper(useWrapping = false)
    private String body;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private DomNode domNode;

    public DomNode getDomNode() {
        return domNode;
    }

    public void setDomNode(DomNode domNode) {
        this.domNode = domNode;
    }

    public int getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(int objectCount) {
        this.objectCount = objectCount;
    }

    public String getFixObjectId() {
        return fixObjectId;
    }

    public void setFixObjectId(String fixObjectId) {
        this.fixObjectId = fixObjectId;
    }

    public String getFixObjectType() {
        return fixObjectType;
    }

    public void setFixObjectType(String fixObjectType) {
        this.fixObjectType = fixObjectType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
