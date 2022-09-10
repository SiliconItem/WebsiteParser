package com.ex.siteparser.controller;


import com.ex.siteparser.model.RequestDto;
import com.ex.siteparser.service.RestService;
import com.ex.siteparser.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private RestService restService;


    @RequestMapping(value = "/initPage", method = RequestMethod.GET,
    produces = "application/json")
    public ResponseDto met1(@RequestParam String url){

        return restService.job1(url);

    }

    @RequestMapping(value = "/getObjectList", method = RequestMethod.GET,
            produces = "application/json")
    public Map<String, Object>  met2(){
        return restService.getObjectsMap();
    }

    @RequestMapping(value = "/evalxpath", method = RequestMethod.POST,
            produces = "application/json")
    public ResponseDto  met2(@RequestBody RequestDto dto){
        return restService.evalXpath(dto);
    }
}
