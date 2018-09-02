package com.hotel.repositories;

import com.hotel.model.constants.RoomType;
import com.hotel.model.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByRoomTypeAndIsBooked(RoomType roomType, Boolean isBooked);
}
