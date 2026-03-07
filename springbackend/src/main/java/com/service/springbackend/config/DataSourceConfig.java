package com.service.springbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("springbackend/.env")) {
            props.load(fis);
        }

        String url = "jdbc:postgresql://" + props.getProperty("DB_HOST") + ":" +
                props.getProperty("DB_PORT") + "/" +
                props.getProperty("DB_NAME") + "?sslmode=require";
        String user = props.getProperty("DB_USER");
        String password = props.getProperty("DB_PASSWORD");

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);

        return ds;
    }
}