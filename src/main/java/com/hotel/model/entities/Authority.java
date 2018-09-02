package com.hotel.model.entities;

import com.hotel.model.AbstractIdentifiableEntity;
import com.hotel.model.constants.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Entity
@Table(name = "AUTHORITY")
public class Authority extends AbstractIdentifiableEntity {

    @Column(name = "ROLE", length = 50, nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}