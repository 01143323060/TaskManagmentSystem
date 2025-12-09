package DataBase;
import AdminModule.*;
import java.io.*;
import java.util.*;
public class RWF {
    public static void login() throws Exception {
        ////////////////////////user///////////////////////
        File fileusers = new File("D:\\TaskManagmentSystem\\Resources\\Users.txt");
        if (fileusers.length() != 0) {
            try (Scanner input = new Scanner(fileusers)) {
                long i = 0;
                while (input.hasNext()) {
                    String n = input.next();
                    i = input.nextLong();
                    String p = input.next();
                    String r = input.next();
                    AdminModule.Users.setCounter(i);
                    Users u = new Users(n, p, r);
                    Query.addUser(u);
                }
                i++;
                AdminModule.Users.setCounter(i);
            }
        }else {
            AdminModule.Users.setCounter(1);
        }
        // ////////////////////////employeetype///////////////////////
        File fileemployeetype = new File("D:\\TaskManagmentSystem\\Resources\\EmployeeType.txt");
        if (fileemployeetype.length() != 0) {
            try (Scanner input = new Scanner(fileemployeetype)) {
                long i = 0;
                while (input.hasNext()) {
                    i = input.nextLong();
                    String name = input.nextLine().trim();
                    AdminModule.EmployeeType.setCounter(i);
                    EmployeeType et = new EmployeeType(name);
                    Query.addEmployeeType(et);
                }
                i++;
                AdminModule.EmployeeType.setCounter(i);
            }
        }else {
            AdminModule.EmployeeType.setCounter(1);
        }
        // ////////////////////////employee///////////////////////
        File fileemployee = new File("D:\\TaskManagmentSystem\\Resources\\Employee.txt");
        if (fileemployee.length() != 0) {
            try (Scanner input = new Scanner(fileemployee)) {
                long i = 0;
                while (input.hasNext()) {
                    String name = input.next();
                    i = input.nextLong();
                    String pass = input.next();
                    String role = input.next();
                    String hiredate = input.next();
                    long typeid = input.nextLong();
                    String type = input.nextLine();
                    AdminModule.Employee.setEmpCounter(i);
                    Employee e = new Employee(name, pass, role, hiredate, typeid);
                    Query.addEmployee(e);
                }
                i++;
                AdminModule.Employee.setEmpCounter(i); 
            }
        }else {
            AdminModule.Employee.setEmpCounter(1);
        }
        ///////////////////////taskphases///////////////////////
        File filetaskphases = new File("D:\\TaskManagmentSystem\\Resources\\TaskPhases.txt");
        if (filetaskphases.length() != 0) {
            try (Scanner input = new Scanner(filetaskphases)) {
                long id = 0;
                while (input.hasNext()) {
                    id = input.nextLong();
                    String phase = input.nextLine().trim();
                    AdminModule.TaskPhases.setCounter(id);
                    TaskPhases tp = new TaskPhases(phase);
                    Query.addTaskPhase(tp);
                }
                id++;
                AdminModule.TaskPhases.setCounter(id);
            }
        }else {
            AdminModule.TaskPhases.setCounter(1);
        }
        ////////////////////////////projects///////////////////////
        File fileprojects = new File("D:\\TaskManagmentSystem\\Resources\\Projects.txt");
        if (fileprojects.length() != 0) {
            try (Scanner input = new Scanner(fileprojects)) {
                long id = 0;
                while (input.hasNext()) {
                    id = input.nextLong();
                    String title = input.next();
                    String phase = input.nextLine();
                    AdminModule.Projects.setCounter(id);
                    Projects p = new Projects(title, id);
                    Query.addProject(p);
                }
                id++;
                AdminModule.Projects.setCounter(id);
            }
        }else {
            AdminModule.Projects.setCounter(1);
        }
    }

    public static void logout()
    throws Exception {
        ////////////////////////user///////////////////////
        FileWriter fileusers = new FileWriter("D:\\TaskManagmentSystem\\Resources\\Users.txt");
        fileusers.write("");
        fileusers.close();
        File fileusers1 = new File("D:\\TaskManagmentSystem\\Resources\\Users.txt");
        if (fileusers1.length() == 0) {
            try (PrintWriter output = new PrintWriter(fileusers1)) {
                while (ArrayLists.users.size() != 0) {
                    Users u = ArrayLists.users.remove(0);
                    output.print(u.getName() + " ");
                    output.print(u.getID() + " ");
                    output.print(u.getPassword() + " ");
                    output.println(u.getRole());
                }
            }
        }
        // ////////////////////////employeetype///////////////////////
        FileWriter fileemployeetype = new FileWriter("D:\\TaskManagmentSystem\\Resources\\EmployeeType.txt");
        fileemployeetype.write("");
        fileemployeetype.close();
        File fileemployeetype1 = new File("D:\\TaskManagmentSystem\\Resources\\EmployeeType.txt");
        if (fileemployeetype1.length() == 0) {
            try (PrintWriter output = new PrintWriter(fileemployeetype1)) {
                while (ArrayLists.employeetype.size() != 0) {
                    EmployeeType et = ArrayLists.employeetype.remove(0);
                    output.print(et.getId() + " ");
                    output.println(et.getname());
                }
            }
        }
        // ////////////////////////employee///////////////////////
        FileWriter fileemployee = new FileWriter("D:\\TaskManagmentSystem\\Resources\\Employee.txt");
        fileemployee.write("");
        fileemployee.close();
        File fileemployee1 = new File("D:\\TaskManagmentSystem\\Resources\\Employee.txt");
        if (fileemployee1.length() == 0) {
            try (PrintWriter output = new PrintWriter(fileemployee1)) {
                while (ArrayLists.employee.size() != 0) {
                    Employee e = ArrayLists.employee.remove(0);
                    output.print(e.getName() + " ");
                    output.print(e.getID() + " ");
                    output.print(e.getPassword() + " ");
                    output.print(e.getRole() + " ");
                    output.print(e.getHireDate() + " ");
                    output.print(e.getTypeID() + " ");
                    output.println(e.getType());
                    // output.println(Query.getTypeNameById(e.getTypeID()));
                }
            }
        }
        ///////////////////////taskphases///////////////////////
        FileWriter filetaskphases = new FileWriter("D:\\TaskManagmentSystem\\Resources\\TaskPhases.txt");
        filetaskphases.write("");
        filetaskphases.close();
        File filetaskphases1 = new File("D:\\TaskManagmentSystem\\Resources\\TaskPhases.txt");
        if (filetaskphases1.length() == 0) {
            try (PrintWriter output = new PrintWriter(filetaskphases1)) {
                while (ArrayLists.taskphases.size() != 0) {
                    TaskPhases tp = ArrayLists.taskphases.remove(0);
                    output.print(tp.getid() + " ");
                    output.println(tp.getphase());
                }
            }
        }
        ////////////////////////////projects///////////////////////
        FileWriter fileprojects = new FileWriter("D:\\TaskManagmentSystem\\Resources\\Projects.txt");
        fileprojects.write("");
        fileprojects.close();
        File fileprojects1 = new File("D:\\TaskManagmentSystem\\Resources\\Projects.txt");
        if (fileprojects1.length() == 0) {
            try (PrintWriter output = new PrintWriter(fileprojects1)) {
                while (ArrayLists.projects.size() != 0) {
                    Projects p = ArrayLists.projects.remove(0);
                    output.print(p.getID() + " ");
                    output.print(p.getTitle() + " ");
                    output.print(p.getPhaseID()+ " ");
                    output.println(p.getPhase());
                }
            }
        }
    }
}