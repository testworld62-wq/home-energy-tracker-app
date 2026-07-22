package com.codominator.usageservice.service;

import com.codominator.usageservice.kafka.event.EnergyUsageEvent;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@RequiredArgsConstructor
public class UsageService {
    private final InfluxDBClient influxDbClient;

    @Value("${influx.bucket}")
    private String influxBucket;
    @Value("${influx.org}")
    private String influxOrg;

    @KafkaListener(topics = "energy-usage", groupId = "usage-service")
    public void energyUsageEvent(EnergyUsageEvent event){
        log.info("Received energy usage event: {}", event );
        Point point = Point.measurement("energy-usage")
                .addTag("deviceId", String.valueOf(event.deviceId()))
                .addTag("energyConsumed", String.valueOf(event.energyConsumed()))
                .time(event.timestamp(), WritePrecision.MS);
        influxDbClient.getWriteApiBlocking().writePoint(influxBucket, influxOrg, point);

    }
}
