package com.service.springbackend.dto;

public record LoginResponse(
        String email,
        String username,
        String token
) {}
