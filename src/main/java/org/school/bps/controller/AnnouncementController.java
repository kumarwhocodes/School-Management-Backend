package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.AnnouncementDTO;
import org.school.bps.dto.CustomResponse;
import org.school.bps.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    
    private final AnnouncementService announcementService;
    
    @GetMapping("/")
    public String announcementGreeting() {
        return "This is announcement endpoint.";
    }
    
    @GetMapping("/fetch-all")
    public CustomResponse<List<AnnouncementDTO>> fetchAllAnnouncements() {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Announcement List fetched successfully.",
                announcementService.fetchAllAnnouncements()
        );
    }
    
    @PostMapping("/create")
    public CustomResponse<AnnouncementDTO> createAnnouncement(
            @RequestBody AnnouncementDTO announcement
    ) {
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Announcement created successfully.",
                announcementService.createAnnouncement(announcement)
        );
    }
    
    @PutMapping("/update")
    public CustomResponse<AnnouncementDTO> updateAnnouncement(
            @RequestBody AnnouncementDTO announcement
    ) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Announcement updated successfully.",
                announcementService.updateAnnouncement(announcement)
        );
    }
    
    @GetMapping("/{id}")
    public CustomResponse<AnnouncementDTO> fetchAnnouncementById(
            @PathVariable int id
    ) {
        return new CustomResponse<>(
                HttpStatus.FOUND,
                "Announcement fetched successfully.",
                announcementService.fetchAnnouncementById(id)
        );
    }
    
    @DeleteMapping("/{id}")
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
    
}
