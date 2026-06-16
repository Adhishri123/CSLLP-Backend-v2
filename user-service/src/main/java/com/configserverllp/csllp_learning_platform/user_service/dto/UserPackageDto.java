package com.configserverllp.csllp_learning_platform.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPackageDto {
    private Long id;
    private String fullName;
    private String designation;
    private String role;
    private Double annualSalary;
    private LocalDate dateOfJoining;
}
