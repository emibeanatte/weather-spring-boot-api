

package com.weather.api.emibeanatte.security.service;

import com.weather.api.emibeanatte.security.dto.LoginUserDto;
import com.weather.api.emibeanatte.security.dto.RegisterUserDto;
import com.weather.api.emibeanatte.security.entity.Role;
import com.weather.api.emibeanatte.security.entity.User;
import com.weather.api.emibeanatte.security.enums.RoleName;
import com.weather.api.emibeanatte.security.repository.RolRepository;
import com.weather.api.emibeanatte.security.repository.UserRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto registerUser) {
        Optional<Role> role = roleRepository.findByRoleName(RoleName.USER);

        if (!role.isPresent()) {
            throw new RuntimeException("Role user not found");
        }

        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setRole(role.get());

        return userRepository.save(user);
    }

    public User signin(LoginUserDto loginUser) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()));

        return userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(NoSuchElementException::new);
    }
    
}
