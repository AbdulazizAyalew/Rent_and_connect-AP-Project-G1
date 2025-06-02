package ui;

import services.UserService;
import models.Renter;
import models.Lender;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    public LoginView() throws SQLException {
        setTitle("Login Page");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 15));
        getContentPane().setBackground(new Color(44, 62, 80)); // Dark background same as RegisterView

        // Title label
        JLabel title = new JLabel("Login to Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Form panel with light background and padding
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setOpaque(true);
        formPanel.setPreferredSize(new Dimension(400, 250));

        // Border with title styled like RegisterView
        Border outerBorder = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
            outerBorder,
            "",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 18),
            new Color(85, 85, 85)
        );
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            titledBorder,
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 8, 12, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        formPanel.add(createLabel("Username:"), gbcLabel(gbc, 0, row));
        usernameField = new JTextField(20);
        addPlaceholder(usernameField, "Enter username");
        formPanel.add(usernameField, gbcField(gbc, 1, row++));

        formPanel.add(createLabel("Password:"), gbcLabel(gbc, 0, row));
        passwordField = new JPasswordField(20);
        addPlaceholder(passwordField, "Enter password");
        formPanel.add(passwordField, gbcField(gbc, 1, row++));

        formPanel.add(createLabel("Role:"), gbcLabel(gbc, 0, row));
        roleBox = new JComboBox<>(new String[]{"Renter", "Lender"});
        roleBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        formPanel.add(roleBox, gbcField(gbc, 1, row++));

        // Buttons panel for alignment and spacing
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 35, 10));
        buttonsPanel.setBackground(Color.WHITE);

        JButton loginBtn = new JButton("Login");
        JButton backBtn = new JButton("Back");

        // Stylish button setup
        Color buttonColor = new Color(44, 62, 80); // Same blue as RegisterView
        Color hoverColor = new Color(0, 105, 217);
        Color textColor = Color.WHITE;
        Border roundedBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(buttonColor.darker(), 1, true),
            BorderFactory.createEmptyBorder(10, 35, 10, 35)
        );

        JButton[] buttons = {loginBtn, backBtn};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setFocusPainted(false);
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setBorder(roundedBorder);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setOpaque(true);

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(hoverColor);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonColor);
                }
            });
        }

        buttonsPanel.add(loginBtn);
        buttonsPanel.add(backBtn);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonsPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button actions
        loginBtn.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String role = (String) roleBox.getSelectedItem();

            if (
                username.isEmpty() || username.equals("Enter username") ||
                password.isEmpty() || password.equals("Enter password")
            ) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                UserService userService = new UserService();
                if (role.equals("Renter")) {
                    Renter renter = userService.loginRenter(username, password);
                    if (renter != null) {
                        JOptionPane.showMessageDialog(this, "Welcome, " + renter.getUsername() + "!");
                        new RenterDashboard(renter);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid renter credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Lender lender = userService.loginLender(username, password);
                    if (lender != null) {
                        JOptionPane.showMessageDialog(this, "Welcome, " + lender.getUsername() + "!");
                        new LenderDashboard(lender);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid lender credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Login error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(55, 65, 81)); // Dark grey text like RegisterView labels
        return label;
    }

    private GridBagConstraints gbcLabel(GridBagConstraints gbc, int x, int y) {
        GridBagConstraints c = (GridBagConstraints) gbc.clone();
        c.gridx = x;
        c.gridy = y;
        c.anchor = GridBagConstraints.EAST;
        return c;
    }

    private GridBagConstraints gbcField(GridBagConstraints gbc, int x, int y) {
        GridBagConstraints c = (GridBagConstraints) gbc.clone();
        c.gridx = x;
        c.gridy = y;
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    // Placeholder helper (works for JTextField and JPasswordField)
    private void addPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
}
