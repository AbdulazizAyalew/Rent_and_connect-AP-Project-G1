package ui;

import models.Renter;
import models.HouseListing;
import services.HouseService;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class RenterDashboard extends JFrame {

    private final Renter renter;
    private JPanel listingsPanel;
    private JComboBox<String> locationFilter;
    private JComboBox<String> typeFilter;

    public RenterDashboard(Renter renter) {
        this.renter = renter;

        setTitle("Renter Dashboard");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 150, 243));
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel welcomeLabel = new JLabel("Welcome, " + renter.getUsername() + " 👋");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBackground(new Color(244, 67, 54));
        signOutButton.setForeground(Color.WHITE);
        signOutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        signOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signOutButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(signOutButton, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Filter Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        topPanel.setBackground(new Color(245, 245, 245));

        JButton homeButton = new JButton("🏠 Home");
        homeButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        homeButton.addActionListener(e -> {
        new MainMenu();
        dispose();
        });

        locationFilter = new JComboBox<>();
        typeFilter = new JComboBox<>();
        JButton filterButton = new JButton("🔍 Filter");

        locationFilter.setFont(new Font("SansSerif", Font.PLAIN, 14));
        typeFilter.setFont(new Font("SansSerif", Font.PLAIN, 14));
        filterButton.setFont(new Font("SansSerif", Font.PLAIN, 14));

        topPanel.add(homeButton);
        topPanel.add(new JLabel("Location:"));
        topPanel.add(locationFilter);
        topPanel.add(new JLabel("House Type:"));
        topPanel.add(typeFilter);
        topPanel.add(filterButton);

        add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        // Listings Panel
        listingsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        listingsPanel.setBackground(new Color(250, 250, 250));
        JScrollPane scrollPane = new JScrollPane(listingsPanel);
        scrollPane.setBorder(new EmptyBorder(15, 25, 15, 25));
        add(scrollPane, BorderLayout.CENTER);

        filterButton.addActionListener(e -> {
            String location = locationFilter.getSelectedItem().toString();
            String type = typeFilter.getSelectedItem().toString();
            fetchAndDisplayListings(location, type);
        });

        // Load dropdown values and listings
        populateFilterOptions();
        fetchAndDisplayListings("All", "All");

        setVisible(true);
    }

    private void populateFilterOptions() {
        try {
            List<HouseListing> listings = new HouseService().getAllAvailableHouses();

            Set<String> locations = new HashSet<>();
            Set<String> types = new HashSet<>();

            for (HouseListing h : listings) {
                locations.add(h.getLocation());
                types.add(h.getHouseType());
            }

            locationFilter.addItem("All");
            types.forEach(typeFilter::addItem);
            typeFilter.addItem("All");
            locations.forEach(locationFilter::addItem);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load filter options.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchAndDisplayListings(String location, String type) {
        listingsPanel.removeAll();

        try {
            List<HouseListing> listings = new HouseService().getAllAvailableHouses();

            for (Iterator<HouseListing> it = listings.iterator(); it.hasNext(); ) {
                HouseListing h = it.next();
                if (!"All".equals(location) && !h.getLocation().equalsIgnoreCase(location)) {
                    it.remove();
                } else if (!"All".equals(type) && !h.getHouseType().equalsIgnoreCase(type)) {
                    it.remove();
                }
            }

            if (listings.isEmpty()) {
                JLabel emptyLabel = new JLabel("No listings found 🏚️", SwingConstants.CENTER);
                emptyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
                listingsPanel.add(emptyLabel);
            } else {
                for (HouseListing listing : listings) {
                    listingsPanel.add(createHouseCard(listing));
                }
            }

        } catch (SQLException e) {
            JLabel errorLabel = new JLabel("Error fetching listings: " + e.getMessage(), SwingConstants.CENTER);
            errorLabel.setForeground(Color.RED);
            listingsPanel.add(errorLabel);
        }

        listingsPanel.revalidate();
        listingsPanel.repaint();
    }

    private JPanel createHouseCard(HouseListing listing) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(250, 250));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel title = new JLabel(listing.getListingTitle(), SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 15));
        title.setForeground(new Color(33, 33, 33));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel location = new JLabel("📍Location: " + listing.getLocation());
        JLabel type = new JLabel("🏠 House Type: " + listing.getHouseType());
        JLabel price = new JLabel("💰 Price: " + listing.getPrice() + " ETB");

        Font infoFont = new Font("SansSerif", Font.PLAIN, 13);
        location.setFont(infoFont);
        type.setFont(infoFont);
        price.setFont(infoFont);

        location.setAlignmentX(Component.CENTER_ALIGNMENT);
        type.setAlignmentX(Component.CENTER_ALIGNMENT);
        price.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(location);
        card.add(type);
        card.add(price);
        card.add(Box.createVerticalGlue());

        return card;
    }
}
