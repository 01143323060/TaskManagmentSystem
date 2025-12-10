
package TaskModule;
import DataBase.QueryTask;
import java.util.Date;
import AdminModule.Projects;
import AdminModule.TaskPhases;
import AdminModule.Users;
import AdminModule.Employee;
public class Leader extends Users {
    QueryTask td;
    public Leader(String name, String password, String role) {
        super(name,password , role);
    }
    public void createTask(TaskPage task){
        QueryTask.addTask(task);
    }
    public void showAllTasks(){
        QueryTask.showAllTasks();
    }
    public String evaluation(int taskId,String eval){
          TaskPage task=QueryTask.getTaskById(taskId);
          if(task!=null){
              return task.evaluate(eval);
          }
          return "Task not found";
    }
    
   public void reassignTask(int taskId,Employee newEmp){
       TaskPage task=QueryTask.getTaskById(taskId);
       if(task!=null){
           task.reassign(newEmp);
       }
       
   }
   public String changeTaskId(int taskId,int newTaskId){
       return QueryTask.changeTaskId(taskId, newTaskId);
   }
   public String changeTaskCode(int taskId, int newCode){
      return QueryTask.changeTaskCode(taskId, newCode);
   }
   public String changeTaskTitle(int taskId, String newTitle){
       return QueryTask.changeTaskTitle(taskId, newTitle);
   }
   public String changeTaskDesc(int taskId, String newDesc){
       return QueryTask.changeTaskDesc(taskId, newDesc);
   }
   public String changeTaskAssignedEmp(int taskId,Employee newAssignedEmp){
       return QueryTask.changeTaskAssignedEmp(taskId, newAssignedEmp);
   }
   public String changeTaskPhase(int taskId,TaskPhases newTaskPhase){
       return QueryTask.changeTaskPhase(taskId, newTaskPhase);
   }
   public String changeTaskProject(int taskId,Projects newProject){
       return QueryTask.changeTaskProject(taskId, newProject);
   }
   public String changeTaskPriority(int taskId,int newPriority){
       return QueryTask.changeTaskPriority(taskId, newPriority);
   }
   public String changeTaskCreatorName(int taskId,Employee newCreatorName){
       return QueryTask.changeTaskCreatorName(taskId, newCreatorName);
   }
   public String changeTaskStartDate(int taskId,Date newStartDate){
       return QueryTask.changeTaskStartDate(taskId, newStartDate);
   }
   public String changeTaskEndDate(int taskId,Date newEndDate){
       return QueryTask.changeTaskEndDate(taskId, newEndDate);
   }
   public String changeTaskEstimationHours(int taskId,double newEstimationHours){
       return QueryTask.changeTaskEstimationHours(taskId, newEstimationHours);
   }
}