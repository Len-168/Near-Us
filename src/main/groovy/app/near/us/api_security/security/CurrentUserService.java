package app.near.us.api_security.security;

import app.near.us.api_security.entity.User;
import app.near.us.api_security.repository.AuthRepository;
import app.near.us.exception.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CurrentUserService {

    private final AuthRepository authRepository;

    public CurrentUserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String getUsername(){
        return Objects.requireNonNull(SecurityContextHolder.getContext()
            .getAuthentication())
            .getName();
    }

    public String getRole() {
        return getUser().getRole();
    }

    public User getUser() {
        String username = getUsername();
        return authRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Long getUserId() {
        return getUser().getId();
    }
}
