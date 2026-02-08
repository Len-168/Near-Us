package app.near.us.api_security.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegisterReq(

    @NotBlank(message = "Phone number is required")
    @Size(min = 8,max = 15 , message = "Phone Number must be 8-15 characters")
    String phoneNumber,

    @NotBlank(message = "Username is required")
    @Size(min = 5 , max = 20 , message = "Username must be 5-20 characters")
    String username,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password,

    @NotBlank(message = "Confirm Password is required")
    String conPassword,
    String role

) { }
