package com.hotel.controllers;

import com.hotel.dto.ReservationDetailsDTO;
import com.hotel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@RestController
@RequestMapping("${api.path}/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PutMapping("/room")
    public ResponseEntity<?> reserveARoom(@RequestBody ReservationDetailsDTO reservationDetailsDTO){
        return ResponseEntity.ok(bookingService.reserveARoom(reservationDetailsDTO));
    }
}
