package org.school.bps.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "archived_announcements")
public class ArchivedAnnouncement {
    @Id
    private int aId;
    private String title;
    private String message;
    private LocalDate expirationDate;
    private LocalDate archivedDate = LocalDate.now();
}
