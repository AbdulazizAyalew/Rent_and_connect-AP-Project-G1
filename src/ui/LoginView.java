package ui;

import services.UserService;
import models.Renter;
import models.Lender;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Login",
            TitledBorder.CENTER,
            TitledBorder.TOP
        ));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        panel.add(createLabel("Username:"), gbcLabel(gbc, 0, row));
        usernameField = new JTextField(15);
        panel.add(usernameField, gbcField(gbc, 1, row++));

        panel.add(createLabel("Password:"), gbcLabel(gbc, 0, row));
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbcField(gbc, 1, row++));

        panel.add(createLabel("Role:"), gbcLabel(gbc, 0, row));
        roleBox = new JComboBox<>(new String[]{"Renter", "Lender"});
        panel.add(roleBox, gbcField(gbc, 1, row++));

        JButton loginBtn = new JButton("Login");
        panel.add(loginBtn, gbcField(gbc, 0, row));

        JButton backBtn = new JButton("Back");
        panel.add(backBtn, gbcField(gbc, 1, row));

        add(panel);

        // Login Action
        loginBtn.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleBox.getSelectedItem();

            try {
                if (role.equals("Renter")) {
                    UserService renterService = new UserService();
                    Renter renter = renterService.loginRenter(username, password);
                    if (renter != null) {
                        JOptionPane.showMessageDialog(this, "Welcome, " + renter.getUsername());
                        new RenterDashboard(renter); // Optional future GUI
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid renter credentials!");
                    }
                } else {
                    UserService lenderService = new UserService();
                    Lender lender = lenderService.loginLender(username, password);
                    if (lender != null) {
                        JOptionPane.showMessageDialog(this, "Welcome, " + lender.getUsername());
                        new LenderDashboard(lender); // Optional future GUI
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid lender credentials!");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Login error: " + ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        setVisible(true);
    }
    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    private GridBagConstraints gbcLabel(GridBagConstraints gbc, int x, int y) {
        GridBagConstraints c = (GridBagConstraints) gbc.clone();
        c.gridx = x;
        c.gridy = y;
        return c;
    }

    private GridBagConstraints gbcField(GridBagConstraints gbc, int x, int y) {
        GridBagConstraints c = (GridBagConstraints) gbc.clone();
        c.gridx = x;
        c.gridy = y;
        return c;
    }

}
