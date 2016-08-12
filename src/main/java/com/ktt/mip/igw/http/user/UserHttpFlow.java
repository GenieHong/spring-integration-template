package com.ktt.mip.igw.http.user;

import com.ktt.mip.igw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.http.Http;
import org.springframework.integration.mapping.HeaderMapper;

@Configuration
public class UserHttpFlow {

    @Autowired
    private ExpressionParser parser;

    @Autowired
    private HeaderMapper<HttpHeaders> headerMapper;


    private final String MIME_TYPE_JSON = "application/json";

    /**
     * REST to REST
     * @return
     */
//    @Bean
//    public IntegrationFlow userQueryFlow() {
//
//
//        return IntegrationFlows.from(Http.inboundGateway("/user/{userId}")
//                .requestMapping(r -> r.methods(HttpMethod.GET)
//                        .consumes(MIME_TYPE_JSON)
//                        .produces(MIME_TYPE_JSON))
//                .payloadExpression(parser.parseExpression("#pathVariables.userId"))
//                .headerMapper(headerMapper))
//                .channel("httpGetChannel")
//                .handle(Http.outboundGateway("http://localhost:8080/remote/user/{user}")
//                        .charset("UTF-8")
//                        .httpMethod(HttpMethod.GET)
//                        .headerMapper(headerMapper)
//                        .expectedResponseType(User.class)
//                        .uriVariablesExpression("@userVariables.populate(payload)")
//                ).get();
//    }


    @Bean
    public IntegrationFlow userQueryJDBCFlow() {
        return IntegrationFlows.from(Http.inboundGateway("/user/{userId}")
                .requestMapping(r -> r.methods(HttpMethod.GET)
                        .consumes(MIME_TYPE_JSON)
                        .produces(MIME_TYPE_JSON))
                .payloadExpression(parser.parseExpression("#pathVariables.userId"))
                .headerMapper(headerMapper))
                .channel("userHttpChannel")
                .handle("userService", "check").get();
                //.handle(jdbcOutboundGateway()).get();
    }

//
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:setup-tables.sql")
//                .build();
//    }
//
//    @Bean
//    public RowMapper<?> personMapper() {
//        return null;//new PersonMapper();
//    }
//
//    @Bean
//    public SqlParameterSourceFactory requestSource() {
//        ExpressionEvaluatingSqlParameterSourceFactory sourceFactory = new ExpressionEvaluatingSqlParameterSourceFactory();
//        Map<String, String> params = new HashMap<>();
//        params.put("name", "payload.name.toUpperCase()");
//        params.put("gender", "payload.gender.identifier");
//        params.put("dateOfBirth", "payload.dateOfBirth");
//        sourceFactory.setParameterExpressions(params);
//        return sourceFactory;
//    }
//
//    @Bean
//    public SqlParameterSourceFactory replySource() {
//        ExpressionEvaluatingSqlParameterSourceFactory sourceFactory = new ExpressionEvaluatingSqlParameterSourceFactory();
//        sourceFactory.setParameterExpressions(Collections.singletonMap("id", "#this['SCOPE_IDENTITY()']"));
//        return sourceFactory;
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "createPersonRequestChannel")
//    public MessageHandler jdbcOutboundGateway() {
//        JdbcOutboundGateway gateway = new JdbcOutboundGateway(dataSource(),
//                "insert into Person (name,gender,dateOfBirth) values(:name,:gender,:dateOfBirth)",
//                "select * from Person where id = :id");
//        gateway.setRowMapper(personMapper());
//        gateway.setKeysGenerated(true);
//        gateway.setRequestSqlParameterSourceFactory(requestSource());
//        gateway.setReplySqlParameterSourceFactory(replySource());
//        return gateway;
//    }


}
