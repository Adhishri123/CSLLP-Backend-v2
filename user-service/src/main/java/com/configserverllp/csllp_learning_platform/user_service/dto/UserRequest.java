package com.configserverllp.csllp_learning_platform.user_service.dto;



import com.configserverllp.csllp_learning_platform.user_service.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {

    private Long employeeId;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

//    @NotBlank(message = "First name is required")
//    private String firstName;
//
//    private String lastName;

    @NotBlank(message = "Full name is required")
    private String fullName;

//    @NotBlank(message = "Role is required")
//    private String role; // ADMIN, MANAGER, EMPLOYEE, HR
    @NotNull(message = "Role is required")
    private Role role; // ADMIN, MANAGER, EMPLOYEE, HR

    // optional: for admin-created employee
    private Long managerId;

    //@NotBlank(message = "Password is required")
    //@Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String profilePhotoUrl; // NEW

    private String phoneNumber;
    private String address;
    private String designation;
    private String department;
    private Double annualSalary;
    @NotNull(message = "Date of Joining is required")
    private LocalDate dateOfJoining;
    private String panNumber;
    private String pfNumber;
    private String uanNumber;
    private String bankName;
    private String bankBranch;
    private String bankAccountNumber;
    private String vendorCode;
    private String status;

    public void setId(Long userId) {

    }
}

