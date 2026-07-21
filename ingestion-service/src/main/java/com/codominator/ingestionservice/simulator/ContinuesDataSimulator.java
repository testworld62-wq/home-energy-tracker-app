package com.codominator.ingestionservice.simulator;

import com.codominator.ingestionservice.dto.EnergyUsageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContinuesDataSimulator implements CommandLineRunner {

    @Value("${spring.ingestion.mock.time}")
    private Integer requestPerInterval;

    private final RestTemplate restTemplate;

    private final Random random = new Random();

    private static final String URL = "http://127.0.0.1:4003/api/v1/ingestion";

    @Override
    public void run(String @NonNull ... args) {
        log.info("Continuous data simulation started...");
    }

    @Scheduled(fixedRateString = "${simulation.interval.ms}")
    public void sendMockData() {

        for (int i = 0; i < requestPerInterval; i++) {

            EnergyUsageDto energyUsageDto = EnergyUsageDto.builder()
                    .deviceId(random.nextLong(1, 6))
                    .energyConsumed(Math.round(random.nextDouble(0.0, 2.0) * 100.0) / 100.0)
                    .timestamp(Instant.now())
                    .build();

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<EnergyUsageDto> entity = new HttpEntity<>(energyUsageDto, headers);

                ResponseEntity<String> response =
                        restTemplate.postForEntity(URL, entity, String.class);

                log.info("Sent: {} | Response: {}", energyUsageDto, response.getStatusCode());

            } catch (Exception e) {
                log.error("Error sending data", e);
            }
        }
    }
}