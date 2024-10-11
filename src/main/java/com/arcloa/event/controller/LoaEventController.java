package com.arcloa.event.controller;

import com.arcloa.comm.sslVerification;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/event")
public class LoaEventController {

    private final HttpHeaders httpHeaders;

    @Value("${loa.base.url}")
    private String baseUrl;

    @Autowired
    public LoaEventController(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    @RequestMapping("/get")
    public ResponseEntity<Object> getLoaEvent() throws ParseException {

        HttpEntity<String> entity = new HttpEntity<>("",httpHeaders);

        sslVerification.disableSslVerification();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> respEntity = restTemplate.exchange(baseUrl + "news/events", HttpMethod.GET, entity, String.class);


        ObjectMapper objectMapper = new ObjectMapper();
        Object responseMap;

        try {
            responseMap = objectMapper.readValue(respEntity.getBody(), new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("JSON Parsing Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}