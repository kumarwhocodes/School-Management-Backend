package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.AnnouncementDTO;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.InfoDTO;
import org.school.bps.service.AnnouncementService;
import org.school.bps.service.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;
    
    @GetMapping("/")
    public String infoGreeting(){
        return "This is information endpoint.";
    }
    
    @GetMapping("/fetch")
    public CustomResponse<InfoDTO> fetchInfo(){
        return new CustomResponse<>(
                HttpStatus.OK,
                "Info fetched successfully.",
                infoService.fetchInfo()
        );
    }
    
    @PostMapping("/create")
    public CustomResponse<InfoDTO> createInfo(
            @RequestBody InfoDTO info
    ){
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Info created successfully.",
                infoService.createInfo(info)
        );
    }
    
    @PutMapping("/update")
    public CustomResponse<InfoDTO> updateInfo(
            @RequestBody InfoDTO info
    ){
        return new CustomResponse<>(
                HttpStatus.OK,
                "Info updated successfully.",
                infoService.updateInfo(info)
        );
    }
    
    @DeleteMapping("/delete")
    public CustomResponse<String> deleteInfo(
            @PathVariable int id
    ){
        infoService.deleteInfo(id);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Info deleted successfully!!",
                "DELETED"
        );
    }
    
}
