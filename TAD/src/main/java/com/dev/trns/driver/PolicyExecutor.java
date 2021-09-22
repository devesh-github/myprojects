package com.dev.trns.driver;

import com.dev.trns.services.AccountPolicy;
import com.dev.trns.services.DatePolicy;
import com.dev.trns.services.PolicyService;

import java.util.HashMap;
import java.util.Map;

public class PolicyExecutor {

    static Map<String, PolicyService> policyMap = new HashMap<>();

    static {
        policyMap.put("ACCOUNT",new AccountPolicy());
        policyMap.put("DATE",new DatePolicy());
    }

    public static boolean executePolicy(String policy,String prevRec,String newRec,Map<String, String> latestStr) {
        if(policyMap.get(policy)!=null) {
            return policyMap.get(policy).runPolicy(prevRec,newRec,latestStr);
        }
        return true;
    }
}
