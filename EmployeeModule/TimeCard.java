package EmployeeModule;

import DataBase.FileManager;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeCard {
    private static int next = 1;
    private int cardId;
    private String employeeId;
    private LocalDate date;
    private LocalTime arrivalTime;     // We want this to be NOW by default
    private LocalTime departureTime;

    // 1. No-argument constructor → sets arrival time to NOW automatically
    public TimeCard(String employeeId) {
        cardId = next++;
        this.employeeId = employeeId;
        this.date = LocalDate.now();           // Today
        this.arrivalTime = LocalTime.now();    // ← RIGHT NOW!
        this.departureTime = null;             // Not yet departed
    }

    // 2. Full constructor (used when loading from file)
    public TimeCard(int cardId, String employeeId, LocalDate date, LocalTime arrivalTime, LocalTime departureTime) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.date = date;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        if (cardId >= next) {
            next = cardId + 1;
        }
    }

    // 3. Constructor for recording departure only
    public TimeCard(String employeeId, LocalTime departureTime) {
        this(employeeId);               // Reuse no-arg to set date & arrival
        this.departureTime = departureTime;
    }

    // Getters and Setters (added setters for completeness)
    public int getCardId() { return cardId; }
    public String getEmployeeId() { return employeeId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }

    // toString for display
    @Override
    public String toString() {
        return "Timecard [ID=" + cardId + ", Employee=" + employeeId + 
               ", Date=" + date + 
               ", Arrival=" + arrivalTime + 
               ", Departure=" + (departureTime != null ? departureTime : "Not yet") + "]";
    }
    
    // Save to file using FileManager (for file-based storage, load on start)
    public void saveToFile() {
        String path = "../Resources/timecards.txt";
        List<String> timecards = FileManager.readFile(path);
        timecards.add(this.toFileString());
        FileManager.writeFile(path, timecards);
    }
    
    // Load all timecards from file (static for system start)
    public static List<TimeCard> loadFromFile() {
        String path = "../Resources/timecards.txt";
        List<String> lines = FileManager.readFile(path);
        List<TimeCard> timecards = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\|");
            int id = Integer.parseInt(parts[0]);
            String empId = parts[1];
            String state = parts[2];
            LocalTime time = LocalTime.parse(parts[3]);
            // Assuming format id|employeeId|state|time, create based on state
            LocalDate date = LocalDate.now(); // Assume current if not stored; adjust if file has date
            if ("Attendance".equals(state)) {
                timecards.add(new TimeCard(id, empId, date, time, null));
            } else {
                timecards.add(new TimeCard(id, empId, date, null, time));
            }
        }
        return timecards;
    }
    
    // To string for file
    public String toFileString() {
        String state = (departureTime == null) ? "Attendance" : "Departure";
        LocalTime time = (departureTime == null) ? arrivalTime : departureTime;
        return cardId + "|" + employeeId + "|" + state + "|" + time;
    }
}