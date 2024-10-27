package org.school.bps.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.school.bps.dto.InfoDTO;
import org.school.bps.entity.Info;
import org.school.bps.exception.InfoNotFoundException;
import org.school.bps.repository.InfoRepository;
import org.school.bps.repository.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfoService {
    
    private final InfoRepository infoRepo;
    private final StudentRepository studentRepo;
    
    @PostConstruct
    public void initializeSingleton() {
        if (infoRepo.count() == 0) {
            infoRepo.save(new Info());
        }
    }
    
    public InfoDTO fetchInfo() {
        Info info = infoRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new InfoNotFoundException("Info record not found"));
        return info.toInfoDTO();
    }
    
    public InfoDTO updateInfo(InfoDTO infoDTO) {
        Info info = infoRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new InfoNotFoundException("Info record not found"));
        
        info.setSchoolName(infoDTO.getSchoolName());
        info.setSchoolDetails(infoDTO.getSchoolDetails());
        info.setContactNumbers(infoDTO.getContactNumbers());
        info.setSocialMediaLinks(infoDTO.getSocialMediaLinks());
        info.setPhotoUrls(infoDTO.getPhotoUrls());
        info.setTotalRunningDays(infoDTO.getTotalRunningDays());
        
        infoRepo.save(info);  // Save the updated info
        return info.toInfoDTO();
    }
    
    public void modifyRunningDays(int runningDays) {
        Info info = infoRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new InfoNotFoundException("Info record not found"));
        
        info.setTotalRunningDays(runningDays);
        infoRepo.save(info);  // Save the updated running days
    }
    
    @Scheduled(cron = "0 * * * * ?")
    public void updateRunningDays() {
        Info info = infoRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new InfoNotFoundException("Info record not found"));
        int latestTotalRunningDays = info.getTotalRunningDays();
        
        studentRepo.findAll().forEach(student -> {
            student.setTotalDays(latestTotalRunningDays);
            studentRepo.save(student);
        });
        
        System.out.println("Updated total running days for all students.");
    }
}
