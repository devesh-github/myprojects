package com.dev.trns.services;

import java.util.Map;

public interface PolicyService {

    boolean runPolicy(String prevRec, String newRec, Map<String, String> latestStr);
}
