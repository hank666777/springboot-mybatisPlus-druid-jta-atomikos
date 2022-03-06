package com.example.atomikos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.secondarydb")
public class SecondaryProperties extends DataSourceProperties{

}
