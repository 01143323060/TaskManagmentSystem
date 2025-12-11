

import java.util.*;
import AdminModule.Projects;
import AdminModule.TaskPhases;
import TaskModule.TaskPage;
import AdminModule.Employee;
public class QueryTask {
    static ArrayList<TaskPage> tasks=new ArrayList<>();
    private static void save() {
        TaskPage.saveTasks("DataBase/Tasks.txt", tasks);
    }
    private static void loadTasksIfEmpty() {
       if (tasks.isEmpty()) {
           tasks = TaskPage.loadTasks("DataBase/Tasks.txt");
        }
    }
    private static boolean isTaskValid(TaskPage t) {
        return t.getTaskId() != 0 &&
           t.getCode() != 0 &&     
           t.getCreatorName() != null && 
           t.getEstimationHours() != 0 &&
           t.getPriority() != 0 && 
           t.getTitle() != null &&
           t.getDescription() != null &&
           t.getAssignedEmp() != null &&
           t.getTaskPhase() != null &&
           t.getProject() != null &&
           t.getStartDate() != null &&
           t.getEndDate() != null;
    }
    public static String addTask(TaskPage task){
        loadTasksIfEmpty();
         if(!isTaskValid(task)){
            return "Task details are incomplete";
        }
        if(getTaskById(task.getTaskId())!=null){
            return "Task ID already exists";
        }
        tasks.add(task);
        save();
        return "Task added successfully";
    }
    public static void removeTask(TaskPage task){
        loadTasksIfEmpty();
        if(!tasks.isEmpty()){
          tasks.remove(task); 
        }
        save();
    }
    public static TaskPage removeTaskByID(int taskId) {
      loadTasksIfEmpty();
      for (int i = 0; i < tasks.size(); i++) {
        if (tasks.get(i).getTaskId() == taskId) {
            TaskPage removed = tasks.remove(i);
            save();
            return removed;
        }
      }
      return null;
    }

