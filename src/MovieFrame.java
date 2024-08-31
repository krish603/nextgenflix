package src;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;

public class MovieFrame extends JFrame {

    private JTextField searchField;
    private JPanel contentGrid;
    private JScrollPane scrollPane;

    public MovieFrame() {
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

        // Create a panel to hold both the search bar and the grid
        JPanel topPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.CENTER);

        // Create the search bar
        searchField = new JTextField(20);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterMovies();
            }
        });

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(searchField);
        topPanel.add(searchPanel, BorderLayout.NORTH);

        // Create the grid of movie thumbnails
        contentGrid = createContentGrid();
        scrollPane = new JScrollPane(contentGrid);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling
        topPanel.add(scrollPane, BorderLayout.CENTER);
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

        JLabel Movies = new JLabel("Movies");
        Movies.setFont(new Font("Serif", Font.PLAIN, 18));
        Movies.setForeground(Color.RED);
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(Movies);

        JButton TVShow = new JButton("TV Shows");
        TVShow.setFont(new Font("Serif", Font.PLAIN, 18));
        TVShow.setForeground(Color.WHITE);
        TVShow.setBackground(Color.BLACK);
        TVShow.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        TVShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        TVShow.addActionListener(e -> showTVShowsFrame());
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
        NextgenflixDashboard dashboard = new NextgenflixDashboard();
        dashboard.setVisible(true);
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

    private JPanel createContentGrid() {
        JPanel contentGrid = new JPanel(new GridLayout(4, 5, 10, 10));
        contentGrid.setBackground(Color.DARK_GRAY);
        contentGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title, description, image_path FROM movies");

            while (rs.next()) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");

                JPanel moviePanel = NextgenflixDashboard.createMoviePanel("M", title, imagePath, description);
                contentGrid.add(moviePanel);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentGrid;
    }

    private void filterMovies() {
        String searchText = searchField.getText().toLowerCase();
        contentGrid.removeAll();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
            String query = "SELECT title, description, image_path FROM movies WHERE LOWER(title) LIKE '%" + searchText + "%'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");

                JPanel moviePanel = NextgenflixDashboard.createMoviePanel("M", title, imagePath, description);
                contentGrid.add(moviePanel);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        contentGrid.revalidate();
        contentGrid.repaint();
    }
}
