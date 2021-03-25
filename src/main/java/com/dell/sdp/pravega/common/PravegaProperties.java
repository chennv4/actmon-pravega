package com.dell.sdp.pravega.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "pravega")
public class PravegaProperties {

    public String controller_uri;
    public String scope;
    public String stream;
    public String client_auth_method;
    public String client_auth_loadDynamic;
    public String keycloak_service_account;
    public int target_events_per_sec;
    public int scale_factor;
}
