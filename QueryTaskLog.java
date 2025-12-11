package DataBase;
import java.util.*;
import TaskModule.TaskLog;
import TaskModule.TaskPage;
import AdminModule.Employee;
public class QueryTaskLog {
    private static ArrayList<TaskLog> logs = new ArrayList<>();
    private static final String FILE_PATH = "DataBase/TaskLogs.txt";
    private static void loadLogsIfEmpty(){
        if (logs.isEmpty()) {
            logs = TaskLog.loadLogs(FILE_PATH);
        }
    }
    private static void save() {
        TaskLog.saveLogs(FILE_PATH, logs);
    }
    private static boolean isLogValid(TaskLog l) {
        return l.getActualTime()!=null&&
                l.getAssignedEmployee()!=null&&
                l.getFromTime()!=null&&
                l.getToTime()!=null&&
                l.getTaskLogId()!=0&&
                l.getTask()!=null;
    }
    public static String addLog(TaskLog log) {
        loadLogsIfEmpty();
        if(!isLogValid(log)){
            return "Task Log details are incomplete";
        }
        if(getLogById(log.getTaskLogId())!=null){
            return "Task Log ID already exists";
        }
        logs.add(log);
        save();
        return "Log added successfully";
    }
    public static void removeLog(TaskLog log) {
        loadLogsIfEmpty();
        logs.remove(log);
        save();
    }
    public static TaskLog removeLogById(int logId) {
        loadLogsIfEmpty();
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getTaskLogId() == logId) {
                TaskLog removed = logs.remove(i);
                save();
                return removed;
            }
        }
        return null;
    }
    public static TaskLog getLogById(int logId) {
        loadLogsIfEmpty();
        for (TaskLog log : logs) {
            if (log.getTaskLogId() == logId) {
                return log;
            }
        }
        return null;
    }
    public static ArrayList<TaskLog> getAllLogs() {
        loadLogsIfEmpty();
        return new ArrayList<>(logs);
    }
    public static ArrayList<TaskLog> getLogsByEmployee(Employee emp) {
        loadLogsIfEmpty();
        ArrayList<TaskLog> result = new ArrayList<>();
        for (TaskLog log : logs) {
            if (log.getAssignedEmployee().equals(emp)) {
                result.add(log);
            }
        }
        return result;
    }
    public static ArrayList<TaskLog> getLogsByTask(TaskPage task) {
        loadLogsIfEmpty();
        ArrayList<TaskLog> result = new ArrayList<>();
        if (task == null) return result;
        for (TaskLog log : logs) {
            if (log!= null &&log.getTask().getTaskId()==task.getTaskId()) {
                result.add(log);
            }
        }
        return result;
    }
    public static void showAllLogs() {
        loadLogsIfEmpty();
        for (TaskLog log : logs) {
            System.out.println(log);
        }
    }
    public static int size() {
        loadLogsIfEmpty();
        return logs.size();
    }
    public static boolean isEmpty() {
        loadLogsIfEmpty();
        return logs.isEmpty();
    }
    public static String changeFromTime(int taskLogId,Date newFromTime){
        loadLogsIfEmpty();
        TaskLog log=getLogById(taskLogId);
        if( log!=null){
         log.setFromTime(newFromTime);
         save();
         return "From time updated";
       }
       return "Task not found"; 
        
    }
    public static String changeToTime(int taskLogId,Date newToTime){
        loadLogsIfEmpty();
        TaskLog log=getLogById(taskLogId);
        if( log!=null){
         log.setToTime(newToTime);
         save();
         return "To time updated";
       }
       return "Task not found"; 
        
    }
    public static String changeTask(int taskLogId,TaskPage newTask){
        loadLogsIfEmpty();
        TaskLog log=getLogById(taskLogId);
        if( log!=null){
         log.setTask(newTask);
         save();
         return "Task updated";
       }
       return "Task not found"; 
        
    }
    public static String changeTaskLogId(int taskLogId, int newTaskLogId){
        loadLogsIfEmpty();
       TaskLog log=getLogById(taskLogId);
       if( log!=null&&getLogById(newTaskLogId)==null){
         log.setTaskLogID(newTaskLogId);
         save();
         return "Task ID updated";
       }
       else if(getLogById(newTaskLogId)!=null)return "New Id already exists";
       else return "Task not found";
   }
    
    public static String changeTaskAssignedEmp(int taskLogId,Employee newAssignedEmp){
        loadLogsIfEmpty();
       TaskLog log=getLogById(taskLogId);
       if( log!=null){
         log.setAssignedEmployee(newAssignedEmp);
         save();
         return "Assigned employee updated"; 
       }
       return "Task not found"; 
   }
    public static Date getFromTime(int taskLogId){
        loadLogsIfEmpty();
        TaskLog t=getLogById(taskLogId);
        return t!=null?t.getFromTime():null;
    }
    public static Date getToTime(int taskLogId){
        loadLogsIfEmpty();
        TaskLog t=getLogById(taskLogId);
        return t!=null?t.getToTime():null;
    }
    public static Employee getAssignedEmp(int taskLogId){
        loadLogsIfEmpty();
        TaskLog t=getLogById(taskLogId);
        return t!=null?t.getAssignedEmployee():null;
    }
    public static TaskPage getTask(int taskLogId){
        loadLogsIfEmpty();
        TaskLog t=getLogById(taskLogId);
        return t!=null?t.getTask():null;
    }
}
