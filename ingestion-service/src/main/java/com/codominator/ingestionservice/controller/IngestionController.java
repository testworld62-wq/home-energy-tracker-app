package com.codominator.ingestionservice.controller;

import com.codominator.ingestionservice.dto.EnergyUsageDto;
import com.codominator.ingestionservice.service.IngestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingestion")
public class IngestionController {
    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EnergyUsageDto energyUsageDto) {
        ingestionService.ingestEnergyUsage(energyUsageDto);
        return new ResponseEntity<>("Data has been send successfully",HttpStatus.OK);
    }
}
