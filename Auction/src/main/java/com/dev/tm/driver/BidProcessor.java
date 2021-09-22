/**
 * 
 */
package com.dev.tm.driver;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.dev.tm.service.BidExecutionService;
import com.dev.tm.service.BidPopulationService;
import com.dev.tm.service.BidService;
import com.dev.tm.service.BidStartService;

/**
 * @author My PC
 *
 */
public class BidProcessor {
	
	static Map<Integer,BidService> bidServiceMap = new HashMap<Integer, BidService>();
	static Map<String,Object> bidsMap = new HashMap<String, Object>();
	static Map<String,Object> bidsExeMap = new LinkedHashMap<String, Object>();
	static Map<String,Object> bidsSellMap = new LinkedHashMap<String, Object>();
	
	static {
		bidServiceMap.put(1,new BidExecutionService());
		bidServiceMap.put(6,new BidStartService());
		bidServiceMap.put(5,new BidPopulationService());
	}
	
	public static void runBidProcessor(Integer numbeOfParams,String inp) {
		if(bidServiceMap.get(numbeOfParams)!=null) {
			bidServiceMap.get(numbeOfParams).runService(inp, bidsMap, bidsExeMap,bidsSellMap);
		}
	}

}
