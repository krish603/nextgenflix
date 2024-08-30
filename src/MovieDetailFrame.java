package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MovieDetailFrame extends JFrame {

    public MovieDetailFrame(String title, String imagePath, String description) {
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

        JLabel rating = new JLabel("Rating : 9.5");
        rating.setFont(new Font("Serif", Font.PLAIN, 16));
        rating.setForeground(Color.WHITE);
        rating.setBounds(220, 160, 200, 30);
        mainPanel.add(rating);

        JLabel rate = new JLabel("Rate (Out of 10) : ");
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

        JButton playButton;
        if (title.contains("Movie")) {
            playButton = new JButton("Play Movie");
        } else {
            playButton = new JButton("Play Episode");
        }
        playButton.setBackground(Color.decode("#e50914"));
        playButton.setBounds(400, 245, 150, 50);
        mainPanel.add(playButton);

        // Action listeners for the buttons
        watchTrailer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playVideo("assets/video/NEXTGENFLIX.mp4");
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playVideo("assets/video/NEXTGENFLIX.mp4");
            }
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
}