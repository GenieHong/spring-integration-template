package com.ktt.mip.igw.person.flow;

import com.ktt.mip.igw.base.HttpFlowUtils;
import com.ktt.mip.igw.person.domain.ServerPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.gateway.MessagingGatewaySupport;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.mapping.HeaderMapper;

@Configuration
public class PersonHttpFlow {

    @Autowired
    private ExpressionParser parser;

    @Autowired
    private HeaderMapper<HttpHeaders> headerMapper;

    public MessagingGatewaySupport httpGetGateway() {
        HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
        handler.setRequestMapping(HttpFlowUtils.createRequestMappings(new HttpMethod[]{HttpMethod.GET}, "/persons/{personId}"));
        handler.setPayloadExpression(parser.parseExpression("#pathVariables.personId"));
        handler.setHeaderMapper(headerMapper);
        return handler;
    }

    @Bean
    public IntegrationFlow httpGetFlow() {
        return IntegrationFlows.from(httpGetGateway())
                .channel("httpGetChannel")
                .handle("personEndpoint", "get").get();
    }

    public MessagingGatewaySupport httpDelGateway() {
        HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
        handler.setRequestMapping(HttpFlowUtils.createRequestMappings(new HttpMethod[]{HttpMethod.DELETE}, "/persons/{personId}"));
        handler.setStatusCodeExpression(parser.parseExpression("T(org.springframework.http.HttpStatus).NO_CONTENT"));
        handler.setPayloadExpression(parser.parseExpression("#pathVariables.personId"));
        handler.setHeaderMapper(headerMapper);
        return handler;
    }

    @Bean
    public IntegrationFlow httpDeleteFlow() {
        return IntegrationFlows.from(httpDelGateway())
                .channel("httpDeleteChannel")
                .handle("personEndpoint", "delete")
                .get();
    }

    public MessagingGatewaySupport httpPostPutGateway() {
        HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
        handler.setRequestMapping(HttpFlowUtils.createRequestMappings(new HttpMethod[]{HttpMethod.PUT, HttpMethod.POST}, "/persons", "/persons/{personId}"));
        handler.setStatusCodeExpression(parser.parseExpression("T(org.springframework.http.HttpStatus).NO_CONTENT"));
        handler.setRequestPayloadType(ServerPerson.class);
        handler.setHeaderMapper(headerMapper);
        return handler;
    }

    @Bean
    public IntegrationFlow httpPostPutFlow() {
        return IntegrationFlows.from(httpPostPutGateway())
                .channel("routeRequest")
                .route("headers.http_requestMethod", m ->
                        m.prefix("http")
                                .suffix("Channel")
                                .channelMapping("PUT", "Put")
                                .channelMapping("POST", "Post")
                ).get();
    }

    @Bean
    public IntegrationFlow httpPostFlow() {
        return IntegrationFlows.from("httpPostChannel")
                .handle("personEndpoint", "post")
                .get();
    }

    @Bean
    public IntegrationFlow httpPutFlow() {
        return IntegrationFlows.from("httpPutChannel")
                .handle("personEndpoint", "put")
                .get();
    }










}
