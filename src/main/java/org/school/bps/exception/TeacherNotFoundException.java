package org.school.bps.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String id) {
        super("Teacher with id : " + id + " is not found");
    }
}
