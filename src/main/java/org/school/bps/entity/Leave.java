package org.school.bps.entity;

import jakarta.persistence.*;
import lombok.*;
import org.school.bps.dto.LeaveDTO;
import org.school.bps.enums.LeaveStatus;
import org.school.bps.enums.LeaveType;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leaves")
public class Leave {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    
    public LeaveDTO toLeaveDTO() {
        return LeaveDTO
                .builder()
                .id(id)
                .teacherId(teacher.getId()) // Use only the ID to avoid exposing the entire teacher object
                .startDate(startDate)
                .endDate(endDate)
                .leaveType(leaveType)
                .status(status)
                .build();
    }
}
