package com.dev.tm.service;

import static com.dev.tm.constants.AuctionConstants.CLOSE_TIME;
import static com.dev.tm.constants.AuctionConstants.ITEM;
import static com.dev.tm.constants.AuctionConstants.USER_LISTING_PARAM;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dev.tm.util.AuctionUtil;


/**
 * @author My PC
 *
 */
public class BidStartService implements BidService {
	
	@Override
	public void runService(String params, Map<String,Object> bidsMap, Map<String, Object> bidsExeMap, Map<String, Object> bidsSellMap) {
		AuctionUtil au = new AuctionUtil();
		Map<String,String> inpParamsMap = au.getMapFromInpString(params, USER_LISTING_PARAM);
		Map<String,Object> inpItemParamsMap = new LinkedHashMap<String,Object>();
		inpItemParamsMap.put(inpParamsMap.get(ITEM),inpParamsMap);
		if(!bidsExeMap.isEmpty()) {
			if(bidsExeMap.get(inpParamsMap.get(CLOSE_TIME))!=null) {
				LinkedHashMap<String,Object> inpItemParamsMap1 = (LinkedHashMap<String, Object>) bidsExeMap.get(inpParamsMap.get(CLOSE_TIME));
				inpItemParamsMap1.put(inpParamsMap.get(ITEM),inpParamsMap);
				bidsExeMap.put(inpParamsMap.get(CLOSE_TIME),inpItemParamsMap1);
			}
		} else {
			bidsExeMap.put(inpParamsMap.get(CLOSE_TIME),inpItemParamsMap);
		}
		bidsSellMap.put(inpParamsMap.get(ITEM), inpParamsMap);
	}
	
}
