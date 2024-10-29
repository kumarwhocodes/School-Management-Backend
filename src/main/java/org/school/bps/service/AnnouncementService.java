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
    private final AcademicCalendarService academicCalendarService;
    
    public AnnouncementDTO createAnnouncement(AnnouncementDTO announcement) {
        if (announcementRepo.existsById(announcement.getAId())) {
            throw new AnnouncementAlreadyExistsException(announcement.getAId());
        } else {
            Announcement savedAnnouncement = announcementRepo.save(announcement.toAnnouncement());
            
            // If it's a holiday, exclude the holiday dates from academic days
            if (savedAnnouncement.isHoliday()) {
                academicCalendarService.excludeHolidays();
                academicCalendarService.updateTotalAcademicDaysInInfo();
            }
            
            return savedAnnouncement.toAnnouncementDTO();
        }
    }
    
    public List<AnnouncementDTO> fetchAllAnnouncements() {
        List<Announcement> announcements = announcementRepo.findAll();
        return announcements.stream()
                .map(Announcement::toAnnouncementDTO)
                .toList();
    }
    
    public AnnouncementDTO updateAnnouncement(AnnouncementDTO announcement) {
        Announcement existing = announcementRepo.findById(announcement.getAId())
                .orElseThrow(() -> new AnnouncementNotFoundException(announcement.getAId()));
        
        boolean wasHoliday = existing.isHoliday();
        
        existing.setTitle(announcement.getTitle());
        existing.setMessage(announcement.getMessage());
        existing.setExpirationDate(announcement.getExpirationDate());
        existing.setStartDate(announcement.getStartDate());
        existing.setEndDate(announcement.getEndDate());
        existing.setHoliday(announcement.isHoliday());
        
        Announcement updatedAnnouncement = announcementRepo.save(existing);
        
        // Refresh academic days if the announcement is marked as a holiday
        if (wasHoliday != updatedAnnouncement.isHoliday()) {
            academicCalendarService.excludeHolidays();
            academicCalendarService.updateTotalAcademicDaysInInfo();
        }
        
        return updatedAnnouncement.toAnnouncementDTO();
    }
    
    
    public AnnouncementDTO fetchAnnouncementById(int id) {
        return announcementRepo
                .findById(id)
                .map(Announcement::toAnnouncementDTO)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
    }
    
    
    public void deleteAnnouncementById(int id) {
        if (!announcementRepo.existsById(id)) {
            throw new AnnouncementNotFoundException(id);
        }
        
        // Fetch the announcement to check if it's a holiday
        Announcement announcementToDelete = announcementRepo.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
        
        boolean isHoliday = announcementToDelete.isHoliday();
        
        // Delete the announcement
        announcementRepo.deleteById(id);
        
        // Refresh holidays if the announcement was a holiday
        if (isHoliday) {
            // Logic to unmark the holiday and make it an academic day
            academicCalendarService.unmarkHoliday(announcementToDelete);
            academicCalendarService.updateTotalAcademicDaysInInfo();
        }
    }
    
    public void archiveAnnouncementById(int id) {
        if (!announcementRepo.existsById(id)) {
            throw new AnnouncementNotFoundException(id);
        }
        
        // Simply delete the announcement record, without changing its holiday status
        announcementRepo.deleteById(id);
        // You may implement a separate archive logic if needed, such as moving the record to another table.
    }
    
    public List<AnnouncementDTO> findExpiredAnnouncements(LocalDate currentDate) {
        List<Announcement> expiredAnnouncements = announcementRepo.findByExpirationDateBefore(currentDate);
        return expiredAnnouncements.stream()
                .map(Announcement::toAnnouncementDTO)
                .toList();
    }
}
