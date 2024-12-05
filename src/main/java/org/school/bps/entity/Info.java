package org.school.bps.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.school.bps.dto.InfoDTO;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "info")
public class Info {
    
    @Id
    private int id = 1;
    private String schoolName;
    private String schoolDetails;
    @ElementCollection
    private List<BigInteger> contactNumbers;
    @ElementCollection
    private List<String> socialMediaLinks;
    @ElementCollection
    private List<String> photoUrls;
    private int totalRunningDays;
    
    public InfoDTO toInfoDTO() {
        return InfoDTO
                .builder()
                .schoolName(schoolName)
                .schoolDetails(schoolDetails)
                .photoUrls(photoUrls)
                .contactNumbers(contactNumbers)
                .socialMediaLinks(socialMediaLinks)
                .totalRunningDays(totalRunningDays)
                .build();
    }
    
}
