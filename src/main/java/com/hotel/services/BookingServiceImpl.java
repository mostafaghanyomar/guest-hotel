package com.hotel.services;

import com.hotel.dto.ReservationDetailsDTO;
import com.hotel.model.entities.Room;
import com.hotel.model.entities.User;
import com.hotel.repositories.RoomRepository;
import com.hotel.repositories.UserRepository;
import com.hotel.security.JWTUser;
import com.hotel.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 * It's Very Important When Persisting Objects in the DB into a service we mark this service @Transactional
 * to avoid any data inconsistency during performing the db operations.
 */
@Transactional
@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    public Map<String, String> reserveARoom(ReservationDetailsDTO reservationDetailsDTO) {
        Map<String, String> reservationStatus = new HashMap<>();
        JWTUser currentJWTUser = JWTUtil.getCurrentJWTUser();
        Optional<Room> firstRoomFound = roomRepository.findAllByRoomTypeAndIsBooked(reservationDetailsDTO.getRoomType(), false).stream().findFirst();
        String messageParam = "message";
        if(firstRoomFound.isPresent()){
            Room foundRoom = firstRoomFound.get();
            User currentUser = userRepository.findByUsername(currentJWTUser.getUsername());
            currentUser.setBookedRoom(foundRoom);
            foundRoom.setCurrentBooker(currentUser);
            foundRoom.setBooked(true);
            foundRoom.setBookedUntil(bookAWeek());
            roomRepository.save(foundRoom);
            reservationStatus.put(messageParam, "Reservation Occurred Successfully");
        } else {
            reservationStatus.put(messageParam, "Couldn't find any room from the specified type.");
        }
        return reservationStatus;
    }

    private Date bookAWeek() {
        return new Date(new Date().getTime() + 7*24*60*60*1000);
    }
}
