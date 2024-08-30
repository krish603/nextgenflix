package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class NetflixDashboard extends JFrame {

    public NetflixDashboard() {
        // Set the frame properties
        setTitle("Netflix Dashboard");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Create the top navigation bar
        JPanel navBar = createNavBar();
        mainPanel.add(navBar, BorderLayout.NORTH);

        // Create the grid of movie thumbnails
        JPanel contentGrid = createContentGrid();
        JScrollPane scrollPane = new JScrollPane(contentGrid);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setBackground(Color.BLACK);
        navBar.setLayout(new BoxLayout(navBar, BoxLayout.X_AXIS));

        // Add Netflix logo (as text here, but you can use an ImageIcon)
        JLabel logoLabel = new JLabel("Nextgenflix");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));
        logoLabel.setForeground(Color.RED);
        navBar.add(Box.createRigidArea(new Dimension(20, 0))); // Spacer
        navBar.add(logoLabel);

        JLabel home = new JLabel("Home");
        home.setFont(new Font("Serif", Font.PLAIN, 18));
        home.setForeground(Color.RED);
        home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        home.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(home);

        // Add navigation links
        String[] navItems = {"TV Shows", "Movies", "My List"};
        for (String item : navItems) {
            JLabel navItem = new JLabel(item);
            navItem.setFont(new Font("Serif", Font.PLAIN, 18));
            navItem.setForeground(Color.WHITE);
            navItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            navItem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            navBar.add(Box.createHorizontalGlue()); // Push items to the right
            navBar.add(navItem);
        }

        return navBar;
    }

    private JPanel createContentGrid() {
        JPanel contentGrid = new JPanel(new GridLayout(7, 5, 10, 10)); // 3 rows, 5 columns, 10px gaps
        contentGrid.setBackground(Color.DARK_GRAY);
        contentGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        // Example movie details (title, image path, description)
        String[] imagePaths = {
            "assets/img/Posters/12_2_929c555a-bd2c-4d1f-b1c8-9d0cf2e88615.png",
            "assets/img/Posters/29_2_362c9118-1045-4fcb-b4ad-d8d83831da70.png",
            "assets/img/Posters/34_2_2fc257d7-11e8-4ffe-87a1-bade8b297d88.png",
            "assets/img/Posters/Oppenheimer.png",
            "assets/img/Posters/61jBc4kTVSL._AC_UF1000,1000_QL80_.png",
            "assets/img/Posters/71FbCxxC4zL._AC_UF894,1000_QL80_.png",
            "assets/img/Posters/action-movie-poster-template-design-0f5fff6262fdefb855e3a9a3f0fdd361_screen.png",
            "assets/img/Posters/adventure-movie-poster-template-design-7b13ea2ab6f64c1ec9e1bb473f345547_screen.png",
            "assets/img/Posters/custom-made-hollywood-movie-posters-2.png",
            "assets/img/Posters/e5751316c3b567b2e17c914c5c51c85d.png",
            "assets/img/Posters/il_570xN.2835655237_4h8m.png",
            "assets/img/Posters/l_434409_bab40b66.png",
            "assets/img/Posters/lady-vengeance-b.png",
            "assets/img/Posters/MOVCJ3054__57379.png",
            "assets/img/Posters/movie-poster-template-design-21a1c803fe4ff4b858de24f5c91ec57f_screen.png",
            "assets/img/Posters/PG2_Copy_4bef8a47-f239-44aa-97ba-aa0da4b4de38.png",
            "assets/img/Posters/PG17_2_Copy.png",
            "assets/img/Posters/PG100_Copy_3c7a4544-1585-46b3-820d-b6d036653aed.png",
            "assets/img/Posters/PG107_Copy_cef8be77-761a-4d03-9dfc-91f3a78d5635.png",
            "assets/img/Posters/PG159_3_75fc1279-996b-4f8d-a1b7-3dfa0e32277a.png",
            "assets/img/Posters/pg1013.png",
            "assets/img/Posters/pg1599.png",
            "assets/img/Posters/pg1678.png",
            "assets/img/Posters/PGMG080_Copy.png",
            "assets/img/Posters/s-l1200 (1).png",
            "assets/img/Posters/s-l1200.png",
            "assets/img/Posters/Shawshank_Redemption_-_Hope_Is_Good_Thing_Copy.png",
            "assets/img/Posters/small-hollywood-movie-poster-blade-runner-2049-ridley-scott-original-imaf3qvx88xenydd.png",
            "assets/img/Posters/unnamed.png",
            "assets/img/Posters/Zindagi_Na_Milegi_Dobara_Minimal-NGPS2076.png",
            "assets/img/Posters/81jxal5C+uL._AC_UF1000,1000_QL80_.jpg",
            "assets/img/Posters/c104f1bfed20481f35bc96cb9addc940_240x360_crop_center.progressive.png",
            "assets/img/Posters/movie-poster-design-template_841014-16988.png"
        };

        // Add placeholder panels for each movie
        for (int i = 1; i <= imagePaths.length; i++) {
            String title = "Movie " + i;
            String description = "This is the description for Movie " + i + ".";
            JPanel placeholderPanel = createPlaceholderPanel();
            contentGrid.add(placeholderPanel);
            
            // Load movie panels asynchronously
            new MoviePanelLoader(placeholderPanel, title, imagePaths[i - 1], description).execute();
        }

        return contentGrid;
    }

    private JPanel createPlaceholderPanel() {
        JPanel placeholderPanel = new JPanel();
        placeholderPanel.setPreferredSize(new Dimension(200, 300));
        placeholderPanel.setBackground(Color.DARK_GRAY);
        return placeholderPanel;
    }

    // SwingWorker to load movie panels asynchronously
    private class MoviePanelLoader extends SwingWorker<JPanel, Void> {
        private JPanel placeholderPanel;
        private String title;
        private String imagePath;
        private String description;

        public MoviePanelLoader(JPanel placeholderPanel, String title, String imagePath, String description) {
            this.placeholderPanel = placeholderPanel;
            this.title = title;
            this.imagePath = imagePath;
            this.description = description;
        }

        @Override
        protected JPanel doInBackground() {
            return createMoviePanel(title, imagePath, description);
        }

        @Override
        protected void done() {
            try {
                JPanel moviePanel = get();
                placeholderPanel.removeAll();
                placeholderPanel.setLayout(new BorderLayout());
                placeholderPanel.add(moviePanel);
                placeholderPanel.revalidate();
                placeholderPanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private JPanel createMoviePanel(String title, String imagePath, String description) {
        JPanel moviePanel = new JPanel(new BorderLayout());
        moviePanel.setBackground(Color.BLACK);
        moviePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Load the image
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        // Create a label with the image icon
        JLabel thumbnail = new JLabel(imageIcon);
        thumbnail.setPreferredSize(new Dimension(200, 300)); // Adjust size as needed
        moviePanel.add(thumbnail, BorderLayout.CENTER);

        // Add the movie title below the thumbnail
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        titleLabel.setForeground(Color.WHITE);
        moviePanel.add(titleLabel, BorderLayout.SOUTH);

        // Add a mouse listener to the thumbnail
        thumbnail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        thumbnail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new MovieDetailFrame(title, imagePath, description); // Create new MovieDetailFrame here with the movie detailsnew MovieDetailFrame(title, imagePath, description);
            }
        });

        return moviePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NetflixDashboard::new);
    }
}
