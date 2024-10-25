package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.TeacherDTO;
import org.school.bps.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    
    private final TeacherService teacherService;
    
    @GetMapping("/")
    public String greetingPage() {
        return "This is Teacher's page.";
    }
    
    @GetMapping("/fetch-all")
    public CustomResponse<List<TeacherDTO>> fetchAllTeachers() {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Teacher List fetched successfully.",
                teacherService.fetchAllTeachers()
        );
    }
    
    @PostMapping("/create")
    public CustomResponse<TeacherDTO> createTeacher(
            @RequestBody TeacherDTO teacher
    ) {
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Teacher created successfully.",
                teacherService.createTeacher(teacher)
        );
    }
    
    @PutMapping("/update")
    public CustomResponse<TeacherDTO> updateTeacher(
            @RequestBody TeacherDTO teacher
    ) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Teacher updated successfully.",
                teacherService.updateTeacher(teacher)
        );
    }
    
    @GetMapping("/{tId}")
    public CustomResponse<TeacherDTO> fetchTeacherByTId(
            @PathVariable String tId
    ) {
        return new CustomResponse<>(
                HttpStatus.FOUND,
                "Teacher fetched successfully.",
                teacherService.fetchTeacherByTId(tId)
        );
    }
    
    @DeleteMapping("/{tId}")
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

