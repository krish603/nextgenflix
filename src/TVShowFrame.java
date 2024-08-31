package src;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class TVShowFrame extends JFrame {

    private JTextField searchField;
    private JPanel contentGrid;
    private LinkedList<TVShow> tvShowList = new LinkedList<>();

    public TVShowFrame() {
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

        // Create a top panel to hold both the search bar and the grid
        JPanel topPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.CENTER);

        // Create the search bar
        searchField = new JTextField(20);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterTVShows();
            }
        });

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(searchField);
        topPanel.add(searchPanel, BorderLayout.NORTH);

        // Create the grid of TV show thumbnails
        contentGrid = createContentGrid();
        JScrollPane scrollPane = new JScrollPane(contentGrid);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling
        topPanel.add(scrollPane, BorderLayout.CENTER);

        // Load TV shows into LinkedList
        loadTVShows();
        displayTVShows();
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
        home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        home.addActionListener(e -> showDashBoardFrame());
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(home);

        JButton Movies = new JButton("Movies");
        Movies.setFont(new Font("Serif", Font.PLAIN, 18));
        Movies.setForeground(Color.WHITE);
        Movies.setBackground(Color.BLACK);
        Movies.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Movies.addActionListener(e -> showMovieFrame());
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(Movies);

        JLabel TVShow = new JLabel("TV Shows");
        TVShow.setFont(new Font("Serif", Font.PLAIN, 18));
        TVShow.setForeground(Color.RED);
        TVShow.setBackground(Color.BLACK);
        TVShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(TVShow);

        JButton MyList = new JButton("My List");
        MyList.setFont(new Font("Serif", Font.PLAIN, 18));
        MyList.setForeground(Color.WHITE);
        MyList.setBackground(Color.BLACK);
        MyList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        MyList.addActionListener(e -> showListFrame());
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(MyList);

        return navBar;
    }

    private void showDashBoardFrame() {
        this.setVisible(false);
        NetflixDashboard dashboard = new NetflixDashboard();
        dashboard.setVisible(true);
    }

    private void showListFrame() {
        this.setVisible(false);
        MyListFrame list = new MyListFrame();
        list.setVisible(true);
    }

    private void showMovieFrame() {
        this.setVisible(false);
        MovieFrame movie = new MovieFrame();
        movie.setVisible(true);
    }

    private JPanel createContentGrid() {
        contentGrid = new JPanel(new GridLayout(4, 5, 10, 10));
        contentGrid.setBackground(Color.DARK_GRAY);
        contentGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return contentGrid;
    }

    private void loadTVShows() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title, description, image_path FROM tvshows");

            while (rs.next()) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");

                // Add each TV show to the LinkedList
                tvShowList.add(new TVShow(title, description, imagePath));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayTVShows() {
        contentGrid.removeAll();
        for (TVShow tvShow : tvShowList) {
            JPanel moviePanel = NetflixDashboard.createMoviePanel("T", tvShow.getTitle(), tvShow.getImagePath(), tvShow.getDescription());
            contentGrid.add(moviePanel);
        }
        contentGrid.revalidate();
        contentGrid.repaint();
    }

    private void filterTVShows() {
        String searchText = searchField.getText().toLowerCase();
        contentGrid.removeAll();

        for (TVShow tvShow : tvShowList) {
            if (tvShow.getTitle().toLowerCase().contains(searchText)) {
                JPanel moviePanel = NetflixDashboard.createMoviePanel("T", tvShow.getTitle(), tvShow.getImagePath(), tvShow.getDescription());
                contentGrid.add(moviePanel);
            }
        }

        contentGrid.revalidate();
        contentGrid.repaint();
    }

    // TVShow class as a simple data structure for LinkedList
    class TVShow {
        private String title;
        private String description;
        private String imagePath;

        public TVShow(String title, String description, String imagePath) {
            this.title = title;
            this.description = description;
            this.imagePath = imagePath;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImagePath() {
            return imagePath;
        }
    }
}