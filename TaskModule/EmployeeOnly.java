
import AdminModule.Employee;
import DataBase.QueryTask;
import java.util.ArrayList;

public class EmployeeOnly {
    public static void showMyTasks(Employee employee) {
        System.out.println("==== Your Tasks, " + employee.getName() + " ====");
        ArrayList<TaskPage> tasks = QueryTask.getTasksForEmployee(employee.getID());
        if (tasks.isEmpty()) {
            System.out.println("You have no assigned tasks.");
            return;
        }
        for (TaskPage t : tasks) {
            System.out.println("---------------------");
            System.out.println("Task ID: " + t.getTaskId());
            System.out.println("Title: " + t.getTitle());
            System.out.println("Phase: " + t.getTaskPhase().getphase());
            System.out.println("Project: " + t.getProject());
            System.out.println("Start Date: " + t.getStartDate());
            System.out.println("End Date: " + t.getEndDate());
        }
    }
}

