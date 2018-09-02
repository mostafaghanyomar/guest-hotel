package com.hotel.model.entities;

import com.hotel.model.AbstractIdentifiableEntity;
import com.hotel.model.constants.RoomType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Entity
@Table(name = "ROOM")
public class Room extends AbstractIdentifiableEntity {

    @Column(name = "BOOKED", nullable = false)
    @NotNull
    private Boolean isBooked;

    @Column(name = "ROOM_NUMBER", length = 50, unique = true, nullable = false)
    @NotEmpty
    private String roomNumber;

    @Column(name = "ROOM_TYPE", length = 50, nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "BOOKED_UNTIL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedUntil;

    @OneToOne(targetEntity = User.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User currentBooker;

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

    public User getCurrentBooker() {
        return currentBooker;
    }

    public void setCurrentBooker(User currentBooker) {
        this.currentBooker = currentBooker;
    }
}
