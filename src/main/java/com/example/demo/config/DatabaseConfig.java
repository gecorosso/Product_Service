package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Value("${PGHOST}")
    private String host;
    
    @Value("${PGPORT}")
    private String port;
    
    @Value("${PGDATABASE}")
    private String database;
    
    @Value("${PGUSER}")
    private String username;
    
    @Value("${PGPASSWORD}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s/%s", host, port, database));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}