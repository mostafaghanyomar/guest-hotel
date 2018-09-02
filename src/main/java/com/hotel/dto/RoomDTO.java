package com.hotel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.model.constants.RoomType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDTO implements Serializable {
    private static final long serialVersionUID = -6528206323385696163L;

    @JsonIgnore
    private Long id;

    @NotNull(message = "isBooked is null!")
    private Boolean isBooked;

    @NotNull(message = "roomNumber is null!")
    private String roomNumber;

    @NotNull(message = "roomType is null!")
    private RoomType roomType;

    private Date bookedUntil;

    private String booker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Date getBookedUntil() {
        return bookedUntil;
    }

    public void setBookedUntil(Date bookedUntil) {
        this.bookedUntil = bookedUntil;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}
