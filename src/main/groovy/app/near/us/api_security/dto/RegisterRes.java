package app.near.us.api_security.dto;


import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegisterRes(
    String phoneNumber,
    String username,
    String password,
    LocalDateTime createAt,
    String createBy
) { }
