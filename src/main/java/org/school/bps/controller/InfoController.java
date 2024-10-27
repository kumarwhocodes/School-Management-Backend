package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.constants.Endpoints;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.InfoDTO;
import org.school.bps.service.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.INFO)
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;
    
    @GetMapping(Endpoints.INFO_GREETING)
    public String infoGreeting(){
        return "This is information endpoint.";
    }
    
    @GetMapping(Endpoints.FETCH_INFO)
    public CustomResponse<InfoDTO> fetchInfo(){
        return new CustomResponse<>(
                HttpStatus.OK,
                "Info fetched successfully.",
                infoService.fetchInfo()
        );
    }
    
    @PutMapping(Endpoints.UPDATE_INFO)
    public CustomResponse<InfoDTO> updateInfo(
            @RequestBody InfoDTO info
    ){
        return new CustomResponse<>(
                HttpStatus.OK,
                "Info updated successfully.",
                infoService.updateInfo(info)
        );
    }
    
}
