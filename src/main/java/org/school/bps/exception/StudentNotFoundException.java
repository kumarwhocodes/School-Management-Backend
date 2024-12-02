package org.school.bps.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String id) {
        super("Student with id : " + id + " is not found");
    }
}
