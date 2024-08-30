package src;

import java.awt.*;

import javax.swing.*;

public class MyListFrame extends JFrame{
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

        // Create the content panel with movies, TV shows, and Top 10 sections
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createSectionWithTitleAndSlider("Movies", getMovieImagePaths()));

        contentPanel.add(createSectionWithTitleAndSlider("TV Shows", getTVShowImagePaths()));

        // Add the content panel to the main panel
        JScrollPane scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createSectionWithTitleAndSlider(String title, String[] imagePaths) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Serif", Font.BOLD, 24));
        sectionTitle.setForeground(Color.BLACK);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        sectionPanel.add(sectionTitle);

        if (title.equals("Movies")) {
            contentRow = createContentRowForMovies(imagePaths);
        } else {
            contentRow = createContentRowForTVShows(imagePaths);
        }


        JScrollPane sliderScrollPane = new JScrollPane(contentRow, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sliderScrollPane.getHorizontalScrollBar().setUnitIncrement(16); // Set horizontal scroll speed
        sectionPanel.add(sliderScrollPane);

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

        JButton TVShow = new JButton("TV Shows");
        TVShow.setFont(new Font("Serif", Font.PLAIN, 18));
        TVShow.setForeground(Color.WHITE);
        TVShow.setBackground(Color.BLACK);
        TVShow.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        TVShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        TVShow.addActionListener(e -> showTVShowsFrame());
        TVShow.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        navBar.add(Box.createHorizontalGlue()); // Push items to the right
        navBar.add(TVShow);

        JLabel MyList = new JLabel("My List");
        MyList.setFont(new Font("Serif", Font.PLAIN, 18));
        MyList.setForeground(Color.RED);
        MyList.setBackground(Color.BLACK);
        MyList.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        MyList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

    private JPanel createContentRowForMovies(String[] imagePaths) {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        int count = 0;
        // Load all images in the row
        for (String imagePath : imagePaths) {
            count++;
            String title = "Movie "+count;
            String description = "This is the description for " + title + ".";
            JPanel moviePanel = NetflixDashboard.createMoviePanel(title, imagePath, description);
            contentRow.add(moviePanel);
        }

        return contentRow;
    }

    private JPanel createContentRowForTVShows(String[] imagePaths) {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        int count = 0;
        // Load all images in the row
        for (String imagePath : imagePaths) {
            count++;
            String title = "TV Show "+count;
            String description = "This is the description for " + title + ".";
            JPanel moviePanel = NetflixDashboard.createMoviePanel(title, imagePath, description);
            contentRow.add(moviePanel);
        }

        return contentRow;
    }

    // Dummy data for movie image paths
    private String[] getMovieImagePaths() {
        return new String[]{
            "assets/img/Posters/12_2_929c555a-bd2c-4d1f-b1c8-9d0cf2e88615.png",
            "assets/img/Posters/29_2_362c9118-1045-4fcb-b4ad-d8d83831da70.png",
            "assets/img/Posters/34_2_2fc257d7-11e8-4ffe-87a1-bade8b297d88.png",
            "assets/img/Posters/Oppenheimer.png",
            "assets/img/Posters/61jBc4kTVSL._AC_UF1000,1000_QL80_.png",
            "assets/img/Posters/71FbCxxC4zL._AC_UF894,1000_QL80_.png",
            // "assets/img/Posters/action-movie-poster-template-design-0f5fff6262fdefb855e3a9a3f0fdd361_screen.png",
            // "assets/img/Posters/adventure-movie-poster-template-design-7b13ea2ab6f64c1ec9e1bb473f345547_screen.png",
            // "assets/img/Posters/f7eb970d-97f6-420e-9964-547aaae1898b-min.png",
            // "assets/img/Posters/ff8be05a431a19f15e66ac789b1a99b0.png",
            // "assets/img/Posters/gg.png",
            // "assets/img/Posters/ggggggg.png",
            // "assets/img/Posters/mn-min.png",
            // "assets/img/Posters/modern-movie-poster-template-design-dcab0ce86a7861c1c9b8d2c5d8f55e0a_screen.png",
            // "assets/img/Posters/nollywood-movie-posters-nigerian.png",
            // "assets/img/Posters/old-movie-poster.png"
        };
    }

    // Dummy data for TV show image paths
    private String[] getTVShowImagePaths() {
        return new String[]{
            "assets/img/Posters/tvshow1.png",
            "assets/img/Posters/tvshow2.png",
            "assets/img/Posters/tvshow3.png",
            "assets/img/Posters/tvshow4.png",
            "assets/img/Posters/tvshow5.png",
            "assets/img/Posters/tvshow6.png",
            // "assets/img/Posters/tvshow7.png",
            // "assets/img/Posters/tvshow8.png",
            // "assets/img/Posters/tvshow9.png",
            // "assets/img/Posters/tvshow10.png",
            // "assets/img/Posters/tvshow11.png",
            // "assets/img/Posters/tvshow12.png",
            // "assets/img/Posters/tvshow13.png",
            // "assets/img/Posters/tvshow14.png",
            // "assets/img/Posters/tvshow15.png"
        };
    }
}
