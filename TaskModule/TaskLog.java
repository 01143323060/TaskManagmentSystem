

import java.util.*;
import AdminModule.Employee;
import static DataBase.FileManager.*;
import DataBase.Query;
import DataBase.QueryTask;
public class TaskLog {
    private Date fromTime;
    private Date toTime;
    private Employee assignedEmployee;
    private TaskPage task;
    private int taskLogId;
    public TaskLog(){
    }
    public TaskLog(Date fromTime,Date toTime,Employee assignedEmployee,TaskPage task,int taskLogId){
        this.fromTime=fromTime;
        this.toTime=toTime;
        this.assignedEmployee=assignedEmployee;
        this.task=task;
        this.taskLogId=taskLogId;
    }
    public Date getFromTime(){
        return fromTime;
    }
    public Date getToTime(){
        return toTime;
    }
    public int getTaskLogId(){
        return taskLogId;
    }
    public Employee getAssignedEmployee(){
        return assignedEmployee;
    }
    public TaskPage getTask(){
        return task;
    }
    public void setFromTime(Date fromTime){
        this.fromTime=fromTime;
    }
    public void setToTime(Date toTime){
        this.toTime=toTime;
    }
    public void setAssignedEmployee(Employee assignedEmployee){
        this.assignedEmployee=assignedEmployee;
    }
    public void setTask(TaskPage task){
        this.task=task;
    }
    public void setTaskLogID(int taskLogId){
        this.taskLogId=taskLogId;
    }
    public String getActualTime() {
        long diffMs = toTime.getTime() - fromTime.getTime();
        long totalMinutes = diffMs / (1000 * 60);
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return hours + " hours " + minutes + " minutes";
    }

    @Override
    public String toString(){
       return "From time: "+fromTime+" \n To time: "+toTime+" \n Actual time: "+getActualTime()+" \n Assigned employee: "+assignedEmployee+" \n Task: "+task+" \n Task log ID: "+taskLogId; 
    }
    public String toFileString(){
        return fromTime.getTime()+"|"+toTime.getTime()+"|"+assignedEmployee.getID()+"|"+task.getTaskId()+"|"+taskLogId;
   }
    public static TaskLog fromFileString(String line){
        String[]p=line.split("\\|");
        if (p.length != 5) {
          throw new IllegalArgumentException("Corrupted task file line: " + line);
       }
        TaskLog taskLog=new TaskLog();
        taskLog.fromTime=new Date(Long.parseLong(p[0]));
        taskLog.toTime=new Date(Long.parseLong(p[1]));
        taskLog.assignedEmployee=new Employee();
        taskLog.assignedEmployee.setID(Long.parseLong(p[2]));
        taskLog.assignedEmployee.setName(Query.getEmployeeNameById(Long.parseLong(p[2])));
        taskLog.task = QueryTask.getTaskById(Integer.parseInt(p[3]));
        taskLog.taskLogId=Integer.parseInt(p[4]);
        return taskLog;
    }
    public static ArrayList<TaskLog> loadLogs(String path) {
          List<String> lines = readFile(path);
          ArrayList<TaskLog> tasksLog = new ArrayList<>();
          for (String line : lines) tasksLog.add(TaskLog.fromFileString(line));
          return tasksLog;
    }
    public static void saveLogs(String path, List<TaskLog> tasksLog) {
          List<String> lines = new ArrayList<>();
          for (TaskLog t : tasksLog) lines.add(t.toFileString());
          writeFile(path, lines);
   }  
}
