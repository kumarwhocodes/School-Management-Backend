package org.school.bps.constants;

public class Endpoints {
    
    //Student
    public final static String STUDENT = "/student";
    public final static String STUDENT_GREETING = "/";
    public final static String FETCH_ALL_STUDENTS = "/fetch-all";
    public final static String FETCH_STUDENT_BY_ID = "/{stuId}";
    public final static String CREATE_STUDENT = "/create";
    public final static String UPDATE_STUDENT = "/update";
    public final static String DELETE_STUDENT_BY_ID = "/{stuId}";
    public final static String FILTER_BY_CLASS = "/filter-by-class/{className}";
    public static final String MARK_STUDENT_ATTENDANCE = "/attendance";
    
    //Teacher
    public final static String TEACHER = "/teacher";
    public final static String TEACHER_GREETING = "/";
    public final static String FETCH_ALL_TEACHERS = "/fetch-all";
    public final static String FETCH_TEACHER_BY_ID = "/{tId}";
    public final static String CREATE_TEACHER = "/create";
    public final static String UPDATE_TEACHER = "/update";
    public static final String MARK_TEACHER_ATTENDANCE = "/attendance";
    public final static String DELETE_TEACHER_BY_ID = "/{tId}";
    public final static String FIND_MONTHLY_ATTENDANCE = "/monthly";
    
    //Announcement
    public final static String ANNOUNCEMENT = "/announcement";
    public final static String ANNOUNCEMENT_GREETING = "/";
    public final static String FETCH_ALL_ANNOUNCEMENTS = "/fetch-all";
    public final static String CREATE_ANNOUNCEMENT = "/create";
    public final static String UPDATE_ANNOUNCEMENT = "/update";
    public final static String DELETE_ANNOUNCEMENT_BY_ID = "/{id}";
    public final static String FETCH_ANNOUNCEMENT_BY_ID = "/{id}";
    
    //Leave
    public final static String LEAVE = "/leave";
    public final static String APPLY_LEAVE = "/apply";
    public final static String APPROVE_LEAVE = "/{leaveId}/approve";
    public final static String REJECT_LEAVE = "/{leaveId}/reject";
    public final static String FETCH_LEAVE_BY_TEACHER_ID = "/teacher/{teacherId}";
    public final static String FETCH_ALL_LEAVES = "/fetch-all";
    
    //About School
    public final static String INFO = "/info";
    public final static String INFO_GREETING = "/";
    public final static String FETCH_INFO = "/fetch-all";
    public final static String UPDATE_INFO = "/update";
}
