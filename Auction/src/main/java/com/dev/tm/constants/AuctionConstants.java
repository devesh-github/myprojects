/**
 * 
 */
package com.dev.tm.constants;

/**
 * @author My PC
 *
 */
public class AuctionConstants {
	
	public static final String USER_LISTING_PARAM = "timestamp|user_id|action|item|reserve_price|close_time";
	public static final String BIDS_PARAMS = "timestamp|user_id|action|item|bid_amount";
	public static final String OUT_PARAMS = "close_time|item|user_id|status|price_paid|total_bid_count|highest_bid|lowest_bid";
	public static final String TIMESTAMP = "timestamp";
	public static final String USER_ID = "user_id";
	public static final String ACTION = "action";
	public static final String ITEM = "item";
	public static final String RESERVE_PRICE = "reserve_price";
	public static final String CLOSE_TIME = "close_time";
	public static final String BID_AMOUNT = "bid_amount";
	public static final String STATUS = "status";
	public static final String PRICE_PAID = "price_paid";
	public static final String TOTAL_BID_COUNT = "total_bid_count";
	public static final String HIGHEST_BID = "highest_bid";
	public static final String LOWEST_BID = "lowest_bid";
	public static final String BID_HISTORY = "bid_history";
	public static final String VALID_BID_HISTORY = "valid_bid_history";
	
	public static final String STATUS_SOLD = "SOLD";
	public static final String STATUS_UNSOLD = "UNSOLD";
	
	public static final String DELIMETER = "\\|";
	public static final String DELIMETER_PIPE = "|";
	
	public static final String OUTPUT_PATH =""; // Once provide can be put here from properties file
	public static final String OUTPUT_FILE = OUTPUT_PATH+"output.txt";
	public static final String INPUT_PATH =""; // Once provide can be put here from properties file
	public static final String INPUT_FILE = INPUT_PATH+"input.txt";
	
	public static final String INPUT_PROP_FILE = "input.file";
	public static final String OUTPUT_PROP_FILE = "output.file";

}
