package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.LeaveDTO;
import org.school.bps.entity.Leave;
import org.school.bps.entity.Teacher;
import org.school.bps.enums.LeaveStatus;
import org.school.bps.enums.LeaveType;
import org.school.bps.exception.LeaveNotFoundException;
import org.school.bps.exception.LeaveOverlapException;
import org.school.bps.exception.NoCasualLeavesRemainingException;
import org.school.bps.exception.TeacherNotFoundException;
import org.school.bps.repository.LeaveRepository;
import org.school.bps.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LeaveService {
    
    private final LeaveRepository leaveRepository;
    private final TeacherRepository teacherRepository;
    
    public void applyForLeave(String teacherId, LocalDate startDate, LocalDate endDate, LeaveType leaveType) {
        if (leaveRepository.existsByTeacherIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                teacherId, endDate, startDate)) {
            throw new LeaveOverlapException("Leave overlaps with existing leave!");
        }
        
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + teacherId + " not found!"));
        
        if (leaveType == LeaveType.CASUAL && teacher.getRemainingCasualLeaves() <= 0) {
            throw new NoCasualLeavesRemainingException("No casual leaves remaining for teacher with id: " + teacherId);
        }
        
        Leave leave = Leave.builder()
                .teacher(teacher)
                .startDate(startDate)
                .endDate(endDate)
                .leaveType(leaveType)
                .status(LeaveStatus.PENDING)
                .build();
        
        leaveRepository.save(leave);
    }
    
    public void approveLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new LeaveNotFoundException("Leave with id: " + leaveId + " not found!"));
        
        leave.setStatus(LeaveStatus.APPROVED);
        leaveRepository.save(leave);
        
        Teacher teacher = leave.getTeacher();
        
        if (leave.getLeaveType() == LeaveType.CASUAL) {
            int days = (int) (leave.getEndDate().toEpochDay() - leave.getStartDate().toEpochDay() + 1);
            teacher.setRemainingCasualLeaves(teacher.getRemainingCasualLeaves() - days);
            teacher.setUsedCasualLeaves(teacher.getUsedCasualLeaves() + days);
            teacherRepository.save(teacher);
        }
    }
    
    public void rejectLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new LeaveNotFoundException("Leave with id: " + leaveId + " not found!"));
        
        leave.setStatus(LeaveStatus.REJECTED);
        leaveRepository.save(leave);
    }
    
    public List<LeaveDTO> getLeavesByTeacher(String teacherId) {
        return leaveRepository.findByTeacherId(teacherId)
                .stream()
                .map(Leave::toLeaveDTO)
                .toList();
    }
    
    public List<LeaveDTO> fetchAllLeaves() {
        return leaveRepository.findAll()
                .stream()
                .map(Leave::toLeaveDTO)
                .toList();
    }
}

