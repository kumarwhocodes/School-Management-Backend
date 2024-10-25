package org.school.bps.dto;

import lombok.*;
import org.school.bps.entity.Student;
import org.school.bps.entity.Teacher;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String tId;
    private String tName;
    private String password;
    private String email;
    private String subject;
    private double attendancePercentage;
    private BigInteger phoneNumber;

    public Teacher toTeacher() {
        return Teacher
                .builder()
                .tId(tId)
                .tName(tName)
                .password(password)
                .email(email)
                .subject(subject)
                .attendancePercentage(attendancePercentage)
                .phoneNumber(phoneNumber)
                .build();
    }
}
