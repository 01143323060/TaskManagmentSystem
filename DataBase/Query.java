package DataBase;
import AdminModule.*;
import java.time.LocalTime;
import java.util.*;
public class Query {
    ////////////////Admin Module////////////////
    //////////////////start Users functions//////////////////////
    public static void addUser(Users u) {
        ArrayLists.users.add(u);
    }

    public static void updateUser(long id, String name, String pass, String role) {
        for (Users u : ArrayLists.users) {
            if (u.getID() == id) {
                u.setName(name);
                u.setPassword(pass);
                u.setRole(role);
            }
        }
    }

    public static void deleteUser(long id) {
        for (Users u : ArrayLists.users) {
            if (u.getID() == id) {
                ArrayLists.users.remove(u);
                break;
            }
        }
    }
    //////////////////end Users functions//////////////////////
    //////////////////start Employee functions/////////////////

    public static void addEmployee(Employee e) 
    {
        ArrayLists.employee.add(e);
    }

    public static void updateEmployee(long id, String name, long typeid) {
    for (Employee e : ArrayLists.employee) {
        if (e.getID() == id) {
            e.setName(name);
            e.setType(typeid);
            e.setTypeID(typeid);
            break;
        }   
    }
    }

    public static void deleteEmployee(long id) {
        for (Employee e : ArrayLists.employee) {
            if (e.getID() == id) {
                ArrayLists.employee.remove(e);
                break;
            }
        }
    }
    public static String getEmployeeNameById(long id) {
        for (Employee e : ArrayLists.employee) {
            if (e.getID() == id) {
                return e.getName();
            }
        }
        return null;
    }
//     //////////////////end Employee functions/////////////////
//     /////////////////start Projects functions///////////////
    public static void addProject(Projects p) {
        ArrayLists.projects.add(p);
    }

    public static void updateProject(long id, String title, long phaseid) {
        for (Projects p : ArrayLists.projects) {
            if (p.getID() == id) {
                p.setTitle(title);
                p.setPhaseID(phaseid);
                p.setPhase(phaseid);
                break;
            }
        }
    }
    public static void deleteProject(long id) {
        for (Projects p : ArrayLists.projects) {
            if (p.getID() == id) {
                ArrayLists.projects.remove(p);
                break;
            }
        }
    }
    public static String getProjectTitleById(long id) {
        for (Projects p : ArrayLists.projects) {
            if (p.getID() == id) {
                return p.getTitle();
            }
        }
        return null;
    }
//     /////////////////end Projects functions///////////////
//     /////////////////start TaskPhases functions///////////////
    public static void addTaskPhase(TaskPhases tp) {
        ArrayLists.taskphases.add(tp);
    }

    public static void updateTaskPhase(long id, String phase) {
        for (TaskPhases tp : ArrayLists.taskphases) {
            if (tp.getid() == id) {
                tp.setPhase(phase);
            }
        }
    }

    public static void deleteTaskPhase(long id) {
        for (TaskPhases tp : ArrayLists.taskphases) {
            if (tp.getID() == id) {
                ArrayLists.taskphases.remove(tp);
                break;
            }
        }
    }
    public static String getPhaseNameById(long id) {
        for (TaskPhases tp : ArrayLists.taskphases) {
            if (tp.getid() == id) {
                return tp.getphase();
            }
        }
        return null;
    }
//     /////////////////end TaskPhases functions///////////////
//     /////////////////start EmployeeType functions///////////  
    public static void addEmployeeType(EmployeeType et) {
        ArrayLists.employeetype.add(et);
    }
    public static void updateEmployeeType(long id, String name) {
        for (EmployeeType et : ArrayLists.employeetype) {
            if (et.getId() == id) {
                et.setName(name);
            }
        }
    }

    public static String getTypeNameById(long id) {
        for (EmployeeType et : ArrayLists.employeetype) {
            if (et.getId() == id) {
                return et.getname();
            }
        }
        return null;
    }
    public static void deleteEmployeeType(long id) {
        for (EmployeeType et : ArrayLists.employeetype) {
            if (et.getID() == id) {
                ArrayLists.employeetype.remove(et);
                break;
            }
        }
    }
    /////////////////end Admin Module///////////
    private final String pathTimeCards = "../Resources/timecards.txt";
    private final String pathRequest = "../Resources/request.txt";
    private static int nextIdRequest = 1;
    private static int nextIdTimeCard = 1;
    private List<String> TimeCards = new ArrayList<>();
    private List<String> Request = new ArrayList<>();

    public void InsertRequest(int employeeId, String requestType, String reason, LocalTime startTime) {
        Request = FileManager.readFile(pathRequest);
        if (!Request.isEmpty()) {
            String lastLine = Request.get(Request.size() - 1);
            String[] parts = lastLine.split("\\|");
            nextIdRequest = Integer.parseInt(parts[0]) + 1;
        }
        String rowRequest = nextIdRequest + "|" + employeeId + "|" + requestType + "|" + reason + "|" + startTime + "|" + "Under Consideration";
        Request.add(rowRequest);
        FileManager.writeFile(pathRequest, Request);
        nextIdRequest++;
    }

