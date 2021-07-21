package com.dell.sdp.pravega.util;

import com.fasterxml.jackson.databind.JsonNode;
import io.pravega.client.stream.EventStreamWriter;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class PravegaServiceUtil {
    @Async
    public void writeData(EventStreamWriter<JsonNode> writer,JsonNode message) {
        try {
            long threadNo = Thread.currentThread().getId();
            String threadName = Thread.currentThread().getName();

            System.out.println("@@@@@@@@@@@@@ threadNo  :  " + threadNo+ "  threadName : "+threadName);

            final CompletableFuture writeFuture = writer.writeEvent("RoutingKey", message);
            Object b = writeFuture.get();
            //Thread.sleep(500);
           System.out.println("######## EVENT WROTE SUCCESSFULLY  ########### "+b);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@@@@@@@@@@@@@ ERROR  @@@@@@@@@@@@@  " + e.getMessage());
        }

    }
}
