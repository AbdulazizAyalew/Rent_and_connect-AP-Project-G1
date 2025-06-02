package ui;

import models.Lender;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;

import services.HouseService;
import models.HouseListing;

public class LenderDashboard extends JFrame {

    private final Lender lender;

   public LenderDashboard(Lender lender) {
    this.lender = lender;

    setTitle("Lender Dashboard");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    getContentPane().setBackground(Color.WHITE); // cleaner background

    // === Main container ===
    JPanel container = new JPanel();
    container.setLayout(new BorderLayout(20, 20));
    container.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    container.setBackground(Color.WHITE);

    // === Welcome Section ===
    JLabel welcomeLabel = new JLabel("Welcome, " + lender.getUsername() + "!");
    welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeLabel.setForeground(new Color(33, 37, 41));

    JLabel subLabel = new JLabel("What would you like to do today?");
    subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    subLabel.setHorizontalAlignment(SwingConstants.CENTER);
    subLabel.setForeground(new Color(85, 85, 85));

    JPanel welcomePanel = new JPanel(new GridLayout(2, 1, 5, 5));
    welcomePanel.setBackground(Color.WHITE);
    welcomePanel.add(welcomeLabel);
    welcomePanel.add(subLabel);

    // === Button Panel ===
    JButton addListingButton = new JButton("  Add New House Listing");
    JButton logoutButton = new JButton("Exit");

    Font btnFont = new Font("Segoe UI", Font.PLAIN, 16);
    addListingButton.setFont(btnFont);
    logoutButton.setFont(btnFont);

    addListingButton.setFocusPainted(false);
    logoutButton.setFocusPainted(false);

    JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 15, 15));
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.add(addListingButton);
    buttonPanel.add(logoutButton);

    // === Combine Panels ===
    container.add(welcomePanel, BorderLayout.NORTH);
    container.add(buttonPanel, BorderLayout.CENTER);

    add(container);

    // === Logic remains unchanged ===
    addListingButton.addActionListener(e -> showAddHouseListingForm());
    logoutButton.addActionListener(e -> {
        new MainMenu();
        dispose();
    });

    setVisible(true);
}


   private void showAddHouseListingForm() {
    JDialog dialog = new JDialog(this, "Add House Listing", true);
    dialog.setSize(500, 600); // increased height for description field
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(new BorderLayout(10, 10));

    JLabel headerLabel = new JLabel("Add New House Listing", SwingConstants.CENTER);
    headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
    headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
    dialog.add(headerLabel, BorderLayout.NORTH);

    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel titleLabel = new JLabel("Title:");
    JTextField titleField = new JTextField(20);

    JLabel locationLabel = new JLabel("City:");
    JComboBox<String> locationCombo = new JComboBox<>(new String[]{
        "Addis Ababa", "Bole", "Lafto", "Jemo", "Gullele",
        "Kirkos", "Yeka", "Arada", "Nifas Silk", "Akaki Kality"
    });

    JLabel typeLabel = new JLabel("Type:");
    JComboBox<String> typeCombo = new JComboBox<>(new String[]{
        "Condo", "Apartment", "Villa", "Duplex", "Studio"
    });

    JLabel priceLabel = new JLabel("Price (ETB):");
    NumberFormat priceFormat = NumberFormat.getNumberInstance();
    priceFormat.setGroupingUsed(false);
    JFormattedTextField priceField = new JFormattedTextField(priceFormat);
    priceField.setColumns(20);

    JLabel availableLabel = new JLabel("Available:");
    JCheckBox availableCheck = new JCheckBox();

    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionArea = new JTextArea(4, 20);
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

    // === Add components row by row ===
    int row = 0;

    gbc.gridx = 0;
    gbc.gridy = row;
    formPanel.add(titleLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(titleField, gbc);

    gbc.gridx = 0;
    gbc.gridy = ++row;
    formPanel.add(locationLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(locationCombo, gbc);

    gbc.gridx = 0;
    gbc.gridy = ++row;
    formPanel.add(typeLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(typeCombo, gbc);

    gbc.gridx = 0;
    gbc.gridy = ++row;
    formPanel.add(priceLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(priceField, gbc);

    gbc.gridx = 0;
    gbc.gridy = ++row;
    formPanel.add(availableLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(availableCheck, gbc);

    gbc.gridx = 0;
    gbc.gridy = ++row;
    gbc.anchor = GridBagConstraints.NORTH;
    formPanel.add(descriptionLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(descriptionScroll, gbc);
    gbc.anchor = GridBagConstraints.CENTER;

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");
    buttonPanel.add(cancelButton);
    buttonPanel.add(saveButton);

    saveButton.addActionListener(e -> {
    try {
        String title = titleField.getText().trim();
        String priceText = priceField.getText().trim();
        String description = descriptionArea.getText().trim();

        // Validate required fields
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Title cannot be empty.");
            return;
        }
        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Price cannot be empty.");
            return;
        }
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Description cannot be empty.");
            return;
        }

        // Price validation: check if price is a valid number > 0
        BigDecimal price;
        try {
            price = new BigDecimal(priceText);
            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(dialog, "Price must be greater than 0.");
                return;
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(dialog, "Invalid price format.");
            return;
        }

        HouseListing house = new HouseListing();
        house.setTitle(title);
        house.setLocation((String) locationCombo.getSelectedItem());
        house.setHouseType((String) typeCombo.getSelectedItem());
        house.setPrice(price);
        house.setAvailable(availableCheck.isSelected());
        house.setDescription(description);

        boolean success = new HouseService().addHouse(house);
        JOptionPane.showMessageDialog(dialog, success
                ? "House listing added successfully!"
                : "Failed to add house listing.");
        if (success) dialog.dispose();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
    }
});


    cancelButton.addActionListener(e -> dialog.dispose());

    dialog.add(formPanel, BorderLayout.CENTER);
    dialog.add(buttonPanel, BorderLayout.SOUTH);
    dialog.setVisible(true);
}

}
