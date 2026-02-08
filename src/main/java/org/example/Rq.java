package org.example;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    // 고객의 요청
    private Map<String, String> paramMap;
    private String actionName;

    // 생성자
    public Rq(String command) {
        paramMap = new HashMap<>();

        String[] commandBits = command.split("\\?");

        actionName = commandBits[0];
        String queryString = "";

        if (commandBits.length > 1) {
            queryString = commandBits[1];
        }

        String[] queryStringBits = queryString.split("&");

        for (String param : queryStringBits) {
            String[] paramBits = param.split("=");
            String key = paramBits[0];
            String value = null;

            if (paramBits.length > 1) {
                value = paramBits[1];
            }

            if (value == null) {
                continue;
            }

            paramMap.put(key, value);
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String key) {
        return paramMap.get(key);
    }
}
