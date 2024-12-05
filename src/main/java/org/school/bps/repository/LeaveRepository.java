package org.school.bps.repository;

import org.school.bps.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByTeacherId(String teacherId);
    
    boolean existsByTeacherIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String teacherId, LocalDate start, LocalDate end);
}

