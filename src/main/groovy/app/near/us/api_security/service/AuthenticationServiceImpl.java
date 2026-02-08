package app.near.us.api_security.service;

import app.near.us.api_security.dto.LoginReq;
import app.near.us.api_security.dto.LoginRes;
import app.near.us.api_security.dto.RegisterReq;
import app.near.us.api_security.dto.RegisterRes;
import app.near.us.api_security.entity.User;
import app.near.us.api_security.handler.AuthHandler;
import app.near.us.api_security.jwt.JwtUtil;
import app.near.us.api_security.repository.AuthRepository;
import app.near.us.common.ApiResponse;
import app.near.us.exception.AlreadyExistsException;
import app.near.us.exception.BadRequestException;
import app.near.us.exception.DisableException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    final private AuthRepository authRepository;
    final private AuthHandler handler;
    final private JwtUtil jwtUtil;
    final private PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<RegisterRes> register(RegisterReq req) {

        validateRegisterRequest(req);
        User user = handler.toEntity(req);
        User savedUser = authRepository.save(user);

        return ApiResponse.success("Register Success",handler.toRegisterResponse(savedUser));
    }

    @Override
    public ApiResponse<LoginRes> login(LoginReq req) {

        User user = authRepository.findByUsername(req.username()).orElseThrow(
            () -> new BadRequestException("Invalid username or password")
        );

        validateLoginRequest(req, user);

        String token = jwtUtil.generateToken(user);

        LoginRes res = handler.toLoginResponse(user,token);
        return ApiResponse.success("Login Success", res);
    }

    private void validateLoginRequest(LoginReq req , User user){
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new BadRequestException("Password and Confirm Password do not match");
        }
        if (!user.isEnabled()) {
            throw new DisableException("User is disabled");
        }
    }

    private void validateRegisterRequest(RegisterReq req){
        if (authRepository.existsByPhoneNumber(req.phoneNumber())) {
            throw new AlreadyExistsException("Phone number already exists");
        }
        if (authRepository.existsByUsername(req.username())) {
            throw new AlreadyExistsException("Username already exists");
        }
        if (!req.password().equals(req.conPassword())) {
            throw new BadRequestException("Password and Confirm Password do not match");
        }
    }
}
