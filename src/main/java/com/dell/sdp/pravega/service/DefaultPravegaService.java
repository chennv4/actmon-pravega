package com.dell.sdp.pravega.service;

import com.dell.sdp.pravega.common.DataGenerator;
import com.dell.sdp.pravega.common.PravegaProperties;
import com.dell.sdp.pravega.common.serialization.JsonNodeSerializer;
import io.pravega.client.ClientConfig;
import io.pravega.client.EventStreamClientFactory;
import io.pravega.client.admin.StreamManager;
import io.pravega.client.stream.EventStreamWriter;
import io.pravega.client.stream.EventWriterConfig;
import io.pravega.client.stream.ScalingPolicy;
import io.pravega.client.stream.StreamConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

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


    @Override
    public void write() {
        LOG.info("DefaultPravegaService START");

        try {
            LOG.info("@@@@@@@@@@@@@ URI  @@@@@@@@@@@@@  "+uri);
            URI controllerURI = URI.create(uri);

            //Resource resource = resourceLoader.getResource("classpath:certs/dsip-psearch_truststore.jks");
            Resource resource = resourceLoader.getResource("classpath:certs/dsip.crt");
            File certAsFile = resource.getFile();
            LOG.info("@@@@@@@@@@@@@ certAsFile PATH  @@@@@@@@@@@@@  "+certAsFile.getPath());
            LOG.info("@@@@@@@@@@@@@ certAsFile exists  @@@@@@@@@@@@@  "+certAsFile.exists());

            //setTrustStore(certAsFile.getPath(), "changeit");

            ClientConfig clientConfig = ClientConfig.builder()
                    //.credentials(null)
                    .controllerURI(controllerURI)
                    .trustStore(certAsFile.getPath())
                    .validateHostName(false)
                    .build();


            StreamManager streamManager = StreamManager.create(clientConfig);
            
            StreamConfiguration streamConfig = StreamConfiguration.builder()
                    .scalingPolicy(ScalingPolicy.byEventRate(
                            target_events_per_sec, scale_factor, 1))
                    .build();
            streamManager.createStream(scope, stream, streamConfig);

            EventStreamClientFactory clientFactory = EventStreamClientFactory.withScope(scope, clientConfig);
            // Create  Pravega event writer
            EventStreamWriter<JsonNode> writer = clientFactory.createEventWriter(
                    stream,
                    new JsonNodeSerializer(),
                    EventWriterConfig.builder().build());
            while(true)
            {
                //  Coverst CSV  data to JSON
                JsonNode message = new DataGenerator().getE2EvEvent();
                // Deserialize the JSON message.
                LOG.info("@@@@@@@@@@@@@ E2Ev  EVENT  @@@@@@@@@@@@@  "+message.toString());
                final CompletableFuture writeFuture = writer.writeEvent("RoutingKey", message);
                writeFuture.get();
                Thread.sleep(10000);
            }

        } catch (Exception e) {
            LOG.error("@@@@@@@@@@@@@ ERROR  @@@@@@@@@@@@@  " + e.getMessage());
        }
    }
}
