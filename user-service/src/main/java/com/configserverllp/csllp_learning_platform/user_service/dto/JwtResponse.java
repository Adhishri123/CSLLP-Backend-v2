package com.configserverllp.csllp_learning_platform.user_service.dto;

import com.configserverllp.csllp_learning_platform.user_service.entity.User;

public record JwtResponse(String accessToken, String refreshToken, User user) {
}
