package EmployeeModule;

import java.time.LocalDate;
import java.time.LocalTime;

public class MissionRequest extends Request {
    private LocalDate missionDate;
    private String description;
    
    public MissionRequest(String employeeId, LocalDate missionDate, String description, String reason) {
        super(employeeId, reason);
        this.missionDate = missionDate;
        this.description = description;
    }
    
    // Full constructor for loading
    public MissionRequest(int requestId, String employeeId, LocalTime startDate, LocalTime endDate, String reason, String status, LocalDate missionDate, String description) {
        super(requestId, employeeId, startDate, endDate, reason, status);
        this.missionDate = missionDate;
        this.description = description;
    }
    
    public LocalDate getMissionDate() { return missionDate; }
    public void setMissionDate(LocalDate missionDate) { this.missionDate = missionDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @Override
    public String toFileString() {
        return super.requestId + "|" + super.getEmployeeId() + "|" + "Mission" + "|" + super.getReason() + "|" + super.getStartDate() + "|" + super.getStatus() + (super.getEndDate() != null ? "|" + super.getEndDate() : "") + "|" + missionDate + "|" + description;
    }
    
    @Override
    public String toString() {
        return "MissionRequest [Date=" + missionDate + ", Description=" + description + ", " + super.toString() + "]";
    }
}
