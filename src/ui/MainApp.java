package ui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MainApp.java
 * Single-file Swing app demonstrating:
 * - Left navigation (blue)
 * - Right content area with "cards" (rounded panels)
 * - CardLayout for switching content: Dashboard, Employees, Attendance, Payroll, Settings
 *
 * Save as MainApp.java, compile and run.
 */
public class MainApp {

    private static final Color BRAND_BLUE = new Color(14, 76, 154);    // corporate blue
    private static final Color LIGHT_BG = new Color(245, 248, 252);    // subtle page bg
    private static final Color CARD_BG = Color.WHITE;
    private static final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 18);
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 22);

    private JFrame frame;
    private JPanel contentCards;

    public static void main(String[] args) {
        // Launch on EDT
        SwingUtilities.invokeLater(() -> {
            new MainApp().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("PaySyncX — Demo UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setLocationRelativeTo(null);

        // Root split: left nav / right content
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerSize(1);
        split.setDividerLocation(220);
        split.setEnabled(false);

        split.setLeftComponent(buildNavPanel());
        split.setRightComponent(buildContentPanel());

        frame.getContentPane().add(split);
        frame.setVisible(true);
    }

    private JPanel buildNavPanel() {
        JPanel nav = new JPanel();
        nav.setBackground(BRAND_BLUE);
        nav.setLayout(new BorderLayout());
        nav.setBorder(new EmptyBorder(18, 14, 18, 14));

        // Top - app title
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JLabel logo = new JLabel("<html><span style='color:white;font-weight:bold;font-size:16pt'>PaySyncX</span></html>");
        logo.setBorder(new EmptyBorder(6, 6, 18, 6));
        top.add(logo, BorderLayout.NORTH);

        nav.add(top, BorderLayout.NORTH);

        // Middle - nav items
        JPanel items = new JPanel();
        items.setOpaque(false);
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        items.add(navItem("Dashboard", 0));
        items.add(Box.createVerticalStrut(6));
        items.add(navItem("Employees", 1));
        items.add(Box.createVerticalStrut(6));
        items.add(navItem("Attendance", 2));
        items.add(Box.createVerticalStrut(6));
        items.add(navItem("Payroll", 3));
        items.add(Box.createVerticalStrut(6));
        items.add(navItem("Settings", 4));

        // push items to top nicely
        JPanel centerWrap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerWrap.setOpaque(false);
        centerWrap.add(items);
        nav.add(centerWrap, BorderLayout.CENTER);

        // Bottom - small footer
        JLabel footer = new JLabel("<html><span style='color:#cfe0ff;font-size:10pt'>v1.0 • Demo UI</span></html>");
        footer.setBorder(new EmptyBorder(10, 6, 6, 6));
        nav.add(footer, BorderLayout.SOUTH);

        return nav;
    }

    private JPanel navItem(String text, int cardIndex) {
        JPanel item = new JPanel(new BorderLayout());
        item.setMaximumSize(new Dimension(200, 46));
        item.setBackground(new Color(0,0,0,0));
        item.setOpaque(false);

        JLabel label = new JLabel("  " + text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        label.setBorder(new EmptyBorder(8, 10, 8, 10));
        label.setOpaque(false);

        item.add(label, BorderLayout.CENTER);

        // hover/click effects
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(LIGHT_BG.darker());
                item.setBackground(new Color(255,255,255,20));
                item.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.WHITE);
                item.setOpaque(false);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard(cardIndex);
            }
        });

        return item;
    }

    private JPanel buildContentPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(LIGHT_BG);

        // top header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(LIGHT_BG);
        header.setBorder(new EmptyBorder(16, 18, 16, 18));
        JLabel title = new JLabel("Dashboard");
        title.setFont(TITLE_FONT);
        title.setForeground(BRAND_BLUE);
        header.add(title, BorderLayout.WEST);

        root.add(header, BorderLayout.NORTH);

        // content area with CardLayout
        contentCards = new JPanel(new CardLayout());
        contentCards.setBackground(LIGHT_BG);

        contentCards.add(dashboardPanel(), "0");
        contentCards.add(employeesPanel(), "1");
        contentCards.add(attendancePanel(), "2");
        contentCards.add(payrollPanel(), "3");
        contentCards.add(settingsPanel(), "4");

        root.add(wrapWithPadding(contentCards), BorderLayout.CENTER);

        return root;
    }

    // <<< FIXED: simple, reliable showCard method >>>
    private void showCard(int index) {
        CardLayout cl = (CardLayout) contentCards.getLayout();
        cl.show(contentCards, String.valueOf(index));

        // Update the window title to reflect selection
        String[] names = {"Dashboard", "Employees", "Attendance", "Payroll", "Settings"};
        if (index >= 0 && index < names.length) {
            frame.setTitle("PaySyncX — " + names[index]);
        }
    }

    private JPanel wrapWithPadding(JComponent c) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(LIGHT_BG);
        p.setBorder(new EmptyBorder(18, 18, 18, 18));
        p.add(c, BorderLayout.CENTER);
        return p;
    }

    // -------- Panels for each card --------

    private JPanel dashboardPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(LIGHT_BG);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(12, 12, 12, 12);
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1.0;
        g.weighty = 1.0;

        // Row 1: three cards horizontally
        JPanel row = new JPanel(new GridLayout(1, 3, 18, 0));
        row.setOpaque(false);
        row.add(createCard("Employee Management", "Manage employees, add / edit / remove", () -> showCard(1)));
        row.add(createCard("Attendance", "Record check-ins / check-outs", () -> showCard(2)));
        row.add(createCard("Payroll", "Generate payslips and reports", () -> showCard(3)));

        g.gridx = 0; g.gridy = 0;
        p.add(row, g);

        // Row 2: wide summary card
        g.gridy = 1;
        JPanel summary = createCard("Quick Summary", "<html><b>Employees:</b> 3 &nbsp;&nbsp; <b>Payrolls:</b> 1 &nbsp;&nbsp; <b>Pending:</b> 0</html>", null);
        summary.setPreferredSize(new Dimension(800, 220));
        p.add(summary, g);

        return p;
    }

    private JPanel createCard(String title, String subtitle, Runnable action) {
        RoundedPanel card = new RoundedPanel(14, CARD_BG);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(16, 16, 16, 16));
        card.setOpaque(true);

        JLabel t = new JLabel(title);
        t.setFont(HEADER_FONT);
        t.setForeground(BRAND_BLUE);

        JLabel s = new JLabel(subtitle);
        s.setFont(new Font("SansSerif", Font.PLAIN, 13));
        s.setForeground(Color.DARK_GRAY);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(t, BorderLayout.NORTH);
        top.add(s, BorderLayout.CENTER);

        card.add(top, BorderLayout.CENTER);

        if (action != null) {
            JButton btn = new JButton("Open");
            btn.setBackground(BRAND_BLUE);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(null);
            btn.addActionListener(e -> action.run());
            JPanel foot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            foot.setOpaque(false);
            foot.add(btn);
            card.add(foot, BorderLayout.SOUTH);

            // clickable card hover
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    card.setBackground(new Color(239, 246, 255));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    card.setBackground(CARD_BG);
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    action.run();
                }
            });
        }

        return card;
    }

    private JPanel employeesPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(LIGHT_BG);

        // header
        JLabel lbl = new JLabel("Employee List");
        lbl.setFont(HEADER_FONT);
        lbl.setForeground(BRAND_BLUE);
        p.add(lbl, BorderLayout.NORTH);

        // table with sample data
        String[] cols = {"ID","Name","Role","Basic Salary","Delete"};
        DefaultTableModel m = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return c==4; }
        };
        m.addRow(new Object[]{1, "Sai Kumar", "Software Engineer", 45000.0, "Delete"});
        m.addRow(new Object[]{2, "Ravi Teja", "HR Manager", 60000.0, "Delete"});
        m.addRow(new Object[]{3, "Anjali", "Accountant", 50000.0, "Delete"});

        JTable table = new JTable(m);
        table.setRowHeight(28);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new EmptyBorder(12,0,0,0));
        p.add(sp, BorderLayout.CENTER);

        return wrapWithCardLayoutPadding(p);
    }

    private JPanel attendancePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(LIGHT_BG);
        JLabel lbl = new JLabel("Employee Attendance");
        lbl.setFont(HEADER_FONT);
        lbl.setForeground(BRAND_BLUE);
        p.add(lbl, BorderLayout.NORTH);

        String[] cols = {"ID","Employee ID","Date","Check-in","Check-out","Hours"};
        DefaultTableModel m = new DefaultTableModel(cols, 0);
        m.addRow(new Object[]{1,1,"2025-11-01","09:00","17:00",8});
        m.addRow(new Object[]{2,1,"2025-11-02","09:30","17:30",8});
        m.addRow(new Object[]{3,2,"2025-11-01",null,null,0});
        m.addRow(new Object[]{4,3,"2025-11-01","10:00","17:00",7});
        JTable table = new JTable(m);
        table.setRowHeight(26);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new EmptyBorder(12,0,0,0));
        p.add(sp, BorderLayout.CENTER);

        // bottom quick actions
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setOpaque(false);
        JButton checkIn = new JButton("Check In");
        JButton checkOut = new JButton("Check Out");
        checkIn.setBackground(BRAND_BLUE);
        checkIn.setForeground(Color.WHITE);
        checkOut.setBackground(BRAND_BLUE);
        checkOut.setForeground(Color.WHITE);
        actions.add(checkIn);
        actions.add(checkOut);
        p.add(actions, BorderLayout.SOUTH);

        return wrapWithCardLayoutPadding(p);
    }

    private JPanel payrollPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(LIGHT_BG);
        JLabel lbl = new JLabel("Payroll Management");
        lbl.setFont(HEADER_FONT);
        lbl.setForeground(BRAND_BLUE);
        p.add(lbl, BorderLayout.NORTH);

        String[] cols = {"ID","Employee ID","Month","Year","Basic","Allowances","Deductions","Net Salary"};
        DefaultTableModel m = new DefaultTableModel(cols, 0);
        m.addRow(new Object[]{1,1,11,2025,45000.0,5000.0,2000.0,48000.0});
        JTable table = new JTable(m);
        table.setRowHeight(26);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new EmptyBorder(12,0,0,0));
        p.add(sp, BorderLayout.CENTER);

        JButton gen = new JButton("Generate Payslip");
        gen.setBackground(BRAND_BLUE);
        gen.setForeground(Color.WHITE);
        JPanel foot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        foot.setOpaque(false);
        foot.add(gen);
        p.add(foot, BorderLayout.SOUTH);

        return wrapWithCardLayoutPadding(p);
    }

    private JPanel settingsPanel() {
        JPanel p = new JPanel();
        p.setBackground(LIGHT_BG);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(12,12,12,12));

        JLabel lbl = new JLabel("Settings");
        lbl.setFont(HEADER_FONT);
        lbl.setForeground(BRAND_BLUE);
        p.add(lbl);
        p.add(Box.createVerticalStrut(12));

        JCheckBox cb1 = new JCheckBox("Enable email notifications");
        cb1.setOpaque(false);
        JCheckBox cb2 = new JCheckBox("Auto-generate payroll monthly");
        cb2.setOpaque(false);
        p.add(cb1);
        p.add(cb2);

        return wrapWithCardLayoutPadding(p);
    }

    private JPanel wrapWithCardLayoutPadding(JPanel content) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(LIGHT_BG);
        wrapper.setBorder(new EmptyBorder(12, 0, 0, 0));
        wrapper.add(content, BorderLayout.CENTER);
        return wrapper;
    }

    // -------- helper rounded panel --------
    static class RoundedPanel extends JPanel {
        private int cornerRadius = 15;
        private Color backgroundColor;

        public RoundedPanel(int radius, Color bg) {
            super();
            cornerRadius = radius;
            backgroundColor = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // drop shadow (light)
            g2.setColor(new Color(0,0,0,12));
            g2.fillRoundRect(4, 6, getWidth()-8, getHeight()-8, cornerRadius, cornerRadius);

            // main background
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth()-8, getHeight()-8, cornerRadius, cornerRadius);

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }
    }
}
