package org.school.bps.exception;

public class TeacherAlreadyExistsException extends RuntimeException {
    public TeacherAlreadyExistsException(String id) {
        super("Teacher with id : " + id + " already exists!");
    }
}
