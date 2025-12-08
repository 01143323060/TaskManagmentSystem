package GUI;

import javax.swing.*;
import java.awt.*;
import EmployeeModule.*;

public class MainFrame extends JFrame {
    private String role;           // "Admin" or "Employee"
    private int employeeId = -1;   // only for Employee role

    public MainFrame(String role) {
        this.role = role;
        initUI();
    }

    public MainFrame(String role, int employeeId) {
        this.role = role;
        this.employeeId = employeeId;
        initUI();
    }

    private void initUI() {
        setTitle("Task Management System");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Tabbed Pane
        JTabbedPane tabs = new JTabbedPane();

        if (role.equals("Admin")) {
            tabs.addTab("Admin Module", new AdminPanel());
            tabs.addTab("Tasks Module", new TaskPanel(true));     // true = admin can manage all
            tabs.addTab("Employee Module", new EmployeePanel(true));
        } else {
            tabs.addTab("My Tasks", new TaskPanel(false, employeeId));
            tabs.addTab("Time Card & Requests", new EmployeePanel(false, employeeId));
        }

        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }
}