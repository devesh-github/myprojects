package com.dev.tm.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import com.dev.tm.util.AuctionUtil;

import static com.dev.tm.constants.AuctionConstants.DELIMETER;
import static com.dev.tm.constants.AuctionConstants.INPUT_FILE;
import static com.dev.tm.constants.AuctionConstants.INPUT_PROP_FILE;

public class AuctionDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Read the file and trigger the process
		Scanner scanner = null;
		AuctionUtil au = new AuctionUtil();
		try {
			scanner = new Scanner(new File(au.getPropFilePath(INPUT_PROP_FILE)));
			while (scanner.hasNextLine()) {
				String inp = scanner.nextLine();
				Integer paramCount = inp.split(DELIMETER).length;
				BidProcessor.runBidProcessor(paramCount, inp);
			}
			System.out.println("Auction is successful");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

}
