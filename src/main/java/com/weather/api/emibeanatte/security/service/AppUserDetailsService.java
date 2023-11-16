package com.weather.api.emibeanatte.security.service;

import com.weather.api.emibeanatte.security.entity.User;
import com.weather.api.emibeanatte.security.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class AppUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        
        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException("User not found.");   
        }
        return optUser.get();
    }
    
    
    
}
