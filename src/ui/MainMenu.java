package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Rent & Connect");
        setSize(750, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        Image icon = Toolkit.getDefaultToolkit().getImage("src/ui/Images/Icon.png");
        setIconImage(icon);


        // Fonts
        Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 15);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(44, 62, 80)); // dark blue-gray
        JLabel titleLabel = new JLabel("RENT & CONNECT", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Center Content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
        centerPanel.setBackground(Color.WHITE);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(246, 245, 245));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descriptionLabel = new JLabel(
                "<html><div style='text-align: center;'>Rent & Connect is a simple and user-friendly platform<br>" +
                        "where people can easily find rental homes online.<br>" +
                        "We aim to make the rental process smooth and accessible for everyone.</div></html>",
                SwingConstants.CENTER);
        descriptionLabel.setFont(textFont);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        JButton loginButton = createStyledButton("Login", buttonFont);
        JButton registerButton = createStyledButton("Register", buttonFont);
        JButton exitButton = createStyledButton("Exit", buttonFont);

        // Add components
        card.add(descriptionLabel);
        card.add(Box.createVerticalStrut(30));
        card.add(loginButton);
        card.add(Box.createVerticalStrut(15));
        card.add(registerButton);
        card.add(Box.createVerticalStrut(15));
        card.add(exitButton);

        centerPanel.add(card);
        add(centerPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new GridLayout(2, 1));
        footerPanel.setBackground(new Color(230, 230, 230));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel contactLabel = new JLabel("Contact us: rentconnect@gmail.com | +251 900 931 23", SwingConstants.CENTER);
        contactLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel copyright =
                new JLabel("© 2025 Rent & Connect. All rights reserved.", SwingConstants.CENTER);
        copyright.setFont(new Font("Segoe UI", Font.ITALIC, 12));

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

    // Utility method to style buttons
    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBackground(new Color(44, 62, 80));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(44, 62, 80));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
