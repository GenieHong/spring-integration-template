package com.ktt.mip.igw.base;


import org.springframework.http.HttpMethod;
import org.springframework.integration.http.inbound.RequestMapping;

public class HttpFlowUtils {

    private static final String MIME_TYPE_JSON = "application/json";

    public static RequestMapping createRequestMappings(HttpMethod[] method, String... path) {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setMethods(method);
        requestMapping.setConsumes(MIME_TYPE_JSON);
        requestMapping.setProduces(MIME_TYPE_JSON);
        requestMapping.setPathPatterns(path);
        return requestMapping;
    }
}
