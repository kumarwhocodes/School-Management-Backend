package org.school.bps.repository;

import org.school.bps.entity.Student;
import org.school.bps.entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {
    boolean existsByStudentAndDate(Student student, LocalDate date);
}
