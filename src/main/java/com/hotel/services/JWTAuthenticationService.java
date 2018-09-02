package com.hotel.services;

import com.hotel.dto.JWTAuthCredentialsDTO;
import com.hotel.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
public interface JWTAuthenticationService {
    Map<String, String> login(JWTAuthCredentialsDTO jwtAuthCredentialsDTO);
    Map<String, String> refreshAndGetBackAuthenticationToken(HttpServletRequest request) throws BadRequestException;
}
