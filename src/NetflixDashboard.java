package src;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NetflixDashboard extends JFrame {
    private JPanel contentRow;
    public JPanel sectionPanel;

    public NetflixDashboard() {
        setTitle("Nextgenflix");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Create the top navigation bar
        JPanel navBar = createNavBar();
        mainPanel.add(navBar, BorderLayout.NORTH);

        // Create the content panel with movies, TV shows, and Top 10 sections
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createSectionWithTitleAndSlider("Movies", getMovies()));

        contentPanel.add(createSectionWithTitleAndSlider("TV Shows", getTVShows()));

        contentPanel.add(createSectionWithTitleAndSlider("Best Movies", getBestMovies()));

        contentPanel.add(createSectionWithTitleAndSlider("Best TV Shows", getBestTvShows()));

        // Add the content panel to the main panel
        JScrollPane scrollPane1 = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane1, BorderLayout.CENTER);

        JScrollPane scrollPane2 = new JScrollPane(sectionPanel , JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPanel.add(scrollPane2);

        // Make the frame visible
        setVisible(true);
    }

    public JPanel createNavBar() {
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
    
        // Create Movies button
        JButton moviesButton = new JButton("Movies");
        moviesButton.setFont(new Font("Serif", Font.PLAIN, 18));
        moviesButton.setForeground(Color.WHITE);
        moviesButton.setBackground(Color.BLACK);
        moviesButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        moviesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        moviesButton.addActionListener(e -> showMovieFrame()); // Correctly add ActionListener here
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(moviesButton);
    
        // Create TV Shows button
        JButton tvShowsButton = new JButton("TV Shows");
        tvShowsButton.setFont(new Font("Serif", Font.PLAIN, 18));
        tvShowsButton.setForeground(Color.WHITE);
        tvShowsButton.setBackground(Color.BLACK);
        tvShowsButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        tvShowsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tvShowsButton.addActionListener(e -> showTVShowsFrame()); // Correctly add ActionListener here
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(tvShowsButton);
    
        // Create My List button
        JButton myListButton = new JButton("My List");
        myListButton.setFont(new Font("Serif", Font.PLAIN, 18));
        myListButton.setForeground(Color.WHITE);
        myListButton.setBackground(Color.BLACK);
        myListButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        myListButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myListButton.addActionListener(e -> showListFrame()); // Correctly add ActionListener here
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(myListButton);
    
        return navBar;
    }
    

    private void showListFrame() {
        this.setVisible(false);
        MyListFrame list = new MyListFrame();
        list.setVisible(true);
    }

    private void showTVShowsFrame() {
        this.setVisible(false);
        TVShowFrame tvshow = new TVShowFrame();
        tvshow.setVisible(true);
    }

    private void showMovieFrame() {
        this.setVisible(false);
        MovieFrame movie = new MovieFrame();
        movie.setVisible(true);
    }

    private JPanel createSectionWithTitleAndSlider(String title, MediaItem[] imagePaths) {
        sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Serif", Font.BOLD, 24));
        sectionTitle.setForeground(Color.BLACK);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        sectionPanel.add(sectionTitle);

        if (title.equals("Movies")) {
            contentRow = createContentRowForMovies();
        } else if (title.equals("TV Shows")) {
            contentRow = createContentRowForTVShows();
        } else if (title.equals("Best Movies")) {
            contentRow = createContentRowForBestMovies();
        } else {
            contentRow = createContentRowForBestTVShow();
        }


        JScrollPane sliderScrollPane = new JScrollPane(contentRow, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sliderScrollPane.getHorizontalScrollBar().setUnitIncrement(16); // Set horizontal scroll speed
        sectionPanel.add(sliderScrollPane);

        return sectionPanel;
    }

    private JPanel createContentRowForMovies() {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title, description, image_path FROM movies");

            while (rs.next()) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");

                JPanel moviePanel = NetflixDashboard.createMoviePanel("M", title, imagePath, description);
                contentRow.add(moviePanel);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentRow;
    }

    private JPanel createContentRowForTVShows() {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title, description, image_path FROM tvshows");

            while (rs.next()) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");

                JPanel moviePanel = NetflixDashboard.createMoviePanel("", title, imagePath, description);
                contentRow.add(moviePanel);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentRow;
    }

    private JPanel createContentRowForBestMovies() {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);
    
        MediaItem[] bestMovies = getBestMovies(); // Fetch the best movies
    
        for (MediaItem movie : bestMovies) {
            if (movie != null) {
                JPanel moviePanel = createMoviePanel("M", movie.title, movie.imagePath, movie.description);
                contentRow.add(moviePanel);
            }
        }
    
        return contentRow;
    }
    

    private JPanel createContentRowForBestTVShow() {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);
    
        MediaItem[] bestTvShows = getBestTvShows(); // Fetch the best TV shows
    
        for (MediaItem tvShow : bestTvShows) {
            if (tvShow != null) {
                JPanel moviePanel = createMoviePanel("", tvShow.title, tvShow.imagePath, tvShow.description);
                contentRow.add(moviePanel);
            }
        }
    
        return contentRow;
    }
    

    // public JPanel createPlaceholderPanel() {
    //     JPanel placeholderPanel = new JPanel();
    //     placeholderPanel.setPreferredSize(new Dimension(200, 300));
    //     placeholderPanel.setBackground(Color.DARK_GRAY);
    //     return placeholderPanel;
    // }

    // SwingWorker to load movie panels asynchronously
    // public class MoviePanelLoader extends SwingWorker<JPanel, Void> {
    //     private JPanel placeholderPanel;
    //     private String title;
    //     private String imagePath;
    //     private String description;

    //     public MoviePanelLoader(JPanel placeholderPanel, String title, String imagePath, String description) {
    //         this.placeholderPanel = placeholderPanel;
    //         this.title = title;
    //         this.imagePath = imagePath;
    //         this.description = description;
    //     }

    //     @Override
    //     protected JPanel doInBackground() {
    //         return createMoviePanel(title, imagePath, description);
    //     }

    //     @Override
    //     protected void done() {
    //         try {
    //             JPanel moviePanel = get();
    //             placeholderPanel.removeAll();
    //             placeholderPanel.setLayout(new BorderLayout());
    //             placeholderPanel.add(moviePanel);
    //             placeholderPanel.revalidate();
    //             placeholderPanel.repaint();
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }


    static JPanel createMoviePanel(String type, String title, String imagePath, String description) {
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

        // Add the title below the thumbnail
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        titleLabel.setForeground(Color.WHITE);
        moviePanel.add(titleLabel, BorderLayout.SOUTH);

        // Add a mouse listener to the thumbnail
        thumbnail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        thumbnail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new MovieDetailFrame(type,title, imagePath, description); // Create new MovieDetailFrame here with the movie details
            }
        });

        return moviePanel;
    }

    private MediaItem[] getMovies() {
        MediaItem[] movies = new MediaItem[21]; // Array to store movie details
    
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
    
            // SQL query to get the top 10 movies based on rating
            String query = "SELECT title, image_path, description FROM movies";
            ResultSet rs = stmt.executeQuery(query);
    
            int index = 0;
            while (rs.next() && index<21) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");
                movies[index] = new MediaItem(title, imagePath, description);
                index++;
            }
    
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return movies;
    }

    private MediaItem[] getTVShows() {
        MediaItem[] tvShows = new MediaItem[21]; // Array to store movie details
    
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
    
            String query = "SELECT title, image_path, description FROM tvshows";
            ResultSet rs = stmt.executeQuery(query);
    
            int index = 0;
            while (rs.next() && index < 21) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");
                tvShows[index] = new MediaItem(title, imagePath, description);
                index++;
            }
    
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return tvShows;
    }

    private MediaItem[] getBestMovies() {
        MediaItem[] bestMovies = new MediaItem[10]; // Array to store movie details
    
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
    
            // SQL query to get the top 10 movies based on rating
            String query = "SELECT title, image_path, description FROM movies ORDER BY rating DESC LIMIT 10";
            ResultSet rs = stmt.executeQuery(query);
    
            int index = 0;
            while (rs.next() && index < 10) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");
                bestMovies[index] = new MediaItem(title, imagePath, description);
                index++;
            }
    
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return bestMovies;
    }
    
    
    private MediaItem[] getBestTvShows() {
        MediaItem[] bestTvShows = new MediaItem[10]; // Array to store TV show details
    
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
    
            // SQL query to get the top 10 TV shows based on rating
            String query = "SELECT title, image_path, description FROM tvshows ORDER BY rating DESC LIMIT 10";
            ResultSet rs = stmt.executeQuery(query);
    
            int index = 0;
            while (rs.next() && index < 10) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");
                bestTvShows[index] = new MediaItem(title, imagePath, description);
                index++;
            }
    
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return bestTvShows;
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NetflixDashboard::new);
    }
}

class MediaItem {
    String title;
    String imagePath;
    String description;

    MediaItem(String title, String imagePath, String description) {
        this.title = title;
        this.imagePath = imagePath;
        this.description = description;
    }
}
