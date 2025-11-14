package ui;

import dao.EmployeeDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {

    private JButton btn;
    private String label;
    private boolean clicked;
    private int row;
    private EmployeeList parent;

    public ButtonEditor(JCheckBox checkBox, EmployeeList parent) {
        super(checkBox);
        this.parent = parent;

        btn = new JButton();
        btn.setOpaque(true);
        btn.setBackground(Color.RED);
        btn.setForeground(Color.WHITE);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int col) {
        this.row = row;
        label = "Delete";
        btn.setText(label);
        clicked = true;
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            int empId = Integer.parseInt(parent.table.getValueAt(row, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(parent,
                    "Delete Employee ID: " + empId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                EmployeeDAO.delete(empId);
                parent.loadEmployees();
            }
        }
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
