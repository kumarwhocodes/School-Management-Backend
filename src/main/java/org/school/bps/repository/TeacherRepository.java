package org.school.bps.repository;

import org.school.bps.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    boolean existsByEmail(String email); // Check by email
    boolean existsByPhoneNumber(BigInteger phoneNumber); // Check by phone number
}
