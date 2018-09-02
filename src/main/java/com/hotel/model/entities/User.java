package com.hotel.model.entities;

import com.hotel.model.AbstractIdentifiableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Entity
@Table(name = "USER")
public class User extends AbstractIdentifiableEntity {

    @Column(name = "LAST_MODIFIED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastModified;

    @Column(name = "USERNAME", length = 50, unique = true, nullable = false)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "PASSWORD", length = 100, nullable = false)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name = "ENABLED", nullable = false)
    @NotNull
    private Boolean enabled = Boolean.TRUE;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @Column(name = "EMAIL", length = 50, nullable = false)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @OneToOne(mappedBy = "currentBooker")
    private Room bookedRoom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @PrePersist
    public void onPersist(){
        setLastModified(new Date());
    }

    @PreUpdate
    public void onUpdate(){
        setLastModified(new Date());
    }
}