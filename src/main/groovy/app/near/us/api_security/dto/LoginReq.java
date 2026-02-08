package app.near.us.api_security.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginReq(
    @NotBlank(message = "Username is required")
    String username,
    @NotBlank(message = "password is required")
    String password
) {
}
