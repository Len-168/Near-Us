package app.near.us.api_security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class ApplicationApiKeyFilter extends OncePerRequestFilter {

    @Value("${application-security.register.token}")
    private String registerToken;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws
        ServletException, IOException
    {

        String apiKey = request.getHeader("API-KON-KHMER-168");

        if(apiKey == null || !apiKey.equals(registerToken)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("""
            {
                "success":false,
                "message":"your request are not allow because you don't have application authenticate.",
                "data":null
            }
            """);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
