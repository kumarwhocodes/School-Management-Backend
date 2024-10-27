package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.constants.Endpoints;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.TeacherDTO;
import org.school.bps.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.TEACHER)
@RequiredArgsConstructor
public class TeacherController {
    
    private final TeacherService teacherService;
    
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
    
    
}

