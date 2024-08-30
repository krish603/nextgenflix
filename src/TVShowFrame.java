package src;

import java.awt.*;

import javax.swing.*;

public class TVShowFrame extends JFrame{
 
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

        // Create the grid of TV show thumbnails
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
        home.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(home);

        JButton Movies = new JButton("Movies");
        Movies.setFont(new Font("Serif", Font.PLAIN, 18));
        Movies.setForeground(Color.WHITE);
        Movies.setBackground(Color.BLACK);
        Movies.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        Movies.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Movies.addActionListener(e -> showMovieFrame());
        Movies.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(Movies);

        JLabel TVShow = new JLabel("TV Shows");
        TVShow.setFont(new Font("Serif", Font.PLAIN, 18));
        TVShow.setForeground(Color.RED);
        TVShow.setBackground(Color.BLACK);
        TVShow.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        TVShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        TVShow.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(TVShow);

        JButton MyList = new JButton("My List");
        MyList.setFont(new Font("Serif", Font.PLAIN, 18));
        MyList.setForeground(Color.WHITE);
        MyList.setBackground(Color.BLACK);
        MyList.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        MyList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        MyList.addActionListener(e -> showListFrame());
        MyList.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
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
        JPanel contentGrid = new JPanel(new GridLayout(3, 5, 10, 10)); // 3 rows, 5 columns, 10px gaps
        contentGrid.setBackground(Color.DARK_GRAY);
        contentGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Example movie details (title, image path, description)
        String[] imagePaths = {
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
            "add movies here",
        };

        String[] descriptions =new String[35];

        for (int i = 1 ;i < 35;i++){
            descriptions[i]="This is the description for TV Show "+i+".";

        }

        int count = 0;
        for (String imagePath : imagePaths) {
            count++;
            String title = "TV Show "+count;
            String description = "This is the description for " + title + ".";
            JPanel moviePanel = NetflixDashboard.createMoviePanel(title, imagePath, description);
            contentGrid.add(moviePanel);
        }

        return contentGrid;
    }

}
