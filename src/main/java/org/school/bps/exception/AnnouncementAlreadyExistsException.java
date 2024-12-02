package org.school.bps.exception;

public class AnnouncementAlreadyExistsException extends RuntimeException {
    public AnnouncementAlreadyExistsException(String message) {
        super(message);
    }
}
