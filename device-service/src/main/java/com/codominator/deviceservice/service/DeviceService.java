package com.codominator.deviceservice.service;

import com.codominator.deviceservice.dto.DeviceDto;
import com.codominator.deviceservice.entity.Device;
import com.codominator.deviceservice.exception.DeviceNotFoundException;
import com.codominator.deviceservice.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
private final DeviceRepository deviceRepository;

    public DeviceDto createDevice(DeviceDto deviceDto) {
        Device createDevice = Device.builder()
                .name(deviceDto.getName())
                .type(deviceDto.getDevice_type())
                .location(deviceDto.getLocation())
                .userId(deviceDto.getUserId())
                .build();
        System.out.println(createDevice);

        Device createdDevice = deviceRepository.save(createDevice);
        return toDto(createdDevice);
    }

    public DeviceDto findDevice(Long id) {
        return deviceRepository.findById(id).map(this::toDto).orElseThrow(()-> new DeviceNotFoundException("device not found with this id:"+ id));

    }
    public DeviceDto updateDevice(Long id, DeviceDto deviceDto) {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("device not found with this id:" + id));
        device.setName(deviceDto.getName());
        device.setLocation(deviceDto.getLocation());
        deviceRepository.save(device);
        return toDto(device);
    }

    public List<DeviceDto> getAllDevices(){
        return deviceRepository.findAll().stream().map(this::toDto).toList();
    }

    public boolean deleteDevice(Long id){
        deviceRepository.deleteById(id);
        return true;
    }
    private DeviceDto toDto(Device device){
        return  DeviceDto.builder()
                .id(device.getId())
                .name(device.getName())
                .device_type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }
}
