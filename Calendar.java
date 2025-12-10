package TaskModule;
import java.util.*;
public class Calendar {
    private ArrayList<TaskPage> tasks;
    public Calendar(ArrayList<TaskPage> tasks){
        this.tasks=tasks;
    }
    public void showAllEmployeesTasksAndPhases() {
        
        for (TaskPage task : tasks) {
            System.out.println("Employee: " + task.getAssignedEmp() 
                + "     Task: " + task.getTitle() 
                + "     Phase: " + task.getTaskPhase() );
        }
    }
}