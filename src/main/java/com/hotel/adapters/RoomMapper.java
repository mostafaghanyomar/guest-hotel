package com.hotel.adapters;

import com.hotel.dto.RoomDTO;
import com.hotel.model.entities.Room;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
public class RoomMapper
{

    public static RoomDTO makeRoomDTO(Room roomEntity)
    {
        RoomDTO dto = new RoomDTO();
        dto.setBooked(roomEntity.getBooked());
        dto.setBookedUntil(roomEntity.getBookedUntil());
        dto.setRoomNumber(roomEntity.getRoomNumber());
        dto.setRoomType(roomEntity.getRoomType());
        dto.setBooker(getBookerFromEntity(roomEntity));
        return dto;
    }

    private static String getBookerFromEntity(Room roomEntity) {
        if(nonNull(roomEntity.getCurrentBooker())){
            return roomEntity.getCurrentBooker().getFirstname() + " " + roomEntity.getCurrentBooker().getLastname();
        }
        return null;
    }

    public static List<RoomDTO> MakeRoomDTOList(List<Room> roomEntities) {
        return roomEntities.stream().map(RoomMapper::makeRoomDTO)
                .collect(Collectors.toList());
    }
}
