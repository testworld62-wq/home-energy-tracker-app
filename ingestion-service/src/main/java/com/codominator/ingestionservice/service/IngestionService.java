package com.codominator.ingestionservice.service;

import com.codominator.ingestionservice.dto.EnergyUsageDto;
import com.codominator.ingestionservice.kafka.event.EnergyUsageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class IngestionService {
    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    public IngestionService(KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void ingestEnergyUsage(EnergyUsageDto energyUsageDto) {
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .deviceId(energyUsageDto.deviceId())
                .timestamp(energyUsageDto.timestamp())
                .energyConsumed(energyUsageDto.energyConsumed())
                .build();

        kafkaTemplate.send("energyUsage", event);
        log.info("Energy Usage Event sent to topic: {}", energyUsageDto.deviceId());
    }
}
