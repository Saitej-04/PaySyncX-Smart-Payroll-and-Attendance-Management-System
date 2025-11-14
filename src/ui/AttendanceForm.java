package ui;

import dao.AttendanceDAO;
import dao.EmployeeDAO;
import models.Attendance;
import models.Employee;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AttendanceForm extends JFrame {

    private AttendanceUI parent;

    public AttendanceForm(AttendanceUI parent) {
        this.parent = parent;

        setTitle("Add Manual Attendance");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel p = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel l1 = new JLabel("Employee:");
        JComboBox<String> empDropdown = new JComboBox<>();

        List<Employee> emps = EmployeeDAO.getAll();
        for (Employee e : emps) {
            empDropdown.addItem(e.getEmpId() + " - " + e.getName());
        }

        JLabel l2 = new JLabel("Date (YYYY-MM-DD):");
        JTextField date = new JTextField();

        JLabel l3 = new JLabel("Check-In (HH:MM):");
        JTextField cin = new JTextField();

        JLabel l4 = new JLabel("Check-Out (HH:MM):");
        JTextField cout = new JTextField();

        JButton save = new JButton("Save");

        save.addActionListener(e -> {
            try {
                Attendance a = new Attendance();

                int empId = Integer.parseInt(empDropdown.getSelectedItem().toString().split(" - ")[0]);
                a.setEmpId(empId);
                a.setAttDate(Date.valueOf(date.getText()));
                a.setCheckIn(Time.valueOf(cin.getText() + ":00"));
                a.setCheckOut(Time.valueOf(cout.getText() + ":00"));
                a.setHoursWorked(8);

                AttendanceDAO.insert(a);
                parent.loadAttendance();
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid data!");
            }
        });

        p.add(l1); p.add(empDropdown);
        p.add(l2); p.add(date);
        p.add(l3); p.add(cin);
        p.add(l4); p.add(cout);
        p.add(new JLabel());
        p.add(save);

        add(p);
    }
}
