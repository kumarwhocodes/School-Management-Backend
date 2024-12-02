package org.school.bps.service;

import org.school.bps.entity.Announcement;
import org.school.bps.entity.Info;
import org.school.bps.exception.InfoNotFoundException;
import org.school.bps.repository.AnnouncementRepository;
import org.school.bps.repository.InfoRepository;
import org.school.bps.repository.StudentRepository;
import org.school.bps.repository.TeacherRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Service
public class AcademicCalendarService {
    
    private final AnnouncementRepository announcementRepo;
    private final StudentRepository studentRepo;
    private final TeacherRepository teacherRepo;
    private final InfoRepository infoRepo;
    private Set<LocalDate> academicDays;
    
    public AcademicCalendarService(AnnouncementRepository announcementRepo, InfoRepository infoRepo, StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
        this.announcementRepo = announcementRepo;
        this.infoRepo = infoRepo;
        generateAcademicDays();
        excludeHolidays();
        updateTotalAcademicDaysInInfo();
    }
    
    public void excludeHolidays() {
        announcementRepo.findAllByIsHolidayTrue().forEach(announcement -> {
            LocalDate start = announcement.getStartDate();
            LocalDate end = announcement.getEndDate();
            
            // Remove each date in the range from academic days
            start.datesUntil(end.plusDays(1)).forEach(date -> {
                if (academicDays.contains(date)) {
                    academicDays.remove(date);
                    System.out.println("Removing date from academic days: " + date); // Debug log
                }
            });
        });
    }
    
    private void generateAcademicDays() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        
        // Initialize with only weekdays
        academicDays = startDate.datesUntil(endDate.plusDays(1))
                .filter(this::isDefaultAcademicDay)
                .collect(Collectors.toCollection(ConcurrentSkipListSet::new));
    }
    
    //exclude sundays
    private boolean isDefaultAcademicDay(LocalDate date) {
        return !(date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }
    
    public int getTotalAcademicDays() {
        return academicDays.size();
    }
    
    public boolean isAcademicDay(LocalDate date) {
        return academicDays.contains(date);
    }
    
    public void unmarkHoliday(Announcement announcement) {
        LocalDate holidayDate = announcement.getStartDate();
        
        if (!academicDays.contains(holidayDate)) {
            academicDays.add(holidayDate);
            System.out.println("Marking date as academic day: " + holidayDate);
        }
        
        updateTotalAcademicDaysInInfo();
    }
    
    
    public void updateTotalAcademicDaysInInfo() {
        Info info = infoRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new InfoNotFoundException("Info record not found"));
        int totalDays = academicDays.size();
        System.out.println("Total academic days after exclusion: " + totalDays);
        info.setTotalRunningDays(totalDays);
        infoRepo.save(info);
    }
    
    
    @Scheduled(cron = "0 0 * * * ?")
    public void updateTotalDaysForAllStudents() {
        updateTotalAcademicDaysInInfo();
        int totalDays = getTotalAcademicDays();
        
        studentRepo.findAll().forEach(student -> {
            student.setTotalDays(totalDays);
            studentRepo.save(student);
        });
        
        System.out.println("Updated total academic days for ALL STUDENTS based on the academic calendar.");
    }
    
    @Scheduled(cron = "0 0 * * * ?")
    public void updateTotalDaysForAllTeachers() {
        int totalDays = YearMonth.now().lengthOfMonth();
        
        teacherRepo.findAll().forEach(teacher -> {
            teacher.setTotalDays(totalDays);
            teacherRepo.save(teacher);
        });
        System.out.println("Updated total academic days" + totalDays + "for ALL TEACHERS based on the MONTH.");
    }
}
