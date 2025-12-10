
package TaskModule;
import DataBase.QueryTask;
import java.util.Date;
import AdminModule.Projects;
import AdminModule.TaskPhases;
import AdminModule.Users;
import AdminModule.Employee;
public class Leader extends Users {
    QueryTask td;
    public Leader(String name, String password, String role,QueryTask query) {
        super(name,password , role);
        this.td=query;
    }
    public void createTask(TaskPage task){
        td.addTask(task);
    }
    public void showAllTasks(){
        td.showAllTasks();
    }
    public String evaluation(int taskId,String eval){
          TaskPage task=td.getTaskById(taskId);
          if(task!=null){
              return task.evaluate(eval);
          }
          return "Task not found";
    }
    
   public void reassignTask(int taskId,Employee newEmp){
       TaskPage task=td.getTaskById(taskId);
       if(task!=null){
           task.reassign(newEmp);
       }
       
   }
   public String changeTaskId(int taskId,int newTaskId){
       return td.changeTaskId(taskId, newTaskId);
   }
   public String changeTaskCode(int taskId, int newCode){
      return td.changeTaskCode(taskId, newCode);
   }
   public String changeTaskTitle(int taskId, String newTitle){
       return td.changeTaskTitle(taskId, newTitle);
   }
   public String changeTaskDesc(int taskId, String newDesc){
       return td.changeTaskDesc(taskId, newDesc);
   }
   public String changeTaskAssignedEmp(int taskId,Employee newAssignedEmp){
       return td.changeTaskAssignedEmp(taskId, newAssignedEmp);
   }
   public String changeTaskPhase(int taskId,TaskPhases newTaskPhase){
       return td.changeTaskPhase(taskId, newTaskPhase);
   }
   public String changeTaskProject(int taskId,Projects newProject){
       return td.changeTaskProject(taskId, newProject);
   }
   public String changeTaskPriority(int taskId,int newPriority){
       return td.changeTaskPriority(taskId, newPriority);
   }
   public String changeTaskCreatorName(int taskId,Employee newCreatorName){
       return td.changeTaskCreatorName(taskId, newCreatorName);
   }
   public String changeTaskStartDate(int taskId,Date newStartDate){
       return td.changeTaskStartDate(taskId, newStartDate);
   }
   public String changeTaskEndDate(int taskId,Date newEndDate){
       return td.changeTaskEndDate(taskId, newEndDate);
   }
   public String changeTaskEstimationHours(int taskId,double newEstimationHours){
       return td.changeTaskEstimationHours(taskId, newEstimationHours);
   }
}