package com.hotel.repositories;

import com.hotel.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}