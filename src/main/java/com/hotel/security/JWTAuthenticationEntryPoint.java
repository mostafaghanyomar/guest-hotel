package com.hotel.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = 6456356188121466535L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // Invoked when the user tries to access a secured REST resource without being authorized
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Request");
    }
}