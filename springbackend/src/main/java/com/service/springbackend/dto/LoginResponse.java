package com.service.springbackend.dto;

public record LoginResponse(
        String email,
        String username,
        com.service.springbackend.model.Role role,
        String token
) {}
