package org.school.bps.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.school.bps.dto.AnnouncementDTO;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcements")
public class Announcement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String message;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate expirationDate;
//    @Column(name = "isHoliday", nullable = false)
    @Column(name = "is_holiday")
    private boolean isHoliday;
    
    public AnnouncementDTO toAnnouncementDTO() {
        return AnnouncementDTO
                .builder()
                .aId(id)
                .title(title)
                .message(message)
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .expirationDate(expirationDate)
                .isHoliday(isHoliday)
                .build();
    }
    
}
