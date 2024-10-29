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
        //TODO: same username ke saath doosra user create hoja rha hai
        if (teacherRepo.existsById(teacher.getTId()))
            throw new TeacherAlreadyExistsException(teacher.getTId());
        else {
            return teacherRepo
                    .save(teacher.toTeacher())
                    .toTeacherDTO();
        }
    }
    
    public List<TeacherDTO> fetchAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        return teachers.stream()
                .map(Teacher::toTeacherDTO)
                .toList();
    }
    
    public TeacherDTO updateTeacher(TeacherDTO teacher) {
        if (!teacherRepo.existsById(teacher.getTId())) {
            throw new TeacherNotFoundException(teacher.getTId());
        } else {
            Teacher existingTeacher = teacherRepo.findById(teacher.getTId())
                    .orElseThrow(() -> new TeacherNotFoundException(teacher.getTId()));
            
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
