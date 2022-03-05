package com.dev.trns.driver;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TrnsAnomalyDriverTest {

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void main() {
    }

    @Test
    public void copyUsingChunks() {
        TrnsAnomalyDriver tad = new TrnsAnomalyDriver();
        String source = "G:\\Devesh\\myprojects\\TAD\\src\\test\\resources\\testinputdata.txt";
        String target = "G:\\Devesh\\myprojects\\TAD\\src\\test\\resources\\testoutputdata.txt";
        try {
            tad.readInputFileUsingChunks(source, target);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Got exception" + e);
        }
    }
}