package com.arcloa.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * LostArk API 호출 시 사용되는 헤더 공통 Bean으로 등록
 */
@Configuration
public class HttpHeader {

    @Value("${loa.token.bearer}")
    private String token;

    @Bean
    @Scope("singleton")
    public HttpHeaders HttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        return headers;
    }
}
