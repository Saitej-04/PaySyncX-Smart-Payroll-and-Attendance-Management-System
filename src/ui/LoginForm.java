package ui;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Login");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 100, 30);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(130, 30, 150, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 100, 30);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 70, 150, 30);
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(110, 120, 120, 30);
        loginBtn.addActionListener(e -> loginUser());
        panel.add(loginBtn);

        add(panel);
    }

    private void loginUser() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if (user.equals("admin") && pass.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

            // 👉 Open NEW UI
            MainApp.main(null);

            dispose(); // close login form
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
