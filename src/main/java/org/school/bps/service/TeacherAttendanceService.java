package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.entity.Teacher;
import org.school.bps.entity.TeacherAttendance;
import org.school.bps.repository.LeaveRepository;
import org.school.bps.repository.TeacherAttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherAttendanceService {
    
    private final TeacherAttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    
    /**
     * Marks attendance for a list of teachers for today.
     * Throws an exception if today is not an academic day.
     *
     * @param teacherIds List of teacher IDs whose attendance is to be marked.
     */
    public void markAttendanceForTeachers(List<String> teacherIds) {
        LocalDate today = LocalDate.now();
        
        for (String teacherId : teacherIds) {
            boolean isOnApprovedLeave = leaveRepository.existsByTeacherIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                    teacherId, today, today);
            
            if (!attendanceRepository.existsByTeacherIdAndDate(teacherId, today)) {
                boolean isPresent = !isOnApprovedLeave && teacherIds.contains(teacherId);
                
                TeacherAttendance attendance = TeacherAttendance.builder()
                        .teacher(Teacher.builder().id(teacherId).build())
                        .date(today)
                        .isPresent(isPresent)
                        .build();
                
                attendanceRepository.save(attendance);
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
