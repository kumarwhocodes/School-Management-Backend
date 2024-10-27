package org.school.bps.dto;

import lombok.*;
import org.school.bps.entity.Student;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String stuId;
    private String stuName;
    private String password;
    private String email;
    private String className;
    private int rollNumber;
    private int presentDays;
    private BigInteger phoneNumber;

    public Student toStudent() {
        return Student
                .builder()
                .stuId(stuId)
                .stuName(stuName)
                .password(password)
                .email(email)
                .className(className)
                .rollNumber(rollNumber)
                .presentDays(presentDays)
                .phoneNumber(phoneNumber)
                .build();
    }
}
