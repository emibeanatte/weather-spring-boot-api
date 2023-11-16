package com.weather.api.emibeanatte.security.entity;

import com.weather.api.emibeanatte.security.enums.RoleName;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role(@NotNull RoleName roleName) {
        this.roleName = roleName;
    }

    public String getAuthority(){
        return roleName.name();
    }
       
}