    public void UpdateRequest(int id, int employeeId, String requestType, String reason, LocalTime startTime, String status) {
        Request = FileManager.readFile(pathRequest);
        if (id > 0 && id <= Request.size()) {
            String getRow = Request.get(id - 1);
            String[] checksSplit = getRow.split("\\|");
            if (checksSplit[1].equals(Integer.toString(employeeId))) {
                String update = id + "|" + employeeId + "|" + requestType + "|" + reason + "|" + startTime + "|" + status;
                Request.set(id - 1, update);
                FileManager.writeFile(pathRequest, Request);
                return;
            } else {
                System.out.println("Employee ID doesn't match.");
            }
        } else {
            System.out.println("That Request Id doesn't exist.");
        }
    }

    public void ApprovedRequest(int id, int employeeId, int approvedType) {
        Request = FileManager.readFile(pathRequest);
        if (id > 0 && id <= Request.size()) {
            String getRow = Request.get(id - 1);
            String[] checksSplit = getRow.split("\\|");
            if (checksSplit[1].equals(Integer.toString(employeeId))) {
                String status = (approvedType == 1) ? "Approved" : "Disapproved";
                String phase = (approvedType == 1) ? "Pending" : "Canceled";
                String update = id + "|" + checksSplit[1] + "|" + checksSplit[2] + "|" + checksSplit[3] + "|" + checksSplit[4] + "|" + phase + "|" + status;
                Request.set(id - 1, update);
                FileManager.writeFile(pathRequest, Request);
            } else {
                System.out.println("Employee ID doesn't match.");
            }
        } else {
            System.out.println("The Request doesn't exist.");
        }
    }

    public List<String> ShowAllRequests() {
        Request = FileManager.readFile(pathRequest);
        if (Request.isEmpty()) {
            List<String> result = new ArrayList<>();
            result.add("There's no Request.");
            return result;
        }
        return new ArrayList<>(Request);
    }

    public String GetIdRequest(int id) {
        Request = FileManager.readFile(pathRequest);
        for (String temp : Request) {
            String[] parts = temp.split("\\|");
            if (Integer.parseInt(parts[0]) == id) {
                return temp;
            }
        }
        return "ID Request Doesn't exist";
    }

    public List<String> ShowAllRequests(int type) {
        List<String> result = new ArrayList<>();
        if (type < 1 || type > 3) {
            result.add("Wrong Type.");
            return result;
        }
        Request = FileManager.readFile(pathRequest);
        String[] types = {"Vacation", "Mission", "Permission"};
        String targetType = types[type - 1];
        for (String temp : Request) {
            String[] parts = temp.split("\\|");
            if (parts[2].equals(targetType)) {
                result.add(temp);
            }
        }
        return result;
    }

    public void DeleteAllRequests() {
        FileManager.writeFile(pathRequest, new ArrayList<>());
    }

    public void TimeCard(int employeeId, LocalTime time) {
        TimeCards = FileManager.readFile(pathTimeCards);
        String lastState = null;
        for (String line : TimeCards) {
            String[] parts = line.split("\\|");
            if (Integer.parseInt(parts[1]) == employeeId) {
                lastState = parts[2];
            }
        }
        String state = (lastState == null || "Departure".equals(lastState)) ? "Attendance" : "Departure";
        int id = (TimeCards.isEmpty()) ? 1 : Integer.parseInt(TimeCards.get(TimeCards.size() - 1).split("\\|")[0]) + 1;
        String newRow = id + "|" + employeeId + "|" + state + "|" + time;
        TimeCards.add(newRow);
        FileManager.writeFile(pathTimeCards, TimeCards);
    }

    public String GetIdTimeCards(int id) {
        TimeCards = FileManager.readFile(pathTimeCards);
        for (String temp : TimeCards) {
            String[] parts = temp.split("\\|");
            if (Integer.parseInt(parts[0]) == id) {
                return temp;
            }
        }
        return "Time Card ID Doesn't Exist";
    }

    public String GetEmployeeIdTimeCards(int employeeId) {
        TimeCards = FileManager.readFile(pathTimeCards);
        for (String temp : TimeCards) {
            String[] parts = temp.split("\\|");
            if (Integer.parseInt(parts[1]) == employeeId) {
                return temp;
            }
        }
        return "Time Card ID Doesn't Exist";
    }

    public List<String> ShowAllTimeCards() {
        TimeCards = FileManager.readFile(pathTimeCards);
        if (TimeCards.isEmpty()) {
            List<String> result = new ArrayList<>();
            result.add("There's no Time Cards.");
            return result;
        }
        return new ArrayList<>(TimeCards);
    }

    public void DeleteAllTimeCards() {
        FileManager.writeFile(pathTimeCards, new ArrayList<>());
    }
}