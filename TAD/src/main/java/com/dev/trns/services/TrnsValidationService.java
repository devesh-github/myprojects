package com.dev.trns.services;

import org.apache.commons.validator.routines.InetAddressValidator;

import java.util.Arrays;

import static com.dev.trns.constant.TrnsConstants.VALID_STATUSCODE_LIST;

public class TrnsValidationService {

    public static boolean runBasicValidation(String trns) {
        //Record validation
        if(trns.split(",").length!=8) {
            return false;
        }

        String tranxId = trns.split(",")[0];
        String timestamp = trns.split(",")[1];
        String sourceIP = trns.split(",")[2];
        String fromAccount = trns.split(",")[3];
        String toAccount = trns.split(",")[4];
        String statusCode = trns.split(",")[5];
        String currency = trns.split(",")[6];
        String amount = trns.split(",")[7];

        for(String str: trns.split(",")) {
            if(str==null || str.isEmpty()) {
                return false;
            }
        }

        //IP validation
        InetAddressValidator validator = InetAddressValidator.getInstance();
        if (!validator.isValidInet4Address(sourceIP)) {
            return false;
        }

        //StatusCode validation
        if(!Arrays.asList(VALID_STATUSCODE_LIST.split(",")).contains(statusCode)) {
            return false;
        }

        return true;
    }

}
