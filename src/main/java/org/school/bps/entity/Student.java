package org.school.bps.entity;

import jakarta.persistence.*;
import lombok.*;
import org.school.bps.dto.StudentDTO;
import org.school.bps.enums.Role;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "admNum", nullable = false)
    private String stuId;
    private String stuName;
    private String password;
    private String email;
    private String className;
    private int rollNumber;
    private double attendancePercentage;
    private BigInteger phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    public StudentDTO toStudentDTO() {
        return StudentDTO
                .builder()
                .stuId(stuId)
                .stuName(stuName)
                .password(password)
                .email(email)
                .className(className)
                .rollNumber(rollNumber)
                .attendancePercentage(attendancePercentage)
                .phoneNumber(phoneNumber)
                .build();
    }
}
