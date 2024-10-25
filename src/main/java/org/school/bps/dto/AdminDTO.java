package org.school.bps.dto;

import lombok.*;
import org.school.bps.entity.Admin;
import org.school.bps.entity.Teacher;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private String username;
    private String password;
    private String email;
    private BigInteger phoneNumber;

    public Admin toAdmin() {
        return Admin
                .builder()
                .username(username)
                .password(password)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
