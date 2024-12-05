package org.school.bps.exception;

public class LeaveOverlapException extends RuntimeException {
    public LeaveOverlapException(String message) {
        super(message);
    }
}