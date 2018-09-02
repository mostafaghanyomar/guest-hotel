package com.hotel.services;

import com.hotel.dto.JWTAuthCredentialsDTO;
import com.hotel.exception.BadRequestException;
import com.hotel.security.JWTUser;
import com.hotel.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 * Isolating the Logic in a service from the Endpoints.
 */
@Service("jwtAuthenticationService")
public class JWTAuthenticationServiceImpl implements JWTAuthenticationService {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public Map<String, String> login(JWTAuthCredentialsDTO jwtAuthCredentialsDTO){
        //Authenticate the User by His Credentials
        final Authentication authentication = daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtAuthCredentialsDTO.getUsername(),
                        jwtAuthCredentialsDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Generate the Token and Return it.
        final String token = jwtUtil.generateToken((JWTUser) authentication.getPrincipal());
        return jwtUtil.wrapToken(token);
    }

    public Map<String, String> refreshAndGetBackAuthenticationToken(HttpServletRequest request) throws BadRequestException {
        String token = jwtUtil.getTokenFromRequest(request);
        String username = jwtUtil.getUsernameFromToken(token);
        JWTUser user = (JWTUser) userDetailsService.loadUserByUsername(username);

        if (jwtUtil.canTokenBeRefreshed(token, user.getLastModified())) {
            String refreshedToken = jwtUtil.refreshToken(token);
            return jwtUtil.wrapToken(refreshedToken);
        } else {
            throw new BadRequestException("Invalid Token");
        }
    }
}
