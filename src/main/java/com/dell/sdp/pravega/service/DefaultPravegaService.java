package com.dell.sdp.pravega.service;

import com.dell.sdp.pravega.common.DataGenerator;
import com.dell.sdp.pravega.common.PravegaProperties;
import com.dell.sdp.pravega.common.serialization.JsonNodeSerializer;
import com.dell.sdp.pravega.util.PravegaServiceUtil;
import io.pravega.client.ClientConfig;
import io.pravega.client.EventStreamClientFactory;
import io.pravega.client.admin.StreamManager;
import io.pravega.client.stream.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.net.SocketException;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;

@Configuration
public class DefaultPravegaService implements PravegaService {

	 // Logger initialization
    private static final Logger LOG = LoggerFactory.getLogger(DefaultPravegaService.class);

    @Autowired
    PravegaProperties pravegaProperties;

    @Value("${pravega.controller_uri}")
    private String uri;

    @Value("${pravega.scope}")
    private String scope;

    @Value("${pravega.stream}")
    private String stream;

    @Value("${pravega.client_auth_method}")
    private String client_auth_method;

    @Value("${pravega.client_auth_loadDynamic}")
    private String client_auth_loadDynamic;

    @Value("${pravega.keycloak_service_account}")
    private String keycloak_service_account;

    @Value("${pravega.target_events_per_sec}")
    private int target_events_per_sec;

    @Value("${pravega.scale_factor}")
    private int scale_factor;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private PravegaServiceUtil pravegaServiceUtil;

    private EventStreamWriter<JsonNode> writer = null;



    @PostConstruct
    private void postConstructor()
    {
        try {
            System.out.println("@@@@@@@@@@@@@ URI  @@@@@@@@@@@@@  "+uri);
            URI controllerURI = URI.create(uri);

            //Resource resource = resourceLoader.getResource("classpath:certs/dsip-psearch_truststore.jks");
           /* Resource resource = resourceLoader.getResource("classpath:certs/dsip.crt");
            File certAsFile = resource.getFile();
            System.out.println("@@@@@@@@@@@@@ certAsFile PATH  @@@@@@@@@@@@@  "+certAsFile.getPath());
            System.out.println("@@@@@@@@@@@@@ certAsFile exists  @@@@@@@@@@@@@  "+certAsFile.exists());*/

            //setTrustStore(certAsFile.getPath(), "changeit");

            ClientConfig clientConfig = ClientConfig.builder()
                    //.credentials(null)
                    .controllerURI(controllerURI)
                    //.trustStore(certAsFile.getPath())
                    .validateHostName(false)
                    .build();


            StreamManager streamManager = StreamManager.create(clientConfig);

            // pravega.retentionPolicy.param=86400000  1 day
            StreamConfiguration streamConfig = StreamConfiguration.builder()
                    .scalingPolicy(ScalingPolicy.byEventRate(
                            target_events_per_sec, scale_factor, 5))
                    .retentionPolicy(RetentionPolicy.byTime(Duration.ofMillis(86400000 * 3)))
                    .build();

            streamManager.createStream(scope, stream, streamConfig);

            EventStreamClientFactory clientFactory = EventStreamClientFactory.withScope(scope, clientConfig);
            // Create  Pravega event writer
            writer = clientFactory.createEventWriter(
                    stream,
                    new JsonNodeSerializer(),
                    EventWriterConfig.builder().build());
            System.out.println("@@@@@@@@@@@@@ Created Pravega Writer  @@@@@@@@@@@@@  "+writer);

        } catch (Exception e) {
            System.out.println("@@@@@@@@@@@@@ Constructor ERROR  @@@@@@@@@@@@@  " + e.getMessage());
        }
    }

    /*@Async
    public void writeData(JsonNode message) {
        try {

            final CompletableFuture writeFuture = writer.writeEvent("RoutingKey", message);
            writeFuture.get();
            long threadNo = Thread.currentThread().getId();

            System.out.println("@@@@@@@@@@@@@ threadNo  @@@@@@@@@@@@@  " + threadNo);

        } catch (Exception e) {
                e.printStackTrace();
                System.out.println("@@@@@@@@@@@@@ ERROR  @@@@@@@@@@@@@  " + e.getMessage());
            }

    }*/

    @Override
    //@Async
    public void write() {
        System.out.println("DefaultPravegaService START");

        try {

           //while(true)
            for(int i = 0; i < 250000; i++)
            {
                //  Coverst CSV  data to JSON
                JsonNode message = new DataGenerator().getE2EvEvent();
                // Deserialize the JSON message.
                //LOG.info("COUNT : " + i + "  @@@@@@@@@@@@@ E2Ev  EVENT  @@@@@@@@@@@@@  " + message.toString());
                System.out.println("COUNT : " + i + "  @@@@@@@@@@@@@ E2Ev  EVENT  @@@@@@@@@@@@@  " + message.toString());
                pravegaServiceUtil.writeData(writer,message);
                Thread.sleep(50);
                //writer.flush();
                /*final CompletableFuture writeFuture = writer.writeEvent("RoutingKey", message);
                writeFuture.get();*/


            }

        }
    /* catch (SocketException se) {
        se.printStackTrace();
         System.out.println("@@@@@@@@@@@@@ SocketException  @@@@@@@@@@@@@  " + se.getMessage());
        *//* insert your failure processings here *//*
    }*/
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("@@@@@@@@@@@@@ ERROR  @@@@@@@@@@@@@  " + e.getMessage());
        }
    }
}
