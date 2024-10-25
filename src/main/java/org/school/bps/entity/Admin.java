package org.school.bps.entity;

import jakarta.persistence.*;
import lombok.*;
import org.school.bps.enums.Role;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name = "adminId", nullable = false)
    private String username;
    private String password;
    private String email;
    private BigInteger phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;
}