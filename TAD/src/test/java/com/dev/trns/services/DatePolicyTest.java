package com.dev.trns.services;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class DatePolicyTest {

    @Test
    public void runPolicy() {
        DatePolicy dp = new DatePolicy();
        Map<String, String> pm = new HashMap<>();
        pm.put("rec","2063379,2021-01-01 15:48:19,2.17.77.191,409000362497,409001201044,1001,SGD,83");
        dp.runPolicy("1744313,2021-01-01 14:06:34,1.32.78.191,409000362497,409001085920,1001,SGD,30","2063379,2021-01-01 15:48:19,2.17.77.191,409000362497,409001201044,1001,SGD,83",pm);
        assertEquals(pm.get("rec"),"2063379,2021-01-01 15:48:19,2.17.77.191,409000362497,409001201044,1001,SGD,83,fraud");
    }
}