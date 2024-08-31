package src;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class MyListFrame extends JFrame {
    private JPanel contentRow;

    public MyListFrame() {
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

        // Create the content panel with movies and TV shows sections
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createSectionWithTitleAndSlider("Movies", getMoviesFromDatabase()));

        contentPanel.add(createSectionWithTitleAndSlider("TV Shows", getTVShowsFromDatabase()));

        // Add the content panel to the main panel
        JScrollPane scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createSectionWithTitleAndSlider(String title, String[][] data) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Serif", Font.BOLD, 24));
        sectionTitle.setForeground(Color.BLACK);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        sectionPanel.add(sectionTitle);

        if (title.equals("Movies")) {
            contentRow = createContentRowForMovies(data);
        } else {
            contentRow = createContentRowForTVShows(data);
        }

        JScrollPane sliderScrollPane = new JScrollPane(contentRow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sectionPanel.add(sliderScrollPane,BorderLayout.CENTER);

        return sectionPanel;
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

        JButton home = new JButton("Home");
        home.setFont(new Font("Serif", Font.PLAIN, 18));
        home.setForeground(Color.WHITE);
        home.setBackground(Color.BLACK);
        home.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        home.addActionListener(e -> showDashBoardFrame());
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(home);

        JButton movies = new JButton("Movies");
        movies.setFont(new Font("Serif", Font.PLAIN, 18));
        movies.setForeground(Color.WHITE);
        movies.setBackground(Color.BLACK);
        movies.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        movies.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        movies.addActionListener(e -> showMovieFrame());
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(movies);

        JButton tvShow = new JButton("TV Shows");
        tvShow.setFont(new Font("Serif", Font.PLAIN, 18));
        tvShow.setForeground(Color.WHITE);
        tvShow.setBackground(Color.BLACK);
        tvShow.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        tvShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tvShow.addActionListener(e -> showTVShowsFrame());
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(tvShow);

        JLabel myList = new JLabel("My List");
        myList.setFont(new Font("Serif", Font.PLAIN, 18));
        myList.setForeground(Color.RED);
        myList.setBackground(Color.BLACK);
        myList.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        myList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(myList);

        return navBar;
    }

    private void showDashBoardFrame() {
        this.setVisible(false);
        NetflixDashboard dashboard = new NetflixDashboard();
        dashboard.setVisible(true);
    }

    private void showMovieFrame() {
        this.setVisible(false);
        MovieFrame movie = new MovieFrame();
        movie.setVisible(true);
    }

    private void showTVShowsFrame() {
        this.setVisible(false);
        TVShowFrame tvshow = new TVShowFrame();
        tvshow.setVisible(true);
    }

    private JPanel createContentRowForMovies(String[][] data) {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        // Load all images in the row
        for (String[] movieData : data) {
            String title = movieData[0];
            String imagePath = movieData[1];
            String description = movieData[2];
            JPanel moviePanel = NetflixDashboard.createMoviePanel("M", title, imagePath, description);
            contentRow.add(moviePanel);
        }

        return contentRow;
    }

    private JPanel createContentRowForTVShows(String[][] data) {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        // Load all images in the row
        for (String[] tvShowData : data) {
            String title = tvShowData[0];
            String imagePath = tvShowData[1];
            String description = tvShowData[2];
            JPanel tvShowPanel = NetflixDashboard.createMoviePanel("", title, imagePath, description);
            contentRow.add(tvShowPanel);
        }

        return contentRow;
    }

    private String[][] getMoviesFromDatabase() {
        String query = "SELECT title, image_path, description FROM movies WHERE is_in_watchlist = true";
        return fetchDataFromDatabase(query);
    }

    private String[][] getTVShowsFromDatabase() {
        String query = "SELECT title, image_path, description FROM tvshows WHERE is_in_watchlist = true";
        return fetchDataFromDatabase(query);
    }

    private String[][] fetchDataFromDatabase(String query) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
    
            // Use a temporary list to store data
            java.util.List<String[]> dataList = new java.util.ArrayList<>();
    
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String imagePath = resultSet.getString("image_path");
                String description = resultSet.getString("description");
                dataList.add(new String[]{title, imagePath, description});
            }
    
            // Convert the list to a 2D array
            String[][] data = new String[dataList.size()][3];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }
    
            return data;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0][0];
        }
    }
    
}
