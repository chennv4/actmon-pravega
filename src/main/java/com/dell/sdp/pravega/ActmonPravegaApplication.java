package com.dell.sdp.pravega;

import com.dell.sdp.pravega.service.DefaultPravegaService;
import com.dell.sdp.pravega.service.PravegaService;
import com.dell.sdp.pravega.util.PravegaServiceUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class ActmonPravegaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ActmonPravegaApplication.class, args);
    }

    @Bean
    public PravegaService getPravegaService(){
        return  new DefaultPravegaService();
    }

    @Bean
    public PravegaServiceUtil getPravegaServiceUtil(){
        return  new PravegaServiceUtil();
    }

    @Override
    public void run(String... args) throws Exception {
        getPravegaServiceUtil();
        getPravegaService().write();
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("pravega-writer-");
        executor.initialize();
        return executor;
    }
}
