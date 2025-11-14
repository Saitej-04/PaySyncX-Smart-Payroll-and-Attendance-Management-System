package ui;

import dao.EmployeeDAO;
import models.Employee;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class EmployeeForm extends JFrame {

    private JTextField nameField, roleField, salaryField, emailField, phoneField;

    public EmployeeForm() {
        setTitle("Add Employee");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Role:"));
        roleField = new JTextField();
        add(roleField);

        add(new JLabel("Basic Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        JButton saveBtn = new JButton("Save");
        add(saveBtn);

        saveBtn.addActionListener(e -> saveEmployee());
    }

    private void saveEmployee() {
        try {
            Employee emp = new Employee();
            emp.setName(nameField.getText());
            emp.setRole(roleField.getText());
            emp.setBasicSalary(Double.parseDouble(salaryField.getText()));
            emp.setEmail(emailField.getText());
            emp.setPhone(phoneField.getText());

            // DATE JOINED = TODAY
            emp.setDateJoined(new Date(System.currentTimeMillis()));
            emp.setActive(true);

            EmployeeDAO.insert(emp);

            JOptionPane.showMessageDialog(this, "Employee Added!");
            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving employee!");
        }
    }
}
