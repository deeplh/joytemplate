package com.keepjoy.core.runner;

import com.keepjoy.core.module.datadict.DataDictFactory;
import com.keepjoy.core.properties.KeepJoyProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(value=1)
public class KeepJoyRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(KeepJoyProperties.getDataDictMode()){
            DataDictFactory.create();
        }
    }

}
