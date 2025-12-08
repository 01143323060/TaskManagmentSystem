package EmployeeModule;

import java.time.LocalTime;

public class LeaveRequest extends Request {
    private LeaveType type;
    public LeaveRequest(String employeeId, String reason, LeaveType type){
        super(employeeId, reason);
        this.type = type;
    }
    
    // Full constructor for loading
    public LeaveRequest(int requestId, String employeeId, LocalTime startDate, LocalTime endDate, String reason, String status, LeaveType type) {
        super(requestId, employeeId, startDate, endDate, reason, status);
        this.type = type;
    }
    
    public LeaveType getType(){
        return type;
    }
    
    public void setType(LeaveType type) {
        this.type = type;
    }
    
    @Override
    public String toFileString(){
        return super.requestId + "|" + super.getEmployeeId() + "|" + "Vacation" + "|" + super.getReason() + "|" + super.getStartDate() + "|" + super.getStatus() + (super.getEndDate() != null ? "|" + super.getEndDate() : "") + "|" + type.getName();
    }
    
    @Override
    public String toString() {
        return "LeaveRequest [Type=" + type.getName() + ", " + super.toString() + "]";
    }
    
    // LeaveType inner class (for recording types of vacations as per project)
    public static class LeaveType {
        private String name;
        public LeaveType (String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}