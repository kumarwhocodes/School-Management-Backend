package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.constants.Endpoints;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.LeaveDTO;
import org.school.bps.enums.LeaveType;
import org.school.bps.service.LeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Endpoints.LEAVE)
public class LeaveController {
    
    private final LeaveService leaveService;
    
    @PostMapping(Endpoints.APPLY_LEAVE)
    public CustomResponse<String> applyForLeave(
            @RequestParam String teacherId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam LeaveType leaveType) {
        leaveService.applyForLeave(teacherId, LocalDate.parse(startDate), LocalDate.parse(endDate), leaveType);
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Leave applied successfully.",
                null
        );
    }
    
    @GetMapping(Endpoints.FETCH_ALL_LEAVES)
    public CustomResponse<List<LeaveDTO>> fetchAllLeaves() {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Leave list fetched successfully.",
                leaveService.fetchAllLeaves()
        );
    }
    
    @GetMapping(Endpoints.FETCH_LEAVE_BY_TEACHER_ID)
    public CustomResponse<List<LeaveDTO>> getLeavesByTeacher(@PathVariable String teacherId) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Leaves fetched successfully for teacher.",
                leaveService.getLeavesByTeacher(teacherId)
        );
    }
    
    @PostMapping(Endpoints.APPROVE_LEAVE)
    public CustomResponse<String> approveLeave(@PathVariable Long leaveId) {
        leaveService.approveLeave(leaveId);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Leave approved successfully.",
                null
        );
    }
    
    @PostMapping(Endpoints.REJECT_LEAVE)
    public CustomResponse<String> rejectLeave(@PathVariable Long leaveId) {
        leaveService.rejectLeave(leaveId);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Leave rejected successfully.",
                null
        );
    }
}
