package org.lucasdc.auth.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "table_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String Role;
}
