package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Rent & Connect");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Font titleFont = new Font("Arial", Font.BOLD, 26);
        Font textFont = new Font("SansSerif", Font.PLAIN, 16);
        Font buttonFont = new Font("Serif", Font.BOLD, 16);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0x2E86C1));
        JLabel titleLabel = new JLabel("RENT & CONNECT", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Center Content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel descriptionLabel = new JLabel(
            "<html><center>Rent & Connect is a simple and user-friendly platform<br>" +
            "where people can easily find rental homes online. <br>" +
            "We aim to make the rental process smooth and accessible for everyone.</center></html>", SwingConstants.CENTER);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setFont(textFont);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton exitButton = new JButton("Exit");

        loginButton.setFont(buttonFont);
        registerButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(descriptionLabel);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(loginButton);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(registerButton);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(exitButton);

        add(centerPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new GridLayout(2, 1));
        footerPanel.setBackground(Color.LIGHT_GRAY);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel contactLabel = new JLabel("Contact us: rentconnect@gmail.com | +25190093123", SwingConstants.CENTER);
        contactLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel copyright =
            new JLabel("© 2025 Rent & Connect. All rights reserved.", SwingConstants.CENTER);
        copyright.setFont(new Font("SansSerif", Font.ITALIC, 12));

        footerPanel.add(contactLabel);
        footerPanel.add(copyright);
        add(footerPanel, BorderLayout.SOUTH);

        // Button Actions
        loginButton.addActionListener(e -> {
            try {
                new LoginView();
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error opening login view");
            }
        });

        registerButton.addActionListener(e -> {
            try {
                new RegisterView();
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error opening register view");
            }
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Goodbye!");
            System.exit(0);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
