package com.codominator.deviceservice.controller;

import com.codominator.deviceservice.dto.DeviceDto;

import com.codominator.deviceservice.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping("/create")
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto){
        return new ResponseEntity<>(deviceService.createDevice(deviceDto), HttpStatus.CREATED);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<DeviceDto> createDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto){
        return new ResponseEntity<>(deviceService.updateDevice(id, deviceDto), HttpStatus.OK);
    }
    @GetMapping("get-all")
    public ResponseEntity<List<DeviceDto>> getAllDevice(){
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDevice(@PathVariable Long id){
        return new ResponseEntity<>(deviceService.deleteDevice(id), HttpStatus.OK);
    }
}
