package com.bcwms.runner;

import com.bcwms.factory.CosClientFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Order(value = 2)
public class BcwRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CosClientFactory.init();
    }

    @PreDestroy
    public void destory() {
        CosClientFactory.shutdown();
    }
}
