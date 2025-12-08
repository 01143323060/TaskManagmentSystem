package EmployeeModule;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DataBase.FileManager;

public class Request {
    protected static int nextId = 1;
    protected int requestId;
    protected String employeeId;
    protected LocalTime startDate;
    protected LocalTime endDate;
    protected String reason;
    protected String status;

    public Request(String employeeId, String reason) {
        requestId = nextId++;
        this.employeeId = employeeId;
        this.reason = reason;
        this.status = "Pending";
        this.startDate = LocalTime.now();
        this.endDate = null;
    }

    public Request(int requestId, String employeeId, LocalTime startDate, LocalTime endDate, String reason, String status) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        if (requestId >= nextId) {
            nextId = requestId + 1;
        }
    }

    // Getters and Setters
    public int getRequestId() { return requestId; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public LocalTime getStartDate() { return startDate; }
    public void setStartDate(LocalTime startDate) { this.startDate = startDate; }
    public LocalTime getEndDate() { return endDate; }
    public void setEndDate(LocalTime endDate) { this.endDate = endDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void approve() {
        this.status = "Approved";
    }

    public void disapprove() {
        this.status = "Disapproved";
    }

    public void SaveToFile() {
        String path = "../Resources/request.txt";
        List<String> requests = FileManager.readFile(path);
        // Check if exists, update or add
        boolean found = false;
        for (int i = 0; i < requests.size(); i++) {
            if (Integer.parseInt(requests.get(i).split("\\|")[0]) == requestId) {
                requests.set(i, toFileString());
                found = true;
                break;
            }
        }
        if (!found) {
            requests.add(toFileString());
        }
        FileManager.writeFile(path, requests);
    }

    public static List<Request> loadFromFile() {
        String path = "../Resources/request.txt";
        List<String> lines = FileManager.readFile(path);
        List<Request> reqs = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length < 6) continue;
            int id = Integer.parseInt(parts[0]);
            String empId = parts[1];
            String type = parts[2];
            String reason = parts[3];
            LocalTime start = LocalTime.parse(parts[4]);
            String status = parts[5];
            LocalTime end = (parts.length > 6) ? LocalTime.parse(parts[6]) : null;
            // Based on type, create subclass
            if ("Vacation".equals(type)) {
                String leaveType = (parts.length > 7) ? parts[7] : "Unknown";
                reqs.add(new LeaveRequest(id, empId, start, end, reason, status, new LeaveRequest.LeaveType(leaveType)));
            } else if ("Mission".equals(type)) {
                LocalDate missionDate = (parts.length > 7) ? LocalDate.parse(parts[7]) : LocalDate.now();
                String desc = (parts.length > 8) ? parts[8] : "";
                reqs.add(new MissionRequest(id, empId, start, end, reason, status, missionDate, desc));
            } else if ("Permission".equals(type)) {
                LocalDate permDate = (parts.length > 7) ? LocalDate.parse(parts[7]) : LocalDate.now();
                LocalTime pStart = (parts.length > 8) ? LocalTime.parse(parts[8]) : LocalTime.now();
                LocalTime pEnd = (parts.length > 9) ? LocalTime.parse(parts[9]) : LocalTime.now();
                reqs.add(new PermissionRequest(id, empId, start, end, reason, status, permDate, pStart, pEnd));
            } else {
                reqs.add(new Request(id, empId, start, end, reason, status));
            }
        }
        return reqs;
    }

    public String toFileString() {
        return requestId + "|" + employeeId + "|" + "Request" + "|" + reason + "|" + startDate + "|" + status + (endDate != null ? "|" + endDate : "");
    }

    @Override
    public String toString() {
        return "Request [ID=" + requestId + ", Emp=" + employeeId + ", Start=" + startDate + ", End=" + (endDate != null ? endDate : "N/A") + ", Reason=" + reason + ", Status=" + status + "]";
    }
}