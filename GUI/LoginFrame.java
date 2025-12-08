package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsername = new JTextField(15);
    private JPasswordField txtPassword = new JPasswordField(15);

    public LoginFrame() {
        setTitle("Task Management System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Welcome to Task Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        JButton btnLogin = new JButton("Login");
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnLogin, gbc);

        btnLogin.addActionListener(e -> performLogin());

        // Simple hard-coded users (you can later read from employees.txt)
        // Admin: admin / admin
        // Employee: 1001 / 1234
    }

    private void performLogin() {
        String user = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword());

        if (user.equals("admin") && pass.equals("admin")) {
            dispose();
            new MainFrame("Admin");
        } else if (user.matches("\\d+") && pass.equals("1234")) { // any employee ID
            dispose();
            new MainFrame("Employee", Integer.parseInt(user));
        } else {
            JOptionPane.showMessageDialog(this,
                "Wrong username or password!", "Login Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // MAIN METHOD – THIS IS WHAT YOUR INSTRUCTOR WANTS TO SEE
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}