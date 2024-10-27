package org.school.bps.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.school.bps.entity.Announcement;
import org.school.bps.entity.Info;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InfoDTO {
    
    private String schoolName;
    private String schoolDetails;
    private List<BigInteger> contactNumbers;
    private List<String> socialMediaLinks;
    private List<String> photoUrls;
    
    public Info toInfo(){
        return Info
                .builder()
                .schoolName(schoolName)
                .schoolDetails(schoolDetails)
                .photoUrls(photoUrls)
                .contactNumbers(contactNumbers)
                .socialMediaLinks(socialMediaLinks)
                .build();
    }
}
