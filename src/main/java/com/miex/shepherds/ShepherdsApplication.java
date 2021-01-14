package com.miex.shepherds;

import com.alibaba.fastjson.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.miex.shepherds.core.mapper")
public class ShepherdsApplication {
    static Logger logger = LoggerFactory.getLogger(ShepherdsApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShepherdsApplication.class);
        app.setDefaultProperties(init());
        app.run(args);
    }

    /**
     * 初始化配置，
     * 数据库的位置不允许改变
     * @return 核心功能的配置数据
     */
    private static Map<String,Object> init(){
        Map<String,Object> properties = new HashMap<>();
        properties.put("spring.datasource.url","jdbc:sqlite:src/main/resources/sqlite/shepherds.db");
        properties.put("spring.datasource.driver-class-name","org.sqlite.JDBC");
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl((String) properties.get("spring.datasource.driver-class-name"));
        // 将数据库中的配置取出，worker 表 id=0
        try {
            Class.forName((String) properties.get("spring.datasource.driver-class-name"));
            Connection conn = DriverManager.getConnection((String) properties.get("spring.datasource.url"));
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM worker WHERE worker_id = 0");
            resultSet.next();
            JSONObject.parseObject(resultSet.getString("prop")).forEach(properties::put);
            resultSet.close();
            statement.close();
            conn.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        logger.info(properties.toString());
        return properties;
    }
}
