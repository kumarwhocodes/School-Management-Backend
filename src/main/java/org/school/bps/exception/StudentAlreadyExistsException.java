package org.school.bps.exception;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String id) {
        super("User with id : " + id + " already exists!");
    }
}
