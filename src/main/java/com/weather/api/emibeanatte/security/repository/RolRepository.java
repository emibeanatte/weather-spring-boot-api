

package com.weather.api.emibeanatte.security.repository;

import com.weather.api.emibeanatte.security.entity.Role;
import com.weather.api.emibeanatte.security.enums.RoleName;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName roleName);
    
}