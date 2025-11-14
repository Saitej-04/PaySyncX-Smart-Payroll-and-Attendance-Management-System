package main;

import ui.LoginForm;

public class Main {
    public static void main(String[] args) {
        // start login UI on EDT
        javax.swing.SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
