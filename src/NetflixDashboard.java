package src;

import javax.swing.*;
import java.awt.*;

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

        contentPanel.add(createSectionWithTitleAndSlider("Movies", getMovieImagePaths()));

        contentPanel.add(createSectionWithTitleAndSlider("TV Shows", getTVShowImagePaths()));

        contentPanel.add(createSectionWithTitleAndSlider("Best Movies", getBestMoviesImagePaths()));

        contentPanel.add(createSectionWithTitleAndSlider("Best TV Shows", getBestTvShowsImagePaths()));

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

    private JPanel createSectionWithTitleAndSlider(String title, String[] imagePaths) {
        sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Serif", Font.BOLD, 24));
        sectionTitle.setForeground(Color.BLACK);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        sectionPanel.add(sectionTitle);

        if (title.equals("Movies")) {
            contentRow = createContentRowForMovies(imagePaths);
        } else if (title.equals("TV Shows")) {
            contentRow = createContentRowForTVShows(imagePaths);
        } else if (title.equals("Best Movies")) {
            contentRow = createContentRowForBestMovies(imagePaths);
        } else {
            contentRow = createContentRowForBestTVShow(imagePaths);
        }


        JScrollPane sliderScrollPane = new JScrollPane(contentRow, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sliderScrollPane.getHorizontalScrollBar().setUnitIncrement(16); // Set horizontal scroll speed
        sectionPanel.add(sliderScrollPane);

        return sectionPanel;
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
            JPanel moviePanel = createMoviePanel("M",title, imagePath, description);
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
            JPanel moviePanel = createMoviePanel("",title, imagePath, description);
            contentRow.add(moviePanel);
        }

        return contentRow;
    }

    private JPanel createContentRowForBestMovies(String[] imagePaths) {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        int count = 0;
        // Load all images in the row
        for (String imagePath : imagePaths) {
            count++;
            String title = "Movie "+count;
            String description = "This is the description for " + title + ".";
            JPanel moviePanel = createMoviePanel("M",title, imagePath, description);
            contentRow.add(moviePanel);
        }

        return contentRow;
    }

    private JPanel createContentRowForBestTVShow(String[] imagePaths) {
        JPanel contentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contentRow.setBackground(Color.DARK_GRAY);

        int count = 0;
        // Load all images in the row
        for (String imagePath : imagePaths) {
            count++;
            String title = "TV Show "+count;
            String description = "This is the description for " + title + ".";
            JPanel moviePanel = createMoviePanel("",title, imagePath, description);
            contentRow.add(moviePanel);
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

    // Dummy data for Top 10 image paths
    private String[] getBestMoviesImagePaths() {
        return new String[]{
            "assets/img/Posters/top10_1.png",
            "assets/img/Posters/top10_2.png",
            "assets/img/Posters/top10_3.png",
            "assets/img/Posters/top10_4.png",
            "assets/img/Posters/top10_5.png",
            "assets/img/Posters/top10_6.png",
            // "assets/img/Posters/top10_7.png",
            // "assets/img/Posters/top10_8.png",
            // "assets/img/Posters/top10_9.png",
            // "assets/img/Posters/top10_10.png"
        };
    }

    private String[] getBestTvShowsImagePaths() {
        return new String[]{
            "assets/img/Posters/top10_1.png",
            "assets/img/Posters/top10_2.png",
            "assets/img/Posters/top10_3.png",
            "assets/img/Posters/top10_4.png",
            "assets/img/Posters/top10_5.png",
            "assets/img/Posters/top10_6.png",
            // "assets/img/Posters/top10_7.png",
            // "assets/img/Posters/top10_8.png",
            // "assets/img/Posters/top10_9.png",
            // "assets/img/Posters/top10_10.png"
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NetflixDashboard::new);
    }
}
