package org.school.bps.entity;

import jakarta.persistence.*;
import lombok.*;
import org.school.bps.dto.TeacherDTO;
import org.school.bps.enums.Role;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @Column(name = "tId", nullable = false)
    private String tId;
    private String tName;
    private String password;
    private String email;
    private String subject;
    private int presentDays;
    private int totalDays;
    private int salary;
    private BigInteger phoneNumber;
    private int remainingCasualLeaves = 12;
    private int absentees;
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.TEACHER;
    
    public TeacherDTO toTeacherDTO() {
        return TeacherDTO
                .builder()
                .tId(tId)
                .tName(tName)
                .password(password)
                .email(email)
                .subject(subject)
                .presentDays(presentDays)
                .totalDays(totalDays)
                .salary(salary)
                .phoneNumber(phoneNumber)
                .remainingCasualLeaves(remainingCasualLeaves)
                .absentees(absentees)
                .build();
    }
}
