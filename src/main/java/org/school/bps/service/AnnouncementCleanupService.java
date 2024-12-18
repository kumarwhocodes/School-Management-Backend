package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.AnnouncementDTO;
import org.school.bps.entity.ArchivedAnnouncement;
import org.school.bps.exception.AnnouncementNotFoundException;
import org.school.bps.repository.ArchivedAnnouncementRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementCleanupService {
    private final AnnouncementService announcementService;
    private final ArchivedAnnouncementRepository archivedAnnouncementRepository;
    
    //(cron = "0 0 0 * * ?") FOR every midnight
    //(cron = "0 * * * * ?") FOR every minute
    //(cron = "0 0 * * * ?") FOR every hour
    @Scheduled(cron = "0 0 * * * ?")
    public void archiveAndDeleteExpiredAnnouncements() {
        try {
            List<AnnouncementDTO> expiredAnnouncements = announcementService.findExpiredAnnouncements(LocalDate.now());
            if (expiredAnnouncements.isEmpty()) {
                throw new AnnouncementNotFoundException("No expired announcements found for cleanup.");
            }
            
            expiredAnnouncements.forEach(announcement -> {
                ArchivedAnnouncement archivedAnnouncement = new ArchivedAnnouncement();
                archivedAnnouncement.setAId(announcement.getAId());
                archivedAnnouncement.setTitle(announcement.getTitle());
                archivedAnnouncement.setMessage(announcement.getMessage());
                archivedAnnouncement.setExpirationDate(announcement.getExpirationDate());
                archivedAnnouncementRepository.save(archivedAnnouncement);
                announcementService.archiveAnnouncementById(announcement.getAId());
            });
            
        } catch (AnnouncementNotFoundException ex) {
            throw new AnnouncementNotFoundException("Failed to archive announcements: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while archiving announcements: " + ex.getMessage(), ex);
        }
    }
}
