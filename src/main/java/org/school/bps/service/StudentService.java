package org.school.bps.service;

import lombok.RequiredArgsConstructor;
import org.school.bps.dto.StudentDTO;
import org.school.bps.entity.Info;
import org.school.bps.entity.Student;
import org.school.bps.exception.StudentAlreadyExistsException;
import org.school.bps.exception.StudentNotFoundException;
import org.school.bps.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository studentRepo;
    
    public StudentDTO createStudent(StudentDTO student) {
        //TODO: same username ke saath doosra user create hoja rha hai
        if (studentRepo.existsById(student.getStuId()))
            throw new StudentAlreadyExistsException(student.getStuId());
        else {
            return studentRepo
                    .save(student.toStudent())
                    .toStudentDTO();
        }
    }
    
    public List<StudentDTO> fetchAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream()
                .map(Student::toStudentDTO)
                .toList();
    }
    
    public StudentDTO updateStudent(StudentDTO student) {
        if (!studentRepo.existsById(student.getStuId())) {
            throw new StudentNotFoundException(student.getStuId());
        } else {
            Student existingStudent = studentRepo.findById(student.getStuId())
                    .orElseThrow(() -> new StudentNotFoundException(student.getStuId()));
            
            existingStudent.setEmail(student.getEmail());
            existingStudent.setPhoneNumber(student.getPhoneNumber());
            existingStudent.setStuName(student.getStuName());
            // Add other fields as necessary
            
            return studentRepo.save(existingStudent).toStudentDTO();
        }
    }
    
    public StudentDTO fetchStudentByStuId(String stuId) {
        return studentRepo
                .findById(stuId)
                .map(Student::toStudentDTO)
                .orElseThrow(() -> new StudentNotFoundException(stuId));
    }
    
    public void deleteStudentByStuId(String stuId) {
        if (!studentRepo.existsById(stuId))
            throw new StudentNotFoundException(stuId);
        
        studentRepo.deleteById(stuId);
    }
    
    public List<StudentDTO> fetchStudentsByClass(String className) {
        List<Student> students = studentRepo.findByClassName(className);
        return students.stream()
                .map(Student::toStudentDTO)
                .toList();
    }
}
