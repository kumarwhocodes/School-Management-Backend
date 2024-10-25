package org.school.bps.exception;

public class AnnouncementAlreadyExistsException extends RuntimeException {
    public AnnouncementAlreadyExistsException(int id) {
        super("Announcement with id : " + id + " already exists!");
    }
}
