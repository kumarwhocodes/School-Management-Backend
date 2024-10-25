package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.AnnouncementDTO;
import org.school.bps.entity.Announcement;
import org.school.bps.exception.AnnouncementAlreadyExistsException;
import org.school.bps.exception.AnnouncementNotFoundException;
import org.school.bps.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    
    private final AnnouncementRepository announcementRepo;
    
    public AnnouncementDTO createAnnouncement(AnnouncementDTO announcement) {
        if (announcementRepo.existsById(announcement.getId())) {
            throw new AnnouncementAlreadyExistsException(announcement.getId());
        } else {
            return announcementRepo
                    .save(announcement.toAnnouncement())
                    .toAnnouncementDTO();
        }
    }
    
    public List<AnnouncementDTO> fetchAllAnnouncements() {
        List<Announcement> announcements = announcementRepo.findAll();
        return announcements.stream()
                .map(Announcement::toAnnouncementDTO)
                .toList();
    }
    
    public AnnouncementDTO updateAnnouncement(AnnouncementDTO announcement) {
        if (!announcementRepo.existsById(announcement.getId())) {
            throw new AnnouncementNotFoundException(announcement.getId());
        } else {
            Announcement existing = announcementRepo.findById(announcement.getId())
                    .orElseThrow(() -> new AnnouncementNotFoundException(announcement.getId()));
            existing.setTitle(announcement.getTitle());
            existing.setMessage(announcement.getMessage());
            existing.setExpirationDate(announcement.getExpirationDate());
            existing.setStartDate(announcement.getStartDate());
            existing.setEndDate(announcement.getEndDate());
            return announcementRepo.save(existing).toAnnouncementDTO();
        }
    }
    
    public AnnouncementDTO fetchAnnouncementById(int id) {
        return announcementRepo
                .findById(id)
                .map(Announcement::toAnnouncementDTO)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
    }
    
    public void deleteAnnouncementById(int id) {
        if (!announcementRepo.existsById(id))
            throw new AnnouncementNotFoundException(id);
        
        announcementRepo.deleteById(id);
    }
    
    public List<AnnouncementDTO> findExpiredAnnouncements(LocalDate currentDate) {
        List<Announcement> expiredAnnouncements = announcementRepo.findByExpirationDateBefore(currentDate);
        return expiredAnnouncements.stream()
                .map(Announcement::toAnnouncementDTO)
                .toList();
    }
}
