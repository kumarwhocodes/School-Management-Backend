package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.TeacherDTO;
import org.school.bps.entity.Teacher;
import org.school.bps.exception.TeacherAlreadyExistsException;
import org.school.bps.exception.TeacherNotFoundException;
import org.school.bps.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    
    private final TeacherRepository teacherRepo;
    
    public TeacherDTO createTeacher(TeacherDTO teacher) {
        // Validate that the teacher ID is not null or empty
        if (teacher.getId() == null || teacher.getId().isEmpty()) {
            throw new TeacherNotFoundException("Teacher ID cannot be null or empty.");
        }
        
        if (teacherRepo.existsById(teacher.getId())) {
            throw new TeacherAlreadyExistsException(
                    "A teacher with the ID " + teacher.getId() + " already exists.");
        }

        return teacherRepo
                .save(teacher.toTeacher())
                .toTeacherDTO();
    }
    
    public List<TeacherDTO> fetchAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        return teachers.stream()
                .map(Teacher::toTeacherDTO)
                .toList();
    }
    
    public TeacherDTO updateTeacher(TeacherDTO teacher) {
        if (!teacherRepo.existsById(teacher.getId())) {
            throw new TeacherNotFoundException(teacher.getId());
        } else {
            Teacher existingTeacher = teacherRepo.findById(teacher.getId())
                    .orElseThrow(() -> new TeacherNotFoundException(teacher.getId()));
            
            existingTeacher.setEmail(teacher.getEmail());
            existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
            existingTeacher.setTName(teacher.getTName());
            existingTeacher.setSubject(teacher.getSubject());
            existingTeacher.setSalary(teacher.getSalary());
            // Add other fields as necessary
            
            return teacherRepo.save(existingTeacher).toTeacherDTO();
        }
    }
    
    public TeacherDTO fetchTeacherByTId(String tId) {
        return teacherRepo
                .findById(tId)
                .map(Teacher::toTeacherDTO)
                .orElseThrow(() -> new TeacherNotFoundException(tId));
    }
    
    public void deleteTeacherByTId(String tId) {
        if (!teacherRepo.existsById(tId))
            throw new TeacherNotFoundException(tId);
        
        teacherRepo.deleteById(tId);
    }
}
