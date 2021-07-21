package com.dell.sdp.pravega.common;

import com.dell.sdp.pravega.ActmonPravegaApplication;
import com.dell.sdp.pravega.model.JSONData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;

import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

    public String status;
    public String event = "PROCESS";
    public String global_transaction_id = UUID.randomUUID().toString();
    private long business_identifier;
    private long sequenceNumber;
    private long timestamp;

    public DataGenerator() {}

    public JsonNode getE2EvEvent()
    {
        JsonNode data = null;

        // create E2EV object

        //Example Usage:
        String[] statuses = {"STARTED", "INPROGRESS", "COMPLETE", "FAILED"};
        String status = getRandomElement(statuses);
        //System.out.println("==== STATUS ====  "+status);

        String[] bids = {"224920","PO30","PO31","US-155","0000002670","7500003700","6500034229","PCO483"};
        String bid = getRandomElement(bids);
        //System.out.println("==== BID ====  " +bid);

        JSONData dataObj = new JSONData(status, "PROCESS", "eventCode", "eventSubcode",
                "ws.imap.aic.response.out", "summary", "detail", bid, "alternateBusinessIdentifier",
                UUID.randomUUID().toString(), "transactionMode",  "ERP", "DEV",
                "serviceName", new Date().toString(), UUID.randomUUID().toString(), "serviceVersion", "CIC",
                "SearchAD5WebserviceImap", UUID.randomUUID().toString(), "ForkJoinPool.commonPool-worker-0", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb cccccccccccccccccccccccccccccccccccccccc ddddddddddddddddddddddddddddddddddddddddddddddddd eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee fffffffffffffffffffffffffffffffffffff ggggggggggggggggggggggggggggggggggg hhhhhhhhhhhhhhhhhhhhhh", "AIC",  "no",
                "[{\\\"name\\\":\\\"EMC.entityKey\\\",\\\"value\\\":\\\"dell\\\"},{\\\"name\\\":\\\"replyChannel\\\",\\\"value\\\":\\\"org.springframework.messaging.core.GenericMessagingTemplate$TemporaryReplyChannel@650c59d5\\\"},{\\\"name\\\":\\\"errorChannel\\\",\\\"value\\\":\\\"org.springframework.messaging.core.GenericMessagingTemplate$TemporaryReplyChannel@650c59d5\\\"},{\\\"name\\\":\\\"EMC.businessIdentifier\\\",\\\"value\\\":\\\"dell\\\"},{\\\"name\\\":\\\"EMC.globalTransactionId\\\",\\\"value\\\":\\\"00000178-4b2b-55d1-0000-01784b2b55d1\\\"},{\\\"name\\\":\\\"id\\\",\\\"value\\\":\\\"cf9f663b-926e-631d-f3c6-82e3f3b37ea1\\\"},{\\\"name\\\":\\\"EMC.processId\\\",\\\"value\\\":\\\"UNDEFINED\\\"},{\\\"name\\\":\\\"PayloadContext.MessageProfile.Process\\\",\\\"value\\\":\\\"UNDEFINED\\\"},{\\\"name\\\":\\\"EMC.applicationId\\\",\\\"value\\\":\\\"SearchAD5WebserviceImap\\\"},{\\\"name\\\":\\\"timestamp\\\",\\\"value\\\":\\\"1616168834639\\\"}]");

        ObjectMapper mapper = new ObjectMapper();
        // convert user object to `JsonNode`
        JsonNode node = mapper.valueToTree(dataObj);
        return node;
    }

    public static <T> T getRandomElement(T[] arr){
        return arr[ThreadLocalRandom.current().nextInt(arr.length)];
    }

    //public static void main(String[] args) {
    public static void getMessageSize(String data) {

        JsonNode node = new DataGenerator().getE2EvEvent();
        String message = node.toString();
        int size = message.length() * 2;
        int maxSize = 1024 * 1024 * 8;
        System.out.println("==== SIZE in MB ====  "+size);
        if(size > maxSize)
        {
            System.out.println("==== SIZE MORE THAN 8 MB ====  ");
        }
        else
        {
            System.out.println("==== SIZE LESS THAN 8 MB ====  ");
        }

         /*File fileObj = new File("C:\\Users\\chennv4\\Downloads\\tmp\\DSIP_Message");
        String fileSizeReadable = FileUtils.byteCountToDisplaySize(fileObj.length());
        int maxSize = 1024 * 1024 * 8;
        System.out.println("==== SIZE in MB ====  "+fileSizeReadable);*/

        // Get file from file name
       /* File file = new File("U:\intranet_root\intranet\DSIP_Message");

        // Get length of file in bytes
                long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                long fileSizeInMB = fileSizeInKB / 1024;*/

    }
}
