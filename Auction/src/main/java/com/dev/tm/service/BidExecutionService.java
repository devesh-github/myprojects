package com.dev.tm.service;

import static com.dev.tm.constants.AuctionConstants.CLOSE_TIME;
import static com.dev.tm.constants.AuctionConstants.DELIMETER;
import static com.dev.tm.constants.AuctionConstants.DELIMETER_PIPE;
import static com.dev.tm.constants.AuctionConstants.OUTPUT_FILE;
import static com.dev.tm.constants.AuctionConstants.OUT_PARAMS;
import static com.dev.tm.constants.AuctionConstants.PRICE_PAID;
import static com.dev.tm.constants.AuctionConstants.RESERVE_PRICE;
import static com.dev.tm.constants.AuctionConstants.STATUS;
import static com.dev.tm.constants.AuctionConstants.STATUS_SOLD;
import static com.dev.tm.constants.AuctionConstants.STATUS_UNSOLD;
import static com.dev.tm.constants.AuctionConstants.USER_ID;
import static com.dev.tm.constants.AuctionConstants.HIGHEST_BID;
import static com.dev.tm.constants.AuctionConstants.OUTPUT_PROP_FILE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.dev.tm.util.AuctionUtil;


/**
 * @author My PC
 *
 */
public class BidExecutionService implements BidService {
	
	private AuctionUtil au = new AuctionUtil();

	@Override
	public void runService(String params, Map<String,Object> bidsMap, Map<String, Object> bidsExeMap, Map<String, Object> bidsSellMap) {
		if(!bidsExeMap.isEmpty()) {
			HashMap<String,Object> bidsItemExeMap = (HashMap<String,Object>)bidsExeMap.get(params);
			if(bidsItemExeMap!=null && !bidsItemExeMap.isEmpty()){
				runExecution(bidsMap,bidsExeMap,bidsItemExeMap,params);
			}
		}
	}
	
	public void runExecution(Map<String,Object> bidsMap, Map<String, Object> bidsExeMap,Map<String, Object> inpItemParamsMap,String closeTime) {
		for (Map.Entry<String, Object> entry : inpItemParamsMap.entrySet()) {
			Map<String, Object> bids = (Map<String, Object>) bidsMap.get(entry.getKey());
			//System.out.println("bids:"+bids);
			String status = calculateStatus((Map<String, Object>)entry.getValue(),bids);
			Float maxPricePaid = getMaxPricePaid((Map<String, Object>)entry.getValue(), bids);
			int count =0;
			StringBuilder sb = new StringBuilder();
			for(String out:OUT_PARAMS.split(DELIMETER)) {
				if(count!=OUT_PARAMS.split(DELIMETER).length-1){
					if(out.equalsIgnoreCase(CLOSE_TIME)) {
						sb.append(closeTime).append(DELIMETER_PIPE);
					}else if(out.equalsIgnoreCase(STATUS)) {
						sb.append(status).append(DELIMETER_PIPE);
					}else if(STATUS_UNSOLD.equalsIgnoreCase(status) && out.equalsIgnoreCase(USER_ID)) {
						sb.append("").append(DELIMETER_PIPE);
					}else if(STATUS_UNSOLD.equalsIgnoreCase(status) && out.equalsIgnoreCase(PRICE_PAID)) {
						sb.append("0").append(DELIMETER_PIPE);
					}else if(STATUS_SOLD.equalsIgnoreCase(status) && out.equalsIgnoreCase(PRICE_PAID)) {
						sb.append(maxPricePaid).append(DELIMETER_PIPE);
					}else {
						sb.append(bids.get(out)).append(DELIMETER_PIPE);
					}
				} else {
					sb.append(bids.get(out));
				}
				count++;
			}
			
			//Write the data
			try {
				au.appendUsingBufferedWriter(au.getPropFilePath(OUTPUT_PROP_FILE),sb.toString(),1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String calculateStatus(Map<String, Object> inpItemParamsMap,Map<String, Object> bids) {
		if(Float.parseFloat(inpItemParamsMap.get(RESERVE_PRICE).toString())<=
				Float.parseFloat(bids.get(HIGHEST_BID).toString())) {
			return STATUS_SOLD;
		} else {
			return STATUS_UNSOLD;
		}
	}
	
	private Float getMaxPricePaid(Map<String, Object> inpItemParamsMap,Map<String, Object> bids) {
		Float reservePrice = Float.parseFloat(inpItemParamsMap.get(RESERVE_PRICE).toString());
		Float highestBid = Float.parseFloat(bids.get(PRICE_PAID).toString());
		return Math.max(reservePrice, highestBid);
	}
	
}