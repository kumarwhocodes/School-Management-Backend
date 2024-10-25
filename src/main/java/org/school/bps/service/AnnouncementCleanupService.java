package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.AnnouncementDTO;
import org.school.bps.entity.ArchivedAnnouncement;
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
    
    //(cron = "0 0 0 * * ?") FOR daily at midnight
    //(cron = "0 * * * * ?") FOR every minute
    @Scheduled(cron = "0 * * * * ?")
    public void archiveAndDeleteExpiredAnnouncements() {
        List<AnnouncementDTO> expiredAnnouncements = announcementService.findExpiredAnnouncements(LocalDate.now());
        expiredAnnouncements.forEach(announcement -> {
            ArchivedAnnouncement archivedAnnouncement = new ArchivedAnnouncement();
            archivedAnnouncement.setId(announcement.getId());
            archivedAnnouncement.setTitle(announcement.getTitle());
            archivedAnnouncement.setMessage(announcement.getMessage());
            archivedAnnouncement.setExpirationDate(announcement.getExpirationDate());
            archivedAnnouncementRepository.save(archivedAnnouncement);
            announcementService.deleteAnnouncementById(announcement.getId());
        });
    }
}
