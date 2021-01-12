package com.miex.shepherds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class ShepherdsApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShepherdsApplication.class);
        Map<String, Object> properties = new HashMap<>();
        properties.put("server.port",8090);
        app.setDefaultProperties(properties);
        app.run(args);
    }
}
