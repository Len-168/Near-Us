package app.near.us.api_security.dto;

import java.time.LocalDateTime;

public record LoginRes(
    String token,
    String username,
    String phoneNumber,
    LocalDateTime createAt,
    String role
) { }
