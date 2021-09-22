package com.dev.trns.services;

import com.dev.trns.utils.DateUtil;

import java.util.Map;

public class DatePolicy implements PolicyService {

    @Override
    public boolean runPolicy(String prevRec, String newRec, Map<String, String> latestStr) {
        String prevDate,currentDate;
        prevDate = prevRec.split(",")[1];
        currentDate = newRec.split(",")[1];
        String prevIp,currentIp;
        prevIp = prevRec.split(",")[2];
        currentIp = newRec.split(",")[2];
        String prevAcc,currentAcc;
        prevAcc = prevRec.split(",")[3];
        currentAcc = newRec.split(",")[3];
        String newStr=",valid";
        if(!DateUtil.findDifference(prevDate,currentDate)) {
            if(prevAcc.equalsIgnoreCase(currentAcc) && !prevIp.equalsIgnoreCase(currentIp)) {
                newStr = ",fraud";
                latestStr.put("rec",latestStr.get("rec")+newStr);
                return false;
            }
        }
        return true;
    }
}
