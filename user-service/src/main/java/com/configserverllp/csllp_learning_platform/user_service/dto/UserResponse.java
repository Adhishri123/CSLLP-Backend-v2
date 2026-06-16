package com.configserverllp.csllp_learning_platform.user_service.dto;

import com.configserverllp.csllp_learning_platform.user_service.entity.Role;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
//    private String firstName;
//    private String lastName;
    private String fullName;
//    private String role;
    private Role role;
    private Long managerId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Dashboard info
    private int totalEmployees;
    private int totalCourses;
    private int completedCourses;

    // Profile info
    private String profilePhotoUrl;
}
