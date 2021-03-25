package com.dell.sdp.pravega;

import com.dell.sdp.pravega.service.DefaultPravegaService;
import com.dell.sdp.pravega.service.PravegaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class ActmonPravegaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ActmonPravegaApplication.class, args);
    }

    @Bean
    public PravegaService getPravegaService(){
        return  new DefaultPravegaService();
    }

    @Override
    public void run(String... args) throws Exception {
        getPravegaService().write();
    }
}
