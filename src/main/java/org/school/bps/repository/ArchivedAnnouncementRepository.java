package org.school.bps.repository;

import org.school.bps.entity.ArchivedAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedAnnouncementRepository extends JpaRepository<ArchivedAnnouncement, Integer> {

}
