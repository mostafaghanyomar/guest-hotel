package com.hotel.services;

import com.hotel.model.entities.User;
import com.hotel.repositories.UserRepository;
import com.hotel.security.JWTUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Service("userDetailsService")
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (isNull(user)) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return createJWTUser(user);
        }
    }

    private JWTUser createJWTUser(User user) {
        return new JWTUser(
                user.getId(),
                user.getLastModified(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                        .collect(Collectors.toList())
        );
    }
}