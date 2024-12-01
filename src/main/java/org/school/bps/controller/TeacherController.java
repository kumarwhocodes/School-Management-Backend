package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.constants.Endpoints;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.TeacherDTO;
import org.school.bps.entity.Teacher;
import org.school.bps.entity.TeacherAttendance;
import org.school.bps.exception.TeacherNotFoundException;
import org.school.bps.service.TeacherAttendanceService;
import org.school.bps.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping(Endpoints.TEACHER)
@RequiredArgsConstructor
public class TeacherController {
    
    private final TeacherService teacherService;
    private final TeacherAttendanceService teacherAttendanceService;
    
    @GetMapping(Endpoints.TEACHER_GREETING)
    public String greetingPage() {
        return "This is Teacher's page.";
    }
    
    @GetMapping(Endpoints.FETCH_ALL_TEACHERS)
    public CustomResponse<List<TeacherDTO>> fetchAllTeachers() {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Teacher List fetched successfully.",
                teacherService.fetchAllTeachers()
        );
    }
 
    @PostMapping(Endpoints.CREATE_TEACHER)
    public CustomResponse<TeacherDTO> createTeacher(
            @RequestBody TeacherDTO teacher
    ) {
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Teacher created successfully.",
                teacherService.createTeacher(teacher)
        );
    }
    
    @PutMapping(Endpoints.UPDATE_TEACHER)
    public CustomResponse<TeacherDTO> updateTeacher(
            @RequestBody TeacherDTO teacher
    ) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Teacher updated successfully.",
                teacherService.updateTeacher(teacher)
        );
    }
    
    @GetMapping(Endpoints.FETCH_TEACHER_BY_ID)
    public CustomResponse<TeacherDTO> fetchTeacherByTId(
            @PathVariable String tId
    ) {
        return new CustomResponse<>(
                HttpStatus.FOUND,
                "Teacher fetched successfully.",
                teacherService.fetchTeacherByTId(tId)
        );
    }
    
    @DeleteMapping(Endpoints.DELETE_TEACHER_BY_ID)
    public CustomResponse<String> deleteTeacherByTId(
            @PathVariable String tId
    ) {
        teacherService.deleteTeacherByTId(tId);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Teacher deleted successfully!",
                "DELETED!"
        );
    }
    
    @PostMapping(Endpoints.MARK_TEACHER_ATTENDANCE)
    public CustomResponse<String> markTeacherAttendance(
            @RequestBody List<String> teacherIds
    ) {
        teacherAttendanceService.markAttendanceForTeachers(teacherIds);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Teacher attendance updated successfully.",
                "UPDATED!"
        );
    }
    
    // Endpoint to calculate monthly attendance for a teacher
    @GetMapping(Endpoints.FIND_MONTHLY_ATTENDANCE)
    public int getMonthlyAttendance(
            @RequestParam String teacherId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth) {
        return teacherAttendanceService.calculateMonthlyAttendance(teacherId, yearMonth);
    }
    
}

