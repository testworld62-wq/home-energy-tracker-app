package com.codominator.deviceservice.dto;

import com.codominator.deviceservice.model.Device_Type;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeviceDto {
    private Long id;
    private String name;
    private String location;
    private Device_Type device_type;
    private Long userId;

}
