package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.entity.Student;
import org.school.bps.entity.StudentAttendance;
import org.school.bps.repository.StudentAttendanceRepository;
import org.school.bps.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentAttendanceService {
    
    private final StudentAttendanceRepository studentAttendanceRepo;
    private final StudentRepository studentRepo;
    
    public void markAttendanceForStudents(List<String> studentIds) {
        LocalDate today = LocalDate.now();
        
        for (String studentId : studentIds) {
            Student student = studentRepo.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
            
            // Check if attendance for today already exists
            if (!studentAttendanceRepo.existsByStudentAndDate(student, today)) {
                // Create a new attendance record
                StudentAttendance attendance = new StudentAttendance();
                attendance.setStudent(student);
                attendance.setDate(today);
                
                studentAttendanceRepo.save(attendance);
                
                // Update presentDays count
                student.setPresentDays(student.getPresentDays() + 1);
                studentRepo.save(student);
            }
        }
    }
}
