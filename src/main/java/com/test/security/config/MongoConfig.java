package com.test.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.authDBUrl}")
    private String authDBUrl;
    @Value("${spring.data.mongodb.vehicleDBUrl}")
    private String vehicleDBUrl;

    @Bean(name = "vehicleDBMongoTemplate")
    public MongoTemplate vehicleDBMongoTemplate(){
        return new MongoTemplate(vehicleMongoDatabaseFactory());
    }

    @Bean
    public MongoDatabaseFactory vehicleMongoDatabaseFactory(){
        return new SimpleMongoClientDatabaseFactory(vehicleDBUrl);
    }

    @Bean
    public MongoTransactionManager transactionManagerVehicleDB(@Qualifier("vehicleMongoDatabaseFactory") MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean(name = "mongoTemplate")
    @Primary
    public MongoTemplate authDBMongoTemplate(){
        return new MongoTemplate(authDBMongoFactory());
    }

    @Bean
    @Primary
    public MongoDatabaseFactory authDBMongoFactory(){
        return new SimpleMongoClientDatabaseFactory(authDBUrl);
    }

    @Bean
    @Primary
    public MongoTransactionManager transactionManagerAuthDB(@Qualifier("authDBMongoFactory") MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
