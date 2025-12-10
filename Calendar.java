package TaskModule;
import java.util.*;
public class Calendar {
    private ArrayList<TaskPage> tasks;
    public Calendar(ArrayList<TaskPage> tasks){
        this.tasks=tasks;
    }
    public void showAllEmployeesTasksAndPhases() {
        for (TaskPage task : tasks) {
            if (task == null) continue; 
            String empName = task.getAssignedEmp() != null ? task.getAssignedEmp().getName() : "Unassigned";
            String title = task.getTitle() != null ? task.getTitle() : "No title";
            String phase = task.getTaskPhase() != null ? task.getTaskPhase().toString() : "No phase";
            System.out.println("Employee: " + empName 
                + "     Task: " + title 
                + "     Phase: " + phase );
        }
    }
}