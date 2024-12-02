package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.entity.Student;
import org.school.bps.entity.StudentAttendance;
import org.school.bps.exception.StudentNotFoundException;
import org.school.bps.exception.AttendanceMarkingException;
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
    private final AcademicCalendarService academicCalendarService;
    
    public void markAttendanceForStudents(List<String> studentIds) {
        LocalDate today = LocalDate.now();
        
        // Check if today is an academic day
        if (!academicCalendarService.isAcademicDay(today)) {
            throw new AttendanceMarkingException("Today is a holiday, attendance cannot be marked.");
        }
        
        for (String studentId : studentIds) {
            Student student = studentRepo.findById(studentId)
                    .orElseThrow(() -> new StudentNotFoundException(studentId));
            
            // Check if attendance for today already exists
            if (!studentAttendanceRepo.existsByStudentAndDate(student, today)) {
                StudentAttendance attendance = new StudentAttendance();
                attendance.setStudent(student);
                attendance.setDate(today);
                
                studentAttendanceRepo.save(attendance);
                
                student.setPresentDays(student.getPresentDays() + 1);
                studentRepo.save(student);
            }
        }
    }
}
