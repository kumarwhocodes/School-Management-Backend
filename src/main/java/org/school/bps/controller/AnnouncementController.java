package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.constants.Endpoints;
import org.school.bps.dto.AnnouncementDTO;
import org.school.bps.dto.CustomResponse;
import org.school.bps.service.AcademicCalendarService;
import org.school.bps.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.ANNOUNCEMENT)
@RequiredArgsConstructor
public class AnnouncementController {
    
    private final AnnouncementService announcementService;
    private final AcademicCalendarService academicCalendarService;
    
    @GetMapping(Endpoints.ANNOUNCEMENT_GREETING)
    public String announcementGreeting() {
        return "This is announcement endpoint.";
    }
    
    @GetMapping(Endpoints.FETCH_ALL_ANNOUNCEMENTS)
    public CustomResponse<List<AnnouncementDTO>> fetchAllAnnouncements() {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Announcement List fetched successfully.",
                announcementService.fetchAllAnnouncements()
        );
    }
    
    @PostMapping(Endpoints.CREATE_ANNOUNCEMENT)
    public CustomResponse<AnnouncementDTO> createAnnouncement(
            @RequestBody AnnouncementDTO announcement
    ) {
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Announcement created successfully.",
                announcementService.createAnnouncement(announcement)
        );
    }
    
    @PutMapping(Endpoints.UPDATE_ANNOUNCEMENT)
    public CustomResponse<AnnouncementDTO> updateAnnouncement(
            @RequestBody AnnouncementDTO announcement
    ) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Announcement updated successfully.",
                announcementService.updateAnnouncement(announcement)
        );
    }
    
    @GetMapping(Endpoints.FETCH_ANNOUNCEMENT_BY_ID)
    public CustomResponse<AnnouncementDTO> fetchAnnouncementById(
            @PathVariable int id
    ) {
        return new CustomResponse<>(
                HttpStatus.FOUND,
                "Announcement fetched successfully.",
                announcementService.fetchAnnouncementById(id)
        );
    }
    
    @DeleteMapping(Endpoints.DELETE_ANNOUNCEMENT_BY_ID)
    public CustomResponse<String> deleteAnnouncementById(
            @PathVariable int id
    ) {
        announcementService.deleteAnnouncementById(id);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Announcement deleted successfully!",
                "DELETED!"
        );
    }
    
    @GetMapping("/fetchacademic")
    public int fetchAcademicDays(){
        return academicCalendarService.getTotalAcademicDays();
    }
    
}
