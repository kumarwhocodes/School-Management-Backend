package org.school.bps.repository;

import org.school.bps.entity.Announcement;
import org.school.bps.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer> {
    Optional<Info> findTopByOrderByIdDesc();
}
