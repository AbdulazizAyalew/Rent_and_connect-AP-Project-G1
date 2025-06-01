package ui;

import models.Renter;
import models.Lender;
import services.UserService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class RegisterView extends JFrame {
    private JTextField usernameField;
    private JTextField cityField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    public RegisterView() throws SQLException {
        JLabel title = new JLabel("Register", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.BOLD, 18));
        add(title);

        setTitle("Register");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "Signup Form",
            TitledBorder.CENTER,
            TitledBorder.TOP
        ));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        formPanel.add(createLabel("Username:"), gbcLabel(gbc, 0, row));
        usernameField = new JTextField(15);
        formPanel.add(usernameField, gbcField(gbc, 1, row++));

        formPanel.add(createLabel("City:"), gbcLabel(gbc, 0, row));
        cityField = new JTextField(15);
        formPanel.add(cityField, gbcField(gbc, 1, row++));

        formPanel.add(createLabel("Password:"), gbcLabel(gbc, 0, row));
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbcField(gbc, 1, row++));

        formPanel.add(createLabel("Role:"), gbcLabel(gbc, 0, row));
        roleBox = new JComboBox<>(new String[]{"Renter", "Lender"});
        formPanel.add(roleBox, gbcField(gbc, 1, row++));

        JButton registerBtn = new JButton("Register");
        formPanel.add(registerBtn, gbcField(gbc, 0, row));

        JButton backBtn = new JButton("Back");
        formPanel.add(backBtn, gbcField(gbc, 1, row));

        add(formPanel);


        registerBtn.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String city = cityField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleBox.getSelectedItem();

            try {
                if (role.equals("Renter")) {
                    UserService renterService = new UserService();
                    renterService.registerRenter(new Renter(username, city, password));
                    JOptionPane.showMessageDialog(this, "Renter registered successfully!");
                } else {
                    UserService lenderService = new UserService();
                    lenderService.registerLender(new Lender(username, city, password));
                    JOptionPane.showMessageDialog(this, "Lender registered successfully!");
                }
                new MainMenu();
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Registration error: " + ex.getMessage());
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