    public static boolean isEmpty(){
        loadTasksIfEmpty();
        return tasks.isEmpty();
    }
    public static int size(){
        loadTasksIfEmpty();
        return tasks.size();
    }
    public static void showAllTasks(){
        loadTasksIfEmpty();
        for(TaskPage t:tasks){
            System.out.println(t);
        }
    }
    public static TaskPage getTaskById(int taskId){
        loadTasksIfEmpty();
        for(TaskPage t:tasks){
            if(t.getTaskId()==taskId){
                return t;
            }
        }
        return null;
    }
    public static ArrayList<TaskPage> getTasksByCode(int code){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getCode()==code){
                tp.add(t);
            }
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByTitle(String title){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getTitle().equalsIgnoreCase(title)){
                tp.add(t);
            }
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByDesc(String desc){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getDescription().equalsIgnoreCase(desc)){
                tp.add(t);
            }
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByAssignedEmployee(Employee assignedEmp){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getAssignedEmp().equals(assignedEmp)){
                tp.add(t);
            }
        }
        return tp;
    }
    public static  ArrayList<TaskPage> getTasksByTaskPhase(TaskPhases taskPhase){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getTaskPhase().equals(taskPhase)){
                tp.add(t);
            }
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByProject(Projects project){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getProject().equals(project)){
                tp.add(t);
            }
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByPriority(int priority){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getPriority()==priority){
                tp.add(t);
            }
        }
        return tp;
    }
    public static  ArrayList<TaskPage> getTasksByCreatorName(Employee creatorName){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for(TaskPage t:tasks){
            if(t.getCreatorName().equals(creatorName)){
                tp.add(t);
            }
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByStartDate(Date startDate){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for (TaskPage t:tasks){
            if(t.getStartDate().equals(startDate))
                tp.add(t);
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByEndDate(Date endDate){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for (TaskPage t:tasks){
            if(t.getEndDate().equals(endDate))
                tp.add(t);
        }
        return tp;
    }
    public static ArrayList<TaskPage> getTasksByEstimationHours(double estimationHours){
        loadTasksIfEmpty();
        ArrayList <TaskPage> tp=new ArrayList<>();
        for (TaskPage t:tasks){
            if(t.getEstimationHours()==estimationHours)
                tp.add(t);
        }
        return tp;
    }
    public static String changeTaskId(int taskId, int newTaskId){
        loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null&&getTaskById(newTaskId)==null){
         task.setTaskId(newTaskId);
         save();
         return "Task ID updated";
       }
       else if(getTaskById(newTaskId)!=null)return "New Id already exists";
       else return "Task not found";
   }
    
    public static String changeTaskCode(int taskId, int newCode){
        loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setCode(newCode);
         save();
         return "Code updated";
       }
       return "Task not found";
   }
   public static String changeTaskTitle(int taskId, String newTitle){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setTitle(newTitle);
         save();
         return "Title updated";
       } 
       return "Task not found";
   }
   public static String changeTaskDesc(int taskId, String newDesc){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setDescription(newDesc);
         save();
         return "Description updated";
        }
        return "Task not found";
   }
   public static String changeTaskAssignedEmp(int taskId,Employee newAssignedEmp){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setAssignedEmp(newAssignedEmp);
         save();
         return "Assigned employee updated";
       }
       return "Task not found"; 
   }
   public static String changeTaskPhase(int taskId,TaskPhases newTaskPhase){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setTaskPhase(newTaskPhase);
         save();
         return "TaskPhase updated";
       }
       return "Task not found";
   }
   public static String changeTaskProject(int taskId,Projects newProject){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setProject(newProject);
         save();
         return "Project updated";
       }
       return "Task not found";
   }
   public static String changeTaskPriority(int taskId,int newPriority){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setPriority(newPriority);
         save();
         return "Priority updated";
       }
       return "Task not found";
   }
   public static String changeTaskCreatorName(int taskId,Employee newCreatorName){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setCreatorName(newCreatorName);
         save();
         return "CreatorName updated";
       }
       return "Task not found";
   }
   public static String changeTaskStartDate(int taskId,Date newStartDate){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setStartDate(newStartDate);
         save();
         return "Start date updated";
       }
       return "Task not found";
   }
   public static String changeTaskEndDate(int taskId,Date newEndDate){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setEndDate(newEndDate);
         save();
         return "End date updated";
       }
       return "Task not found";
   
   }      
   public static String changeTaskEstimationHours(int taskId,double newEstimationHours){
       loadTasksIfEmpty();
       TaskPage task=getTaskById(taskId);
       if( task!=null){
         task.setEstimationHours(newEstimationHours);
         save();
         return "Estimation hours updated";
       }
       return "Task not found";
   }
   public static Integer getCode(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getCode():null;
   }
   public static  String getTitle(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getTitle():null;
    }
   public static String getDescription(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getDescription():null;
    }
   public static Employee getAssignedEmp(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getAssignedEmp():null;
    }    
   public static TaskPhases getTaskPhase(int taskId){
        loadTasksIfEmpty();
        TaskPage t= getTaskById(taskId);
        return t!=null?t.getTaskPhase():null;
    }
   public static Projects getProject(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getProject():null;
    }
   public static Integer getPriority(int taskId){
       loadTasksIfEmpty();
       TaskPage t=getTaskById(taskId);
       return t!=null?t.getPriority():null;
    }
   public static Employee getCreatorName(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getCreatorName():null;
   }
   public static Date getStartDate(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getStartDate():null;
    }
   public static Date getEndDate(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getEndDate():null;
    }
    public static Double getEstimationHours(int taskId){
        loadTasksIfEmpty();
        TaskPage t=getTaskById(taskId);
        return t!=null?t.getEstimationHours():null;
    }
    public static ArrayList<TaskPage> getTasksForEmployee(long employeeId) {
        loadTasksIfEmpty();
        ArrayList<TaskPage> employeeTasks = new ArrayList<>();
        for (TaskPage t : tasks) {
            if (t == null || t.getAssignedEmp() == null)
                continue;
            if (t.getAssignedEmp().getID() == employeeId) {
                employeeTasks.add(t);
            }
        }
        return employeeTasks;
    }
    
}
