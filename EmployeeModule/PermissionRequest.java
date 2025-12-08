package EmployeeModule;

import java.time.LocalDate;
import java.time.LocalTime;

public class PermissionRequest extends Request {
    private LocalDate permissionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public PermissionRequest(String employeeId, String reason, LocalDate permissionDate, LocalTime startTime, LocalTime endTime) {
        super(employeeId, reason);
        this.permissionDate = permissionDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    // Full constructor for loading
    public PermissionRequest(int requestId, String employeeId, LocalTime startDate, LocalTime endDate, String reason, String status, LocalDate permissionDate, LocalTime startTime, LocalTime endTime) {
        super(requestId, employeeId, startDate, endDate, reason, status);
        this.permissionDate = permissionDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public LocalDate getPermissionDate() { return permissionDate; }
    public void setPermissionDate(LocalDate permissionDate) { this.permissionDate = permissionDate; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    
    @Override
    public String toFileString() {
        return super.requestId + "|" + super.getEmployeeId() + "|" + "Permission" + "|" + super.getReason() + "|" + super.getStartDate() + "|" + super.getStatus() + (super.getEndDate() != null ? "|" + super.getEndDate() : "") + "|" + permissionDate + "|" + startTime + "|" + endTime;
    }
    
    @Override
    public String toString() {
        return "PermissionRequest [Date=" + permissionDate + ", Start=" + startTime + ", End=" + endTime + ", " + super.toString() + "]";
    }
}
