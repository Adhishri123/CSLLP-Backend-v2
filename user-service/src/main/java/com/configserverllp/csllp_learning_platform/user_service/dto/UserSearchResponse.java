package com.configserverllp.csllp_learning_platform.user_service.dto;

import com.configserverllp.csllp_learning_platform.user_service.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSearchResponse {
    private Long id;
    private String employeeId;
    private String fullName;
    private String email;
//    private String role;
    private Role role;
    private String department;
    private String status;
}