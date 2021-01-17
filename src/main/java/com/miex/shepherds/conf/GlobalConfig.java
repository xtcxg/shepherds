package com.miex.shepherds.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

//@Configuration
public class GlobalConfig {
    @Autowired
    private ConfigurableEnvironment environment;

    @Bean
    public void mainConfig(){
        MutablePropertySources propertySources = environment.getPropertySources();
        Properties properties = new Properties();
        properties.put("service.port","8090");

        PropertiesPropertySource constants = new PropertiesPropertySource("global-config", properties);
        propertySources.addLast(constants);
    }
}
