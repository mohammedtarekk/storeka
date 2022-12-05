package me.storeka.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="roles", schema = "access")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nameEN;
    private String nameAR;
    private String description;
    private Boolean isActive;

}
