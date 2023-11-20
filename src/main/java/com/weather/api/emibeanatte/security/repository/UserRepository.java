

package com.weather.api.emibeanatte.security.repository;

import com.weather.api.emibeanatte.security.entity.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {  
    Optional<User> findByUsername(String username);
}
