package src;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class MovieFrame extends JFrame {
 
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

        // Create the grid of movie thumbnails
        JPanel contentGrid = createContentGrid();
        JScrollPane scrollPane = new JScrollPane(contentGrid);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling
        mainPanel.add(scrollPane, BorderLayout.CENTER);
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
        NetflixDashboard dashboard = new NetflixDashboard();
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
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextgenflix", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title, description, image_path FROM movies");

            while (rs.next()) {
                String title = rs.getString("title");
                String imagePath = rs.getString("image_path");
                String description = rs.getString("description");

                JPanel moviePanel = NetflixDashboard.createMoviePanel("M", title, imagePath, description);
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

    // public JPanel createPlaceholderPanel() {
    //     JPanel placeholderPanel = new JPanel();
    //     placeholderPanel.setPreferredSize(new Dimension(200, 300));
    //     placeholderPanel.setBackground(Color.DARK_GRAY);
    //     return placeholderPanel;
    // }

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
    //         return NetflixDashboard.createMoviePanel(title, imagePath, description);
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
}
