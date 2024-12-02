package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.entity.Teacher;
import org.school.bps.entity.TeacherAttendance;
import org.school.bps.exception.TeacherNotFoundException;
import org.school.bps.repository.TeacherAttendanceRepository;
import org.school.bps.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherAttendanceService {
    
    private final TeacherAttendanceRepository attendanceRepository;
    private final AcademicCalendarService academicCalendarService;
    private final TeacherRepository teacherRepository;
    
    /**
     * Marks attendance for a list of teachers for today.
     * Throws an exception if today is not an academic day.
     *
     * @param teacherIds List of teacher IDs whose attendance is to be marked.
     */
    public void markAttendanceForTeachers(List<String> teacherIds) {
        LocalDate today = LocalDate.now();
        
        // Check if today is an academic day
        if (!academicCalendarService.isAcademicDay(today)) {
            throw new IllegalStateException("Attendance cannot be updated for today as it's not an academic day.");
        }
        
        for (String teacherId : teacherIds) {
            // Check if attendance for today is already recorded
            if (!attendanceRepository.existsByTeacherIdAndDate(teacherId, today)) {
                Teacher teacher = teacherRepository.findById(teacherId)
                        .orElseThrow(() -> new TeacherNotFoundException("Teacher with ID: " + teacherId + " not found."));
                
                TeacherAttendance attendance = TeacherAttendance.builder()
                        .teacher(teacher)
                        .date(today)
                        .isPresent(true)
                        .build();
                attendanceRepository.save(attendance);
                
                teacher.setPresentDays(teacher.getPresentDays() + 1);
                teacherRepository.save(teacher);
            }
        }
    }
    
    /**
     * Calculates the monthly attendance for a teacher.
     *
     * @param teacherId The ID of the teacher.
     * @param yearMonth The YearMonth for which attendance needs to be calculated.
     * @return The count of days the teacher was present in the specified month.
     */
    public int calculateMonthlyAttendance(String teacherId, YearMonth yearMonth) {
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        
        // Fetch attendance records for the specified teacher and month
        List<TeacherAttendance> attendanceList = attendanceRepository.findByTeacherIdAndDateBetween(teacherId, startOfMonth, endOfMonth);
        
        return (int) attendanceList.stream()
                .filter(TeacherAttendance::getIsPresent)
                .count();
    }
}
