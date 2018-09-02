package com.hotel.controllers;

import com.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@RestController
@RequestMapping("${api.path}/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/rooms")
    public ResponseEntity<?> getRoomsData(){
        return ResponseEntity.ok(hotelService.getRoomsData());
    }
}
