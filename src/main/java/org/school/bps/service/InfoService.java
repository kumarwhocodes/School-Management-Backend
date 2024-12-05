package org.school.bps.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.school.bps.dto.InfoDTO;
import org.school.bps.entity.Info;
import org.school.bps.exception.InfoNotFoundException;
import org.school.bps.repository.InfoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfoService {
    
    private final InfoRepository infoRepo;
    
    @PostConstruct
    public void initializeSingleton() {
        try {
            if (infoRepo.count() == 0) {
                Info i = new Info();
                i.setId(1);
                i.setSchoolName("Default School Name");
                i.setTotalRunningDays(365);
                infoRepo.save(i);
                System.out.println("Info entity initialized successfully.");
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize Info entity: " + e.getMessage());
        }
    }
    
    public InfoDTO fetchInfo() {
        // Fetch the single Info entity. Assumes there is only one record
        Info info = infoRepo.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new InfoNotFoundException("Info record not found"));
        return info.toInfoDTO();
    }
    
    public InfoDTO updateInfo(InfoDTO infoDTO) {
        // Fetch the single Info entity. Assumes there is only one record
        Info info = infoRepo.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new InfoNotFoundException("Info record not found"));
        
        info.setSchoolName(infoDTO.getSchoolName());
        info.setSchoolDetails(infoDTO.getSchoolDetails());
        info.setContactNumbers(infoDTO.getContactNumbers());
        info.setSocialMediaLinks(infoDTO.getSocialMediaLinks());
        info.setPhotoUrls(infoDTO.getPhotoUrls());
        info.setTotalRunningDays(infoDTO.getTotalRunningDays());
        
        infoRepo.save(info);
        return info.toInfoDTO();
    }
}
