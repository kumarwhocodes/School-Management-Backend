package org.school.bps.exception;

public class AnnouncementNotFoundException extends RuntimeException {
    public AnnouncementNotFoundException(int id) {
        super("Announcement with id : " + id + " is not found");
    }
}
