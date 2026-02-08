package app.near.us.api_security.handler;

import app.near.us.api_security.dto.LoginRes;
import app.near.us.api_security.dto.RegisterReq;
import app.near.us.api_security.dto.RegisterRes;
import app.near.us.api_security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Service
public class AuthHandler {

    final private PasswordEncoder passwordEncoder;

    public RegisterRes toRegisterResponse(User req){
        return new RegisterRes(
            req.getPhoneNumber(),
            req.getUsername(),
            req.getPassword(),
            req.getCreateAt(),
            req.getCreatedBy()
        );
    }

    public User toEntity(RegisterReq req) {

        User user = new User();
        user.setPhoneNumber(req.phoneNumber());
        user.setUsername(req.username());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(req.role());
        user.setEnabled(true);
        user.setCreateAt(LocalDateTime.now());
        user.setCreatedBy("SYSTEM");

        return user;
    }

    public LoginRes toLoginResponse(User user, String token){
        return new LoginRes(
            token,
            user.getUsername(),
            user.getPhoneNumber(),
            user.getCreateAt(),
            user.getRole()
        );
    }

}
