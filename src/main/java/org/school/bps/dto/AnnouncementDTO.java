package org.school.bps.dto;

import lombok.*;
import org.school.bps.entity.Announcement;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {
    private int aId;
    private String title;
    private String message;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate expirationDate;
    private boolean isHoliday;
    
    public Announcement toAnnouncement() {
        return Announcement
                .builder()
                .id(aId)
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
