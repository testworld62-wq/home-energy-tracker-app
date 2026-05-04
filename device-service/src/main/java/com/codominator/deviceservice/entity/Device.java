package com.codominator.deviceservice.entity;

import com.codominator.deviceservice.model.Device_Type;
import jakarta.persistence.Column;

import jakarta.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table
@Builder
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Device_Type type;
    private String location;
    @Column(name = "user_id")
    private Long userId;

}
