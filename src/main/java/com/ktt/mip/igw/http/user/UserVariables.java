package com.ktt.mip.igw.http.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserVariables {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    public Map<String, ?> populate(Object payload) {

        Map<String, Object> variables = new HashMap<>();

        logger.info(payload.toString());

        if(payload instanceof String) {
            variables.put("user", payload);
        } else {
            variables.put("user", EXPRESSION_PARSER.parseExpression("pathVariables.userId"));
        }

        return variables;
    }
}
