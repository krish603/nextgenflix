package src;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDetailFrame extends JFrame {

    private String title;
    private String type; // To distinguish between Movie and TV Show
    private JLabel ratingLabel; // Declare the rating label

    public MovieDetailFrame(String type, String title, String imagePath, String description) {
        this.title = title;
        this.type = type.equals("M") ? "movies" : "tvshows"; // Set the type based on the title

        // Set the frame properties
        setTitle(title);
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the layout to null for absolute positioning
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        setContentPane(mainPanel);

        // Add movie poster
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel thumbnail = new JLabel(imageIcon);
        thumbnail.setBounds(10, 10, 200, 300);
        mainPanel.add(thumbnail);

        // Add movie details
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(220, 10, 150, 30);
        mainPanel.add(titleLabel);

        JButton addToList = new JButton("Add to Your List");
        addToList.setBackground(Color.decode("#e50914"));
        addToList.setBounds(400, 10, 150, 30);
        mainPanel.add(addToList);

        JLabel descriptionArea = new JLabel(description);
        descriptionArea.setFont(new Font("Serif", Font.PLAIN, 16));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBounds(220, 50, 350, 100);
        mainPanel.add(descriptionArea);

        // Fetch the rating from the database
        float fetchedRating = fetchRatingFromDatabase();

        ratingLabel = new JLabel("Rating: " + fetchedRating);
        ratingLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        ratingLabel.setForeground(Color.WHITE);
        ratingLabel.setBounds(220, 160, 200, 30);
        mainPanel.add(ratingLabel);

        JLabel rate = new JLabel("Rate (Out of 10): ");
        rate.setFont(new Font("Serif", Font.PLAIN, 16));
        rate.setForeground(Color.WHITE);
        rate.setBounds(220, 200, 150, 30);
        mainPanel.add(rate);

        JTextField rateText = new JTextField();
        rateText.setBounds(340, 205, 50, 25);
        mainPanel.add(rateText);

        JButton submitRating = new JButton("Submit");
        submitRating.setBackground(Color.decode("#e50914"));
        submitRating.setBounds(410, 205, 100, 25);
        mainPanel.add(submitRating);

        JButton watchTrailer = new JButton("Watch Trailer");
        watchTrailer.setBackground(Color.decode("#e50914"));
        watchTrailer.setBounds(220, 245, 150, 50);
        mainPanel.add(watchTrailer);

        JButton playButton = new JButton(type.equals("M") ? "Play Movie" : "Play Episode");
        playButton.setBackground(Color.decode("#e50914"));
        playButton.setBounds(400, 245, 150, 50);
        mainPanel.add(playButton);

        // Action listeners for the buttons
        watchTrailer.addActionListener(e -> playVideo("assets/video/NEXTGENFLIX.mp4"));

        playButton.addActionListener(e -> playVideo("assets/video/NEXTGENFLIX.mp4"));

        submitRating.addActionListener(e -> {
            String ratingText = rateText.getText();
            if (ratingText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter something", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    float ratingValue = Float.parseFloat(ratingText);
                    if (ratingValue >= 0 && ratingValue <= 10) {
                        updateRatingInDatabase(ratingValue);
                        ratingLabel.setText("Rating: " + ratingValue); // Update the rating label
                        JOptionPane.showMessageDialog(this, "Rated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Please enter a rating between 0 and 10", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid decimal number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addToList.addActionListener(e -> {
            updateWatchlistStatusInDatabase(true);
            JOptionPane.showMessageDialog(this, "Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        // Make the frame visible
        setVisible(true);
    }

    private void playVideo(String videoPath) {
        try {
            File videoFile = new File(videoPath);
            if (videoFile.exists()) {
                Desktop.getDesktop().open(videoFile);
            } else {
                JOptionPane.showMessageDialog(this, "Video file not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to play video.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private float fetchRatingFromDatabase() {
        String query = "SELECT rating FROM " + type + " WHERE title = ?";
        float ratingValue = 0.0f;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery(); 
            if (resultSet.next()) {
                ratingValue = resultSet.getFloat("rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratingValue;
    }

    private void updateRatingInDatabase(float ratingValue) {
        String query = "UPDATE " + type + " SET rating = ? WHERE title = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, ratingValue);
            statement.setString(2, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateWatchlistStatusInDatabase(boolean isInWatchlist) {
        String query = "UPDATE " + type + " SET is_in_watchlist = ? WHERE title = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, isInWatchlist);
            statement.setString(2, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
