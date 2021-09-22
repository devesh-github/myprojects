package com.dev.trns.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void findDifference() {
        DateUtil du = new DateUtil();
        du.findDifference("2021-01-01 14:06:34,1.32.78.191","2021-01-01 14:06:34,1.32.78.191");
    }
}