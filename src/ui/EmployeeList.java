package ui;

import dao.EmployeeDAO;
import models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeList extends JFrame {

    JTable table;
    DefaultTableModel model;

    public EmployeeList() {
        setTitle("Employee List");
        setSize(700, 400);
        setLocationRelativeTo(null);

        String[] cols = {"ID", "Name", "Role", "Salary", "Email", "Phone", "Active", "Delete"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return c == 7;    // only DELETE column clickable
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);

        // DELETE button renderer + editor
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), this));

        loadEmployees();

        add(new JScrollPane(table));
    }

    // -------------------------------------------------
    // LOAD EMPLOYEES FROM DATABASE
    // -------------------------------------------------
    public void loadEmployees() {
        model.setRowCount(0);

        List<Employee> list = EmployeeDAO.getAll();

        for (Employee e : list) {
            model.addRow(new Object[]{
                    e.getEmpId(),
                    e.getName(),
                    e.getRole(),
                    e.getBasicSalary(),
                    e.getEmail(),
                    e.getPhone(),
                    e.isActive() ? "YES" : "NO",
                    "Delete"
            });
        }
    }
}
