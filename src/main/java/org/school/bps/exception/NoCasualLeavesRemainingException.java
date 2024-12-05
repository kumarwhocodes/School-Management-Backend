package org.school.bps.exception;

public class NoCasualLeavesRemainingException extends RuntimeException {
    public NoCasualLeavesRemainingException(String message) {
        super(message);
    }
}