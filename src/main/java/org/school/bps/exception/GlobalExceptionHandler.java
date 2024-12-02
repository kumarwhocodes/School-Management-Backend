package org.school.bps.exception;

import org.school.bps.dto.CustomResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // InfoNotFoundException handler
    @ExceptionHandler(InfoNotFoundException.class)
    public ResponseEntity<CustomResponse<String>> handleInfoNotFoundException(InfoNotFoundException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.NOT_FOUND, ex.getMessage(), "ERROR"),
                HttpStatus.NOT_FOUND
        );
    }
    
    // AnnouncementNotFoundException handler
    @ExceptionHandler(AnnouncementNotFoundException.class)
    public ResponseEntity<CustomResponse<String>> handleAnnouncementNotFoundException(AnnouncementNotFoundException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.NOT_FOUND, ex.getMessage(), "ERROR"),
                HttpStatus.NOT_FOUND
        );
    }
    
    // AnnouncementAlreadyExistsException handler
    @ExceptionHandler(AnnouncementAlreadyExistsException.class)
    public ResponseEntity<CustomResponse<String>> handleAnnouncementAlreadyExistsException(AnnouncementAlreadyExistsException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage(), "ERROR"),
                HttpStatus.BAD_REQUEST
        );
    }
    
    // StudentNotFoundException handler
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<CustomResponse<String>> handleStudentNotFoundException(StudentNotFoundException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.NOT_FOUND, ex.getMessage(), "ERROR"),
                HttpStatus.NOT_FOUND
        );
    }
    
    // AttendanceMarkingException handler
    @ExceptionHandler(AttendanceMarkingException.class)
    public ResponseEntity<CustomResponse<String>> handleAttendanceMarkingException(AttendanceMarkingException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage(), "ERROR"),
                HttpStatus.BAD_REQUEST
        );
    }
    
    //TeacherNotFoundException Handler
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<CustomResponse<String>> handleTeacherNotFoundException(TeacherNotFoundException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.NOT_FOUND, ex.getMessage(), "ERROR"),
                HttpStatus.NOT_FOUND
        );
    }
    
    //TeacherAlreadyExistsException Handler
    @ExceptionHandler(TeacherAlreadyExistsException.class)
    public ResponseEntity<CustomResponse<String>> handleTeacherAlreadyExistsException(TeacherAlreadyExistsException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage(), "ERROR"),
                HttpStatus.BAD_REQUEST
        );
    }
    
    
    
    
    // IllegalStateException handler
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CustomResponse<String>> handleIllegalStateException(IllegalStateException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage(), "ERROR"),
                HttpStatus.BAD_REQUEST
        );
    }
    
    // Handle NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CustomResponse<String>> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Null pointer exception occurred", "ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    
    // Handle DataIntegrityViolationException for database issues
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomResponse<String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.CONFLICT, "Data integrity violation", "ERROR"),
                HttpStatus.CONFLICT
        );
    }
    
    // Generic Exception handler for unforeseen errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                new CustomResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong.", "ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
