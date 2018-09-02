package com.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public BadRequestException(String message){
        super(message);
    }
}
