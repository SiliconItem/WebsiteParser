package com.ex.siteparser.service;

import com.ex.siteparser.model.RequestDto;
import com.ex.siteparser.model.ResponseDto;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class RestService {

    static final String SUCCESS = "success", ERROR = "error";

    private WebClient webClient = new WebClient(BrowserVersion.CHROME);

    {
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.setJavaScriptErrorListener(putJSErrorListener());
    }

    private final Map<String, Object> fixObjectMap = new HashMap<>();

    public  ResponseDto job1 (String urlStr) {
        ResponseDto res = new ResponseDto();
        res.setUrl(urlStr);
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            res.setMessage(e.getMessage());
            res.setBody(e.toString());
            res.setStatus(ERROR);
            return res;
        }

        try {
            WebRequest request = new WebRequest(url);
            HtmlPage htmlPage = webClient.getPage(request);
            final String objId = getRandId();
            String objType = htmlPage.getClass().getSimpleName();
            fixObjectMap.put(objId, htmlPage);

            res.setStatus(SUCCESS);
            res.setFixObjectId(objId);
            res.setFixObjectType(objType);
            res.setBody(htmlPage.asXml());
            res.setMessage("Page " + urlStr + " loaded as obj: " + objId + ", type: " + objType);
        } catch (IOException e) {
            res.setMessage(e.getMessage());
            res.setBody(e.toString());
            res.setStatus(ERROR);
            e.printStackTrace();
        }

        return res;
    }


    public ResponseDto evalXpath(RequestDto request){
        ResponseDto response = new ResponseDto();
        if (request.getObjectId() == null || request.getObjectId().isEmpty()){
            response.setStatus(ERROR);
            response.setMessage("ObjectID must have value");
            return response;
        }

        DomNode object = null;
        try {
            object = (DomNode) fixObjectMap.get(request.getObjectId());
        } catch (Exception e){
            response.setStatus(ERROR);
            response.setMessage("Cannot get object: " + request.getObjectId());
            response.setBody(e.getMessage());
            return response;
        }

        if (object == null){
            response.setStatus(ERROR);
            response.setMessage("Object: " + request.getObjectId() + " not exist");
            return response;
        }


        if (request.getXpath() == null || request.getXpath().isEmpty())
            request.setXpath(".");
        try {
            List<DomNode> xpathRes = object.getByXPath(request.getXpath());
            response.setObjectCount(xpathRes.size());
            response.setStatus(SUCCESS);
            if (xpathRes.size() == 0){
                response.setMessage("Result no contains any element" );
                return response;
            }

            String objId = getRandId();
            DomNode dn = xpathRes.get(0);
            fixObjectMap.put(objId, dn);
            response.setFixObjectId(objId);
            response.setFixObjectType(dn.getClass().getSimpleName());
            response.setBody(dn.asXml());

            response.setMessage("Returned " + xpathRes.size() + " elem. Types is: " + dn.getClass().getSimpleName());

        } catch (Exception e){
            response.setStatus(ERROR);
            response.setMessage("Error while eval xpath: " + request.getXpath() + ", object: " + request.getObjectId());
            response.setBody(e.getMessage());
            return response;
        }

        return response;
    }


    public Map getObjectsMap (){

        Map<String, String> response = new HashMap();

        for (Map.Entry<String,Object> elemn : fixObjectMap.entrySet() ){
            response.put(elemn.getKey(), elemn.getValue().getClass().getSimpleName());
        }

        return response ;
    }



static String getRandId(){
   return RandomStringUtils.random(10, true, true);
}


    public static JavaScriptErrorListener putJSErrorListener() {

        return new JavaScriptErrorListener() {
            @Override
            public void scriptException(HtmlPage htmlPage, ScriptException e) {

            }

            @Override
            public void timeoutError(HtmlPage htmlPage, long l, long l1) {

            }

            @Override
            public void malformedScriptURL(HtmlPage htmlPage, String s, MalformedURLException e) {

            }

            @Override
            public void loadScriptError(HtmlPage htmlPage, URL url, Exception e) {

            }

            @Override
            public void warn(String s, String s1, int i, String s2, int i1) {

            }

        };

    }

}
