package org.school.bps.dto;

import lombok.*;
import org.school.bps.entity.Teacher;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String id;
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
    
    public Teacher toTeacher() {
        return Teacher
                .builder()
                .id(id)
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
