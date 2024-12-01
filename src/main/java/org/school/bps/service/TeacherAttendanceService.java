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
    
    public void markAttendanceForTeachers(List<String> teacherIds) {
        LocalDate today = LocalDate.now();
        
        if (!academicCalendarService.isAcademicDay(today)) {
            System.out.println("Attendance cannot be updated for today as it's not an academic day.");
            return;
        }
        
        for (String teacherId : teacherIds) {
            if (!attendanceRepository.existsByTeacherIdAndDate(teacherId, today)) {
                TeacherAttendance attendance = TeacherAttendance.builder()
                        .teacher(Teacher.builder().id(teacherId).build())
                        .date(today)
                        .isPresent(true)
                        .build();
                
                attendanceRepository.save(attendance);
                
                Teacher teacher = teacherRepository.findById(teacherId)
                        .orElseThrow(()->new TeacherNotFoundException("Teacher with id:"+teacherId+"not found!"));
                
                teacher.setPresentDays(teacher.getPresentDays() + 1);
                teacherRepository.save(teacher);
            }
        }
    }

    public int calculateMonthlyAttendance(String teacherId, YearMonth yearMonth) {
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        
        List<TeacherAttendance> attendanceList = attendanceRepository.findByTeacherIdAndDateBetween(teacherId, startOfMonth, endOfMonth);
        
        return (int) attendanceList.stream()
                .filter(TeacherAttendance::getIsPresent)
                .count();
    }
}
