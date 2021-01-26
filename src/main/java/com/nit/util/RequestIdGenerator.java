package com.nit.util;

import java.util.UUID;

public class RequestIdGenerator {
    static String traceId;

    public static String getTraceId() {

        return  traceId = UUID.randomUUID().toString().replaceAll("_","");
    }

}
