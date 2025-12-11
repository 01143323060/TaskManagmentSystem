
import AdminModule.TaskPhases;
import java.util.*;
import AdminModule.Projects;
import AdminModule.Employee;
import static DataBase.FileManager.*;
import DataBase.Query;
public class TaskPage {
   private int taskId;
    private int code;
    private String title;
    private String desc;
    private Employee assignedEmp;
    private TaskPhases taskPhase;
    private Projects project;
    private int priority;
    private Employee creatorName;
    private Date startDate;
    private Date endDate;
    private double estimationHours;
    private String evaluation="Not evaluated yet";
    TaskPage(){
        
    }
    TaskPage(int taskId,int code,String title,String desc,Employee assignedEmp,
            TaskPhases taskPhase,Projects project,int priority,
            Employee creatorName,Date startDate,Date endDate,double estimationHours){
        this.taskId = taskId;
        this.code = code;
        this.title = title;
        this.desc = desc;
        this.assignedEmp = assignedEmp;
        this.creatorName = creatorName;
        this.taskPhase = taskPhase;
        this.project = project;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimationHours = estimationHours;
    }
    public int getTaskId(){
        return taskId;
    }
    public void setTaskId(int taskId){
        this.taskId=taskId;
    }
    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code=code;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getDescription(){
        return desc;
    }
    public void setDescription(String desc){
        this.desc=desc;
    }
    public Employee getAssignedEmp(){
        return assignedEmp;
    }    
    public void setAssignedEmp(Employee assignedEmp){
        this.assignedEmp=assignedEmp;
    }
    public TaskPhases getTaskPhase(){
        return taskPhase;
    }
    public void setTaskPhase(TaskPhases taskPhase){
        this.taskPhase=taskPhase;
    }
    public Projects getProject(){
        return project;
    }
    public void setProject(Projects project){
        this.project=project;
    }
    public int getPriority(){
        return priority;
    }
    public void setPriority(int priority){
        this.priority=priority;
    }
    public Employee getCreatorName(){
        return creatorName;
    }
    public void setCreatorName(Employee creatorName){
        this.creatorName=creatorName;
    }
    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate=startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public void setEndDate(Date endDate){
        this.endDate=endDate;
    }
    public double getEstimationHours(){
        return estimationHours;
    }
    public void setEstimationHours(double estimationHours){
        this.estimationHours=estimationHours;
    }
    public TaskPage updateTask(String title,String desc,Employee assignedEmp,
            TaskPhases taskPhase,Projects project,int priority,
            Employee creatorName,Date startDate,Date endDate,double estimationHours){
        this.title = title;
        this.desc = desc;
        this.assignedEmp = assignedEmp;
        this.creatorName = creatorName;
        this.taskPhase = taskPhase;
        this.project = project;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimationHours = estimationHours;
        return this;
    }
    public void reassign(Employee emp){
        this.assignedEmp = emp;
    }
    public void changePhase(TaskPhases taskPhase){
        this.taskPhase=taskPhase;
    }
    public String evaluate(String eval){
        evaluation=eval;
        return evaluation;
    }
    @Override
    public String toString(){
         return "TaskPage ID: " + taskId +"\nCode: " + code +"\nTitle: " + title +
               "\nDescription: " + desc +"\nAssigned To: " + assignedEmp.getName() +
               "\nPhase: " + getTaskPhase() +"\nProject: " + project.getTitle() +
               "\nPriority: " + priority +"\nCreator: " + creatorName.getName() +
               "\nStart: " + startDate +"\nEnd: " + endDate +
               "\nEstimated Hours: " + estimationHours +"\nEvaluation: " + evaluation;
    }
    public String toFileString(){
        return taskId+"|"+code+"|"+title+"|"+desc+"|"+assignedEmp.getID()+"|"+
               taskPhase.getid()+"|"+project.getID()+"|"+priority+"|"+
                creatorName.getID()+"|"+startDate.getTime()+"|"+endDate.getTime()+"|"+estimationHours+"|"+evaluation;
    }
    
    public static TaskPage fromFileString(String line) {
       String[] p = line.split("\\|");
       if (p.length != 13) {
          throw new IllegalArgumentException("Corrupted task file line: " + line);
       }
       TaskPage t = new TaskPage();
       t.taskId = Integer.parseInt(p[0]);
       t.code = Integer.parseInt(p[1]);
       t.title= p[2];
       t.desc = p[3];
       t.assignedEmp=new Employee();
       t.assignedEmp.setID(Long.parseLong(p[4]));
       t.assignedEmp.setName(Query.getEmployeeNameById(Long.parseLong(p[4])));
       t.taskPhase=new TaskPhases();
       t.taskPhase.setid(Long.parseLong(p[5]));
       t.taskPhase.setPhase(Query.getPhaseNameById(Long.parseLong(p[5])));
       t.project=new Projects();
       t.project.setID(Long.parseLong(p[6]));
       t.project.setTitle(Query.getProjectTitleById(Long.parseLong(p[6])));
       t.priority= Integer.parseInt(p[7]);
       t.creatorName=new Employee();
       t.creatorName.setID(Long.parseLong(p[8]));
       t.creatorName.setName(Query.getEmployeeNameById(Long.parseLong(p[8])));
       t.startDate = new Date(Long.parseLong(p[9]));
       t.endDate= new Date(Long.parseLong(p[10]));
       t.estimationHours = Double.parseDouble(p[11]);
       t.evaluation=p[12];
       return t;
    }
    public static void saveTasks(String path, ArrayList<TaskPage> tasks) {
          List<String> lines = new ArrayList<>();
          for (TaskPage t : tasks) lines.add(t.toFileString());
          writeFile(path, lines);
   }

   public static ArrayList<TaskPage> loadTasks(String path) {
         List<String> lines = readFile(path);
         ArrayList<TaskPage> tasks = new ArrayList<>();
         for (String line : lines) tasks.add(TaskPage.fromFileString(line));
         return tasks;
    }
    
}
