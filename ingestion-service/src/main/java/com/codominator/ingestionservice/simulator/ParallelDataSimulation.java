package com.codominator.ingestionservice.simulator;

import com.codominator.ingestionservice.dto.EnergyUsageDto;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class ParallelDataSimulation implements CommandLineRunner {
    private final ExecutorService executorService;
    private int parallelThread = 2;
    private int requestPerInterval = 10;
    private final RestTemplate restTemplate;

    private final Random random = new Random();
    private static final String URL = "http://127.0.0.1:4003/api/v1/ingestion";

    public ParallelDataSimulation(RestTemplate restTemplate) {
        this.executorService = Executors.newCachedThreadPool();
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("ParallelDataSimulation Started..");
        ((ThreadPoolExecutor) executorService).setCorePoolSize(parallelThread);

    }

    @Scheduled(fixedRateString = "${simulation.interval.ms}")
    public void sendMockData() {
        int batchSize = requestPerInterval / parallelThread;
        int remainder = requestPerInterval % parallelThread;
        for (int i = 0; i < parallelThread; i++) {
            int requestPerThread = batchSize + (i < remainder ? 1 : 0);
            executorService.submit(() -> {
                for (int j = 0; j < requestPerThread; j++) {

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
            });
        }
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
        log.info("Shutting down...");
    }
}
