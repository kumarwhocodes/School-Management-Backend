package org.school.bps.dto;

import lombok.*;
import org.school.bps.entity.Leave;
import org.school.bps.entity.Teacher;
import org.school.bps.enums.LeaveStatus;
import org.school.bps.enums.LeaveType;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDTO {
    
    private Long id;
    private String teacherId; // Only the ID is needed to reduce coupling
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private LeaveStatus status;
    
    public Leave toLeave(Teacher teacher) {
        return Leave
                .builder()
                .id(id)
                .teacher(teacher)
                .startDate(startDate)
                .endDate(endDate)
                .leaveType(leaveType)
                .status(status)
                .build();
    }
}
