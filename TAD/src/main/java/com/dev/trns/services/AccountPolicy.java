package com.dev.trns.services;

import com.dev.trns.utils.DateUtil;

import java.util.Map;

public class AccountPolicy implements PolicyService {

    @Override
    public boolean runPolicy(String prevRec, String newRec, Map<String, String> latestStr) {
        String prevDate,currentDate;
        prevDate = prevRec.split(",")[1];
        currentDate = newRec.split(",")[1];
        String currentSrcAcc,currentToAcc;
        currentSrcAcc = newRec.split(",")[3];
        currentToAcc = newRec.split(",")[4];
        String newStr=",valid";
        if(!DateUtil.findDifference(prevDate,currentDate)) {
            if(currentToAcc.equalsIgnoreCase(currentSrcAcc)) {
                newStr = ",fraud";
                latestStr.put("rec",latestStr.get("rec")+newStr);
                return false;
            }
        }
        return true;
    }
}
