package com.codominator.deviceservice;

import com.codominator.deviceservice.entity.Device;
import com.codominator.deviceservice.model.Device_Type;
import com.codominator.deviceservice.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DeviceServiceApplicationTests {
    @Autowired
    private DeviceRepository deviceRepository;
    public static final int NUMBER_OF_DEVICES = 400;
    public static final int USER = 200;

    @Test
    void contextLoads() {
    }

    @Test
    @Disabled
    void createDevice() {
        for (int i = 1; i <= NUMBER_OF_DEVICES; i++) {
            var device = Device.builder()
                    .name("device" + i)
                    .type(Device_Type.values()[i % Device_Type.values().length])
                    .location("location" + (i % 3) + 1)
                    .userId((long) (i % USER) + 1)
                    .build();
            deviceRepository.save(device);
        }
        log.info("Device created");

    }

}
