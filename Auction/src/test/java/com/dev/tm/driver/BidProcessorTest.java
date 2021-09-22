package com.dev.tm.driver;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.dev.tm.constants.AuctionConstants.ITEM;
import static com.dev.tm.constants.AuctionConstants.VALID_BID_HISTORY;

public class BidProcessorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testRunBidProcessorPopulateInvalid1() {
		BidProcessor.runBidProcessor(5, "12|8|BID|toaster_1|7.50");
		assertTrue(BidProcessor.bidsExeMap.isEmpty());
		assertTrue(BidProcessor.bidsSellMap.isEmpty());
		assertTrue(BidProcessor.bidsMap.isEmpty());
	}
	
	//@Test
	public void testRunBidProcessorPopulateInValid2() {
		BidProcessor.runBidProcessor(5, "13|5|BID|toaster_1|12.50");
		assertTrue(BidProcessor.bidsExeMap.isEmpty());
		assertTrue(BidProcessor.bidsSellMap.isEmpty());
		assertTrue(BidProcessor.bidsMap.isEmpty());
	}

	@Test
	public void testRunBidProcessorStart() {
		BidProcessor.runBidProcessor(6, "10|1|SELL|toaster_1|10.00|20");
		assertTrue(BidProcessor.bidsSellMap.size()==1);
	}
	
	@Test
	public void testRunBidProcessorPopulateInvalid3() {
		BidProcessor.runBidProcessor(6, "10|1|SELL|toaster_1|10.00|20");
		BidProcessor.runBidProcessor(5, "12|8|BID|toaster_1|7.50");
		assertTrue(BidProcessor.bidsSellMap.size()==1);
		assertTrue(BidProcessor.bidsMap.size()==1);
		assertTrue(BidProcessor.bidsExeMap.size()==1);
	}
	
	@Test
	public void testRunBidProcessorPopulateValid() {
		BidProcessor.runBidProcessor(6, "10|1|SELL|toaster_1|10.00|20");
		BidProcessor.runBidProcessor(5, "13|5|BID|toaster_1|12.50");
		assertTrue(BidProcessor.bidsSellMap.size()==1);
		assertTrue(BidProcessor.bidsMap.size()==1);
		assertTrue(BidProcessor.bidsExeMap.size()==1);
	}
	
	@Test
	public void testRunBidProcessorPopulateValid2() {
		BidProcessor.runBidProcessor(6, "20|1|SELL|toaster_1|10.00|30");
		BidProcessor.runBidProcessor(5, "23|5|BID|toaster_1|12.50");
		BidProcessor.runBidProcessor(5, "27|8|BID|toaster_1|20.00");
		assertTrue(BidProcessor.bidsSellMap.size()==1);
		assertTrue(BidProcessor.bidsMap.size()==1);
		LinkedHashMap<BigDecimal,String> validBidsHistoryMap = (LinkedHashMap<BigDecimal, String>) ((Map<String,Object>)BidProcessor.bidsMap.get("toaster_1")).get(VALID_BID_HISTORY);
		assertTrue(validBidsHistoryMap.size()==2);
		assertTrue(BidProcessor.bidsExeMap.size()==1);
	}
	
	@Test
	public void testRunBidProcessorExe() {
		BidProcessor.runBidProcessor(6, "10|1|SELL|toaster_1|10.00|20");
		BidProcessor.runBidProcessor(5, "12|8|BID|toaster_1|7.50");
		BidProcessor.runBidProcessor(1,"");
	}

}
