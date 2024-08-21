package src;

import javax.swing.*;
import java.awt.*;

public class MovieDetailFrame extends JFrame {

    public MovieDetailFrame(String title, String imagePath, String description) {
        // Set the frame properties
        setTitle(title);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Add movie poster
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel thumbnail = new JLabel(imageIcon);
        mainPanel.add(thumbnail, BorderLayout.WEST);

        // Add movie details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        detailsPanel.add(titleLabel);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setFont(new Font("Serif", Font.PLAIN, 16));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        detailsPanel.add(descriptionArea);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
