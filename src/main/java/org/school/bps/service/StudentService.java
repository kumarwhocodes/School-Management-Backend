package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.StudentDTO;
import org.school.bps.entity.Student;
import org.school.bps.exception.StudentNotFoundException;
import org.school.bps.repository.StudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository studentRepo;
    
    public StudentDTO createStudent(StudentDTO studentDTO) {
        // Validate incoming DTO
        if (studentDTO.getStuId() == null || studentDTO.getStuName() == null) {
            throw new IllegalArgumentException("Student ID and name cannot be null");
        }
        
        Student student = studentDTO.toStudent();
        student.setTotalDays(0);
        
        try {
            Student savedStudent = studentRepo.save(student);
            return savedStudent.toStudentDTO();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error saving student: " + e.getMessage(), e);
        }
    }
    
    public List<StudentDTO> fetchAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream()
                .map(Student::toStudentDTO)
                .toList();
    }
    
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        // Check if the student exists before updating
        Student existingStudent = studentRepo.findById(studentDTO.getStuId())
                .orElseThrow(() -> new StudentNotFoundException(studentDTO.getStuId()));
        
        // Update student fields with the provided DTO
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        existingStudent.setStuName(studentDTO.getStuName());
        
        Student updatedStudent = studentRepo.save(existingStudent);
        return updatedStudent.toStudentDTO();
    }
    
    public StudentDTO fetchStudentByStuId(String stuId) {
        return studentRepo
                .findById(stuId)
                .map(Student::toStudentDTO)
                .orElseThrow(() -> new StudentNotFoundException(stuId));
    }
    
    public void deleteStudentByStuId(String stuId) {
        if (!studentRepo.existsById(stuId)) {
            throw new StudentNotFoundException(stuId);
        }
        studentRepo.deleteById(stuId);
    }
    
    public List<StudentDTO> fetchStudentsByClass(String className) {
        List<Student> students = studentRepo.findByClassName(className);
        return students.stream()
                .map(Student::toStudentDTO)
                .toList();
    }
}
