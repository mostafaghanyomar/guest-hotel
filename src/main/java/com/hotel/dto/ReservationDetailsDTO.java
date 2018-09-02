package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.model.constants.RoomType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDetailsDTO implements Serializable {
    private static final long serialVersionUID = 3196474822087393412L;

    @NotNull(message = "There should be a type to be checked!")
    private RoomType roomType;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
