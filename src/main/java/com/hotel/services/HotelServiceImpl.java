package com.hotel.services;

import com.hotel.adapters.RoomMapper;
import com.hotel.dto.RoomDTO;
import com.hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Service("hotelService")
public class HotelServiceImpl implements HotelService {

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomDTO> getRoomsData() {
        return RoomMapper.MakeRoomDTOList(roomRepository.findAll());
    }
}
