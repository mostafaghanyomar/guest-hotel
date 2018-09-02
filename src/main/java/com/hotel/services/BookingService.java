package com.hotel.services;

import com.hotel.dto.ReservationDetailsDTO;

import java.util.Map;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
public interface BookingService {
    Map<String, String> reserveARoom(ReservationDetailsDTO reservationDetailsDTO);
}
