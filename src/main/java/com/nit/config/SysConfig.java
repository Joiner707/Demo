package com.nit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/config.properties")
public class SysConfig {

    @Autowired
    private Environment env;

    public String getProperty(String str){
        return env.getProperty(str);
    }

}
