package app.near.us.api_security.controller;

import app.near.us.api_security.dto.LoginReq;
import app.near.us.api_security.dto.LoginRes;
import app.near.us.api_security.dto.RegisterReq;
import app.near.us.api_security.dto.RegisterRes;
import app.near.us.api_security.service.AuthenticationService;
import app.near.us.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    final private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApiResponse<RegisterRes> create(@Valid @RequestBody RegisterReq req){
        return authenticationService.register(req);
    }

    @PostMapping("/login")
    public ApiResponse<LoginRes> login(@Valid @RequestBody LoginReq req){
        return authenticationService.login(req);
    }

}
