package org.school.bps.repository;

import org.school.bps.entity.TeacherAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherAttendanceRepository extends JpaRepository<TeacherAttendance, Long> {
    boolean existsByTeacherIdAndDate(String teacherId, LocalDate date);
    
    List<TeacherAttendance> findByTeacherIdAndDateBetween(String teacherTId, LocalDate startDate, LocalDate endDate);
}
