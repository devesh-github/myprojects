package com.dev.tm.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.dev.tm.constants.AuctionConstants.DELIMETER;

public class AuctionUtil {
	
	/**
	 * Use BufferedWriter when number of write operations are more
	 * 
	 * @param filePath
	 * @param text
	 * @param noOfLines
	 */
	public static void appendUsingBufferedWriter(String filePath, String text, int noOfLines) {
		File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		try {
			// to append to file, you need to initialize FileWriter using below constructor
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			for (int i = 0; i < noOfLines; i++) {
				if(file.length()!=0) {
					br.newLine();
				}
				// you can use write or append method
				br.write(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Map<String,String> getMapFromInpString(String params,String paramNames) {
		Map<String,String> paramsMap = new HashMap<String,String>();
		int count = 0;
		String[] paramsStrings = params.split(DELIMETER);
		for(String paramName : paramNames.split(DELIMETER)) {
			paramsMap.put(paramName, paramsStrings[count]);
			count++;
		}
		return paramsMap;
	}
	
	public String getPropFilePath(String str) throws IOException  {
		InputStream input;
		Properties prop = new Properties();
		input = new FileInputStream("application.properties");
	    prop.load(input);
		return prop.getProperty(str);
	}

}
