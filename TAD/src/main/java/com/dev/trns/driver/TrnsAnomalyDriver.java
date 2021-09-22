package com.dev.trns.driver;

import com.dev.trns.utils.TrnsUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.dev.trns.constant.TrnsConstants.*;
import static com.dev.trns.services.TrnsValidationService.runBasicValidation;
import static com.dev.trns.utils.TrnsUtil.extractTrns;

public class TrnsAnomalyDriver {

    public static void main(String[] args) {
        TrnsAnomalyDriver tad = new TrnsAnomalyDriver();
        TrnsUtil tu = new TrnsUtil();
        try {
            tad.readInputFileUsingChunks(tu.getProperty(INPUT_FILE), tu.getProperty(OUTPUT_FILE));
        } catch (Exception e) {
            System.out.print("Got exception" + e);
        }
    }

    public void readInputFileUsingChunks(String source, String target) throws IOException,Exception {
        try (
                InputStream inputStream = new FileInputStream(source);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                OutputStream outputStream = new FileOutputStream(target);
        ) {
            TrnsUtil tu = new TrnsUtil();
            byte[] buffer = new byte[4 * 1024];
            int read;
            int counter = 1;
            while ((read = bufferedInputStream.read(buffer, 0, buffer.length)) != -1) {
                String myString = new String(buffer);
                String prevRec=null, currRec=null,line;
                Scanner scanner = new Scanner(myString);
                Map<String, String> latestStr = new HashMap<>();
                while (scanner.hasNextLine()) {
                    boolean isValid = true;
                    line = scanner.nextLine();
                    extractTrns(counter, line, latestStr);
                    if(counter>1) {
                        //Validate basic transaction details
                        if(runBasicValidation(line)) {
                            //Run Policies
                            if (prevRec == null) {
                                prevRec = line;
                                //latestStr.put("rec",latestStr.get("rec") + ",valid");
                            } else {
                                currRec = line;
                                //Find and assign anomaly
                                for (String policy : tu.getProperty(POLICY_LIST).split(",")) {
                                    if(!PolicyExecutor.executePolicy(policy, prevRec, currRec, latestStr)) {
                                        isValid = false;
                                        break;
                                    }
                                }
                                if(isValid){
                                    latestStr.put("rec",latestStr.get("rec")+",valid");
                                }
                                prevRec = line;
                            }
                        }
                    }
                    counter++;
                    latestStr.put("rec", latestStr.get("rec") + "\n");
                }
                scanner.close();
                buffer = latestStr.get("rec").getBytes(StandardCharsets.UTF_8);
                //outputStream.write(buffer);
                outputStream.write(buffer, 0, buffer.length);
            }
        }
    }

}
