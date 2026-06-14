package com.configserverllp.csllp_learning_platform.user_service.dto;



import com.configserverllp.csllp_learning_platform.user_service.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

//    @NotBlank(message = "Role is required")
//    private String role;

    @NotNull(message = "Role is required")
    private Role role;


//    @Email(message = "Invalid email")
//    @NotBlank(message = "Email is required")
//    private String email;
//
//    @NotBlank(message = "Password is required")
//    private String password;
//
//    @NotBlank(message = "Role is required")
//    private String role;
}

