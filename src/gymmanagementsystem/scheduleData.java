package gymmanagementsystem;

import java.sql.Time;

public class scheduleData {
    private Integer id;
    private String scheduleId;
    private String className;
    private String coachId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;
    private Integer maxCapacity;
    private Integer currentEnrollment;
    private String status;
    
    // Constructor
    public scheduleData(Integer id, String scheduleId, String className, String coachId,
                       String dayOfWeek, Time startTime, Time endTime, 
                       Integer maxCapacity, Integer currentEnrollment, String status) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.className = className;
        this.coachId = coachId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = currentEnrollment;
        this.status = status;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getScheduleId() {
        return scheduleId;
    }
    
    public String getClassName() {
        return className;
    }
    
    public String getCoachId() {
        return coachId;
    }
    
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    
    public Time getStartTime() {
        return startTime;
    }
    
    public Time getEndTime() {
        return endTime;
    }
    
    public Integer getMaxCapacity() {
        return maxCapacity;
    }
    
    public Integer getCurrentEnrollment() {
        return currentEnrollment;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }
    
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public void setCurrentEnrollment(Integer currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Check if class is full
    public boolean isFull() {
        return currentEnrollment >= maxCapacity;
    }
    
    // Get available slots
    public int getAvailableSlots() {
        return maxCapacity - currentEnrollment;
    }
    
    // Get time range as formatted string
    public String getTimeRange() {
        return startTime.toString() + " - " + endTime.toString();
    }
    
    @Override
    public String toString() {
        return "Schedule ID: " + scheduleId + ", Class: " + className + ", Day: " + dayOfWeek;
    }
} 