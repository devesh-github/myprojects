package com.dev.trns.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class TrnsUtil {

    public static void extractTrns(int counter, String line, Map<String, String> latestStr) {
        if(counter ==1) {
            latestStr.put("rec", (latestStr.get("rec")==null?"":latestStr.get("rec"))+ line +",TrnsType");
        } else {
            latestStr.put("rec", latestStr.get("rec") + line);
        }
    }

    public String getProperty(String str) throws Exception {
        InputStream in;
        Properties prop = new Properties();
        in = new FileInputStream("application.properties");
        prop.load(in);
        return prop.getProperty(str);
    }

}
