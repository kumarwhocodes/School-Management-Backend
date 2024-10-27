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
        if (infoRepo.count() == 0) {
            infoRepo.save(new Info());  // Initializes singleton instance if not present
        }
    }
    
    public InfoDTO fetchInfo() {
        Info info = infoRepo.findAll().getFirst(); // Always fetches the single record
        return info.toInfoDTO();
    }
    
    public InfoDTO updateInfo(InfoDTO infoDTO) {
        Info info = infoRepo.findAll().getFirst();
        info.setSchoolName(infoDTO.getSchoolName());
        info.setSchoolDetails(infoDTO.getSchoolDetails());
        info.setContactNumbers(infoDTO.getContactNumbers());
        info.setSocialMediaLinks(infoDTO.getSocialMediaLinks());
        info.setPhotoUrls(infoDTO.getPhotoUrls());
        infoRepo.save(info);  // Save the updated info
        return info.toInfoDTO();
    }
    
//    public InfoDTO createInfo(InfoDTO infoDTO) {
//        Info info = infoDTO.toInfo();
//        return infoRepo.save(info).toInfoDTO();
//    }
//
//    public InfoDTO fetchInfo() {
//        return infoRepo.findTopByOrderByIdDesc()
//                .map(Info::toInfoDTO)
//                .orElseThrow(() -> new InfoNotFoundException("No Info available."));
//    }
//
//
//    public InfoDTO updateInfo(InfoDTO infoDTO) {
//        Info existingInfo = infoRepo.findById(infoDTO.getId())
//                .orElseThrow(() -> new InfoNotFoundException("Info with ID " + infoDTO.getId() + " not found."));
//
//        existingInfo.setSchoolName(infoDTO.getSchoolName());
//        existingInfo.setSchoolDetails(infoDTO.getSchoolDetails());
//        existingInfo.setContactNumbers(infoDTO.getContactNumbers());
//        existingInfo.setSocialMediaLinks(infoDTO.getSocialMediaLinks());
//        existingInfo.setPhotoUrls(infoDTO.getPhotoUrls());
//
//        return infoRepo.save(existingInfo).toInfoDTO();
//    }
//
//    public void deleteInfo(int id) {
//        if (!infoRepo.existsById(id)) {
//            throw new InfoNotFoundException("Info with ID " + id + " not found.");
//        }
//        infoRepo.deleteById(id);
//    }
}
