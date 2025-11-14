package ui;

import dao.AttendanceDAO;
import dao.EmployeeDAO;
import models.Attendance;
import models.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AttendanceUI extends JFrame {

    private JTable table;

    public AttendanceUI() {
        setTitle("Attendance Management");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lbl = new JLabel("Employee Attendance");
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        root.add(lbl, BorderLayout.NORTH);

        // Table
        table = new JTable();
        table.setRowHeight(26);
        root.add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottom = new JPanel();
        JButton checkIn = new JButton("Check In");
        JButton checkOut = new JButton("Check Out");
        JButton manual = new JButton("Add Manual Entry");

        checkIn.addActionListener(e -> checkInAction());
        checkOut.addActionListener(e -> checkOutAction());
        manual.addActionListener(e -> new AttendanceForm(this).setVisible(true));

        bottom.add(checkIn);
        bottom.add(checkOut);
        bottom.add(manual);
        root.add(bottom, BorderLayout.SOUTH);

        add(root);
        loadAttendance();
    }

    public void loadAttendance() {
        String[] cols = {"ID", "Emp ID", "Date", "Check-In", "Check-Out", "Hours"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<Attendance> list = AttendanceDAO.getAll();

        for (Attendance a : list) {
            model.addRow(new Object[]{
                    a.getId(),
                    a.getEmpId(),
                    a.getAttDate().toString(),
                    a.getCheckIn() != null ? a.getCheckIn().toString() : "",
                    a.getCheckOut() != null ? a.getCheckOut().toString() : "",
                    a.getHoursWorked()
            });
        }

        table.setModel(model);
    }

    private int chooseEmployee() {
        List<Employee> emps = EmployeeDAO.getAll();
        if (emps.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No employees found!");
            return -1;
        }

        String[] names = new String[emps.size()];
        for (int i = 0; i < emps.size(); i++) {
            names[i] = emps.get(i).getEmpId() + " - " + emps.get(i).getName();
        }

        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Select Employee",
                "Choose",
                JOptionPane.PLAIN_MESSAGE,
                null,
                names,
                names[0]
        );

        if (selected == null) return -1;
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    private void checkInAction() {
        int empId = chooseEmployee();
        if (empId == -1) return;

        boolean ok = AttendanceDAO.checkIn(empId);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Check-in successful!");
            loadAttendance();
        } else {
            JOptionPane.showMessageDialog(this, "Check-in failed!");
        }
    }

    private void checkOutAction() {
        int empId = chooseEmployee();
        if (empId == -1) return;

        boolean ok = AttendanceDAO.checkOut(empId);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Check-out successful!");
            loadAttendance();
        } else {
            JOptionPane.showMessageDialog(this, "Check-out failed!");
        }
    }
}
