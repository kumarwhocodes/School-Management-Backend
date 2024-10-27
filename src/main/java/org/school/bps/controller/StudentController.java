package org.school.bps.controller;

import lombok.RequiredArgsConstructor;
import org.school.bps.constants.Endpoints;
import org.school.bps.dto.CustomResponse;
import org.school.bps.dto.StudentDTO;
import org.school.bps.service.StudentAttendanceService;
import org.school.bps.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.STUDENT)
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;
    private final StudentAttendanceService studentAttendanceService;
    
    @GetMapping(Endpoints.STUDENT_GREETING)
    public String greetingPage() {
        return "This is Student's page.";
    }
    
    @GetMapping(Endpoints.FETCH_ALL_STUDENTS)
    public CustomResponse<List<StudentDTO>> fetchAllStudents() {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Student List fetched successfully.",
                studentService.fetchAllStudents()
        );
    }
    
    @PostMapping(Endpoints.CREATE_STUDENT)
    public CustomResponse<StudentDTO> createStudent(
            @RequestBody StudentDTO student
    ) {
        return new CustomResponse<>(
                HttpStatus.CREATED,
                "Student created successfully.",
                studentService.createStudent(student)
        );
    }
    
    @PutMapping(Endpoints.UPDATE_STUDENT)
    public CustomResponse<StudentDTO> updateStudent(
            @RequestBody StudentDTO student
    ) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Student updated successfully.",
                studentService.updateStudent(student)
        );
    }
    
    @GetMapping(Endpoints.FETCH_STUDENT_BY_ID)
    public CustomResponse<StudentDTO> fetchStudentByStuId(
            @PathVariable String stuId
    ) {
        return new CustomResponse<>(
                HttpStatus.FOUND,
                "Student fetched successfully.",
                studentService.fetchStudentByStuId(stuId)
        );
    }
    
    @DeleteMapping(Endpoints.DELETE_STUDENT_BY_ID)
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
    
    @GetMapping(Endpoints.FILTER_BY_CLASS)
    public CustomResponse<List<StudentDTO>> fetchStudentsByClass(
            @PathVariable String className
    ) {
        return new CustomResponse<>(
                HttpStatus.OK,
                "Students filtered by class fetched successfully.",
                studentService.fetchStudentsByClass(className)
        );
    }
    
    @PostMapping(Endpoints.MARK_ATTENDANCE)
    public CustomResponse<String> markAttendance(
            @RequestBody List<String> studentIds
    ) {
        studentAttendanceService.markAttendanceForStudents(studentIds);
        return new CustomResponse<>(
                HttpStatus.OK,
                "Attendance updated successfully.",
                "UPDATED!"
        );
    }
    
}
