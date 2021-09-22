package com.dev.tm.service;

import static com.dev.tm.constants.AuctionConstants.BIDS_PARAMS;
import static com.dev.tm.constants.AuctionConstants.BID_AMOUNT;
import static com.dev.tm.constants.AuctionConstants.BID_HISTORY;
import static com.dev.tm.constants.AuctionConstants.CLOSE_TIME;
import static com.dev.tm.constants.AuctionConstants.HIGHEST_BID;
import static com.dev.tm.constants.AuctionConstants.ITEM;
import static com.dev.tm.constants.AuctionConstants.LOWEST_BID;
import static com.dev.tm.constants.AuctionConstants.PRICE_PAID;
import static com.dev.tm.constants.AuctionConstants.STATUS;
import static com.dev.tm.constants.AuctionConstants.STATUS_UNSOLD;
import static com.dev.tm.constants.AuctionConstants.TIMESTAMP;
import static com.dev.tm.constants.AuctionConstants.TOTAL_BID_COUNT;
import static com.dev.tm.constants.AuctionConstants.USER_ID;
import static com.dev.tm.constants.AuctionConstants.VALID_BID_HISTORY;
import static com.dev.tm.constants.AuctionConstants.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.dev.tm.util.AuctionUtil;

/**
 * @author My PC
 *
 */
public class BidPopulationService implements BidService {

	@Override
	public void runService(String params, Map<String,Object> bidsMap, Map<String, Object> bidsExeMap, Map<String, Object> bidsSellMap) {
		AuctionUtil au = new AuctionUtil();
		Map<String,String> paramsMap = au.getMapFromInpString(params, BIDS_PARAMS);
		if(bidsSellMap!=null && !bidsSellMap.isEmpty()) {
			if(bidsMap.get(paramsMap.get(ITEM))!=null) {
				TreeMap<BigDecimal,String> bidsHistoryMap = (TreeMap<BigDecimal, String>) ((Map<String,Object>)bidsMap.get(paramsMap.get(ITEM))).get(BID_HISTORY);
				Float key = Float.valueOf(paramsMap.get(BID_AMOUNT));
				BigDecimal bd = new BigDecimal(key).setScale(2, RoundingMode.HALF_UP);
				bidsHistoryMap.put(bd, paramsMap.get(USER_ID).toString());
				
				LinkedHashMap<BigDecimal,String> validBidsHistoryMap = (LinkedHashMap<BigDecimal, String>) ((Map<String,Object>)bidsMap.get(paramsMap.get(ITEM))).get(VALID_BID_HISTORY);
				if(isValidBid(paramsMap,validBidsHistoryMap,bidsSellMap)) {
					validBidsHistoryMap.put(bd, paramsMap.get(USER_ID).toString());
					System.out.println("validBidsHistoryMap:"+validBidsHistoryMap);
				}
				
				Integer bidCount = Integer.valueOf((String) ((Map<String,Object>)bidsMap.get(paramsMap.get(ITEM))).get(TOTAL_BID_COUNT));
				Map<String,Object> itemBidsMap = new HashMap<String,Object>();
				itemBidsMap.put(CLOSE_TIME,paramsMap.get(TIMESTAMP));
				itemBidsMap.put(ITEM,paramsMap.get(ITEM));	
				itemBidsMap.put(USER_ID,getWinningUserId(validBidsHistoryMap));
				itemBidsMap.put(STATUS,STATUS_UNSOLD);	
				itemBidsMap.put(PRICE_PAID,getPricePaid(validBidsHistoryMap,2));
				itemBidsMap.put(TOTAL_BID_COUNT,String.valueOf(bidCount+1));
				itemBidsMap.put(HIGHEST_BID,getBid(bidsHistoryMap,1));
				itemBidsMap.put(LOWEST_BID,getBid(bidsHistoryMap,bidsHistoryMap.size()));
				itemBidsMap.put(BID_HISTORY,bidsHistoryMap);
				itemBidsMap.put(VALID_BID_HISTORY,validBidsHistoryMap);
				itemBidsMap.put(BID_AMOUNT,paramsMap.get(BID_AMOUNT));
				
				bidsMap.put(paramsMap.get(ITEM), itemBidsMap);
			} else {
				Map<BigDecimal,String> bidsHistoryMap = new TreeMap<BigDecimal,String>(Collections.reverseOrder());
				Float key = Float.valueOf(paramsMap.get(BID_AMOUNT));
				BigDecimal bd = new BigDecimal(key).setScale(2, RoundingMode.HALF_UP);
				bidsHistoryMap.put(bd, paramsMap.get(USER_ID).toString());
				
				Map<String,String> sellMap = (Map<String, String>) bidsSellMap.get(paramsMap.get(ITEM));
				String userId = paramsMap.get(USER_ID);
				Float reservePrice = Float.valueOf(paramsMap.get(BID_AMOUNT));
				Map<BigDecimal,String> validBidsHistoryMap = new LinkedHashMap<BigDecimal,String>();
				if(isValidBid(paramsMap,validBidsHistoryMap,bidsSellMap)) {
					validBidsHistoryMap.put(bd, paramsMap.get(USER_ID).toString());
					//System.out.println(validBidsHistoryMap);
					reservePrice = Float.valueOf(sellMap.get(RESERVE_PRICE));
					userId = "";
				}
				BigDecimal reservePriceBD = new BigDecimal(reservePrice).setScale(2, RoundingMode.HALF_UP);
				
				Map<String,Object> itemBidsMap = new HashMap<String,Object>();
				itemBidsMap.put(CLOSE_TIME,paramsMap.get(TIMESTAMP));
				itemBidsMap.put(ITEM,paramsMap.get(ITEM));	
				itemBidsMap.put(USER_ID,userId);
				itemBidsMap.put(STATUS,STATUS_UNSOLD);	
				itemBidsMap.put(PRICE_PAID,reservePriceBD);
				itemBidsMap.put(TOTAL_BID_COUNT,"1");
				itemBidsMap.put(HIGHEST_BID,paramsMap.get(BID_AMOUNT));
				itemBidsMap.put(LOWEST_BID,paramsMap.get(BID_AMOUNT));
				itemBidsMap.put(BID_HISTORY,bidsHistoryMap);
				itemBidsMap.put(VALID_BID_HISTORY,validBidsHistoryMap);
				itemBidsMap.put(BID_AMOUNT,paramsMap.get(BID_AMOUNT));
				
				bidsMap.put(paramsMap.get(ITEM), itemBidsMap);
			}
		}
	}
	
