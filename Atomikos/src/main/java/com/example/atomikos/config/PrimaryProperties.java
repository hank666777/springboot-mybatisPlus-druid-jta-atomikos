package com.example.atomikos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.primarydb")
public class PrimaryProperties extends DataSourceProperties{

}
