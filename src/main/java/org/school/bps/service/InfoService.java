package org.school.bps.service;

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
    
    public InfoDTO createInfo(InfoDTO infoDTO) {
        Info info = infoDTO.toInfo();
        return infoRepo.save(info).toInfoDTO();
    }
    
    public InfoDTO fetchInfo() {
        return infoRepo.findTopByOrderByIdDesc()
                .map(Info::toInfoDTO)
                .orElseThrow(() -> new InfoNotFoundException("No Info available."));
    }
    
    
    public InfoDTO updateInfo(InfoDTO infoDTO) {
        Info existingInfo = infoRepo.findById(infoDTO.getId())
                .orElseThrow(() -> new InfoNotFoundException("Info with ID " + infoDTO.getId() + " not found."));
        
        existingInfo.setSchoolName(infoDTO.getSchoolName());
        existingInfo.setSchoolDetails(infoDTO.getSchoolDetails());
        existingInfo.setContactNumbers(infoDTO.getContactNumbers());
        existingInfo.setSocialMediaLinks(infoDTO.getSocialMediaLinks());
        existingInfo.setPhotoUrls(infoDTO.getPhotoUrls());
        
        return infoRepo.save(existingInfo).toInfoDTO();
    }
    
    public void deleteInfo(int id) {
        if (!infoRepo.existsById(id)) {
            throw new InfoNotFoundException("Info with ID " + id + " not found.");
        }
        infoRepo.deleteById(id);
    }
}