	private boolean isValidBid(Map<String, String> paramsMap,Map<BigDecimal,String> validBidsHistoryMap,Map<String,Object> bidsSellMap) {
		Map<String,String> sellMap = (Map<String, String>) bidsSellMap.get(paramsMap.get(ITEM));
		Integer bidTS = Integer.valueOf(paramsMap.get(TIMESTAMP));
		Integer itemTS = Integer.valueOf(sellMap.get(TIMESTAMP));
		Integer itemCTS = Integer.valueOf(sellMap.get(CLOSE_TIME));
		Float reservePrice = Float.valueOf(sellMap.get(RESERVE_PRICE));
		BigDecimal reservePriceBD = new BigDecimal(reservePrice).setScale(2, RoundingMode.HALF_UP);
		Float key = Float.valueOf(paramsMap.get(BID_AMOUNT));
		BigDecimal bidAmount = new BigDecimal(key).setScale(2, RoundingMode.HALF_UP);
		
		if (bidTS > itemTS && bidTS < itemCTS
				&& bidAmount.floatValue() >= reservePriceBD.floatValue()) {
			int count = 0;
			if(validBidsHistoryMap.isEmpty()) {
				return true;
			} else if(validBidsHistoryMap.get(bidAmount)!=null) {
				return false;
			} else {
				for (Map.Entry<BigDecimal, String> entry : validBidsHistoryMap.entrySet()) {
					if (count == 0) {
						return bidAmount.floatValue() >= entry.getKey().floatValue();
					}
					count++;
				}
			}
		}
		return false;
	}

	public String getWinningUserId(LinkedHashMap<BigDecimal,String> bidsHistoryMap) {
		int count =0;
		for (Map.Entry<BigDecimal, String> entry : bidsHistoryMap.entrySet()) {
			if(count==bidsHistoryMap.size()-1) {
				return entry.getValue();
			}
			count++;
		}
		return "";
	}
	
	public BigDecimal getPricePaid(Map<BigDecimal,String> bidsHistoryMap,int number) {
		int count =0;
		int size = bidsHistoryMap.size();
		for (Map.Entry<BigDecimal, String> entry : bidsHistoryMap.entrySet()) {
			if(count==size-number) {
				return entry.getKey();
			}
			count++;
		}
		return new BigDecimal("0");
	}
	
	public BigDecimal getBid(Map<BigDecimal,String> bidsHistoryMap,int number) {
		int count =0;
		for (Map.Entry<BigDecimal, String> entry : bidsHistoryMap.entrySet()) {
			if(count==number-1) {
				return entry.getKey();
			}
			count++;
		}
		return new BigDecimal("0");
	}
	
}
