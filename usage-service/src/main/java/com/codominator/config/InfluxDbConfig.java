package com.codominator.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDbConfig {
    @Value("${influx.url}")
    private String influxDbUrl;
    @Value("${influx.token}")
    private String influxToken;
    @Value("${influx.org}")
    private String influxOrg;

    @Bean
    public InfluxDBClient client(){
        return InfluxDBClientFactory.create(influxDbUrl, influxToken.toCharArray(), influxOrg);
    }
}
