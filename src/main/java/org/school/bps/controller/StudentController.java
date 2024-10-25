package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.StudentDTO;
import org.school.bps.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;
    
    @GetMapping("/")
    public String greetingPage() {
        return "This is Student's page.";
    }
    
    @GetMapping("/fetch-all")
    public CustomResponse<List<StudentDTO>> fetchAllStudents() {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Student List fetched successfully.",
                studentService.fetchAllStudents()
        );
    }
    
    @PostMapping("/create")
    public CustomResponse<StudentDTO> createStudent(
            @RequestBody StudentDTO student
    ) {
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Student created successfully.",
                studentService.createStudent(student)
        );
    }
    
    @PutMapping("/update")
    public CustomResponse<StudentDTO> updateStudent(
            @RequestBody StudentDTO student
    ){
        return new CustomResponse<>(
                HttpStatus.OK,
                "Student updated successfully.",
                studentService.updateStudent(student)
        );
    }
    
    @GetMapping("/{stuId}")
    public CustomResponse<StudentDTO> fetchStudentByStuId(
            @PathVariable String stuId
    ) {
        return new CustomResponse<>(
                HttpStatus.FOUND,
                "Student fetched successfully.",
                studentService.fetchStudentByStuId(stuId)
        );
    }
    
    @DeleteMapping("/{stuId}")
    public CustomResponse<String> deleteStudentByStuId(
            @PathVariable String stuId
    ) {
        studentService.deleteStudentByStuId(stuId);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Student deleted successfully!",
                "DELETED!"
        );
    }
    
    @GetMapping("/filter-by-class/{className}")
    public CustomResponse<List<StudentDTO>> fetchStudentsByClass(
            @PathVariable String className
    ) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Students filtered by class fetched successfully.",
                studentService.fetchStudentsByClass(className)
        );
    }
    
    
}
