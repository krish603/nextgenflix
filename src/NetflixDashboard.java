package src;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

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

        // Add navigation links
        String[] navItems = {"Home", "TV Shows", "Movies", "New & Popular", "My List"};
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
            "assets/img/Posters/unnamed.jpg", // Replace with actual paths to your images
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
            // Add paths for the rest of the movies
        };
    
        String[] descriptions =new String[35];

        for (int i = 1 ;i < 35;i++){
            descriptions[i]="This is the description for Movie "+i+".";

        }
            // Add descriptions for the rest of the movies
    
        for (int i = 1; i < 35; i++) {
            String title = "Movie " + i;
            String imagePath = (i <= imagePaths.length) ? imagePaths[i - 1] : imagePaths[i];
            String description = (i <= descriptions.length) ? descriptions[i - 1] : "No description available.";
            JPanel moviePanel = createMoviePanel(title, imagePath, description);
            contentGrid.add(moviePanel);
        }
    
        return contentGrid;
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
                new MovieDetailFrame(title, imagePath, description);
            }
        });
    
        return moviePanel;
    }
}
    
class Verification{
    static Scanner sc = new Scanner(System.in);
    static boolean n_c;
    public static String number_check(Connection con,String number) throws Existed_info{
        boolean b = true;
        n_c = false;
        boolean is_num = false;
        for(int i = 0 ; i < number.length() ; i++){
            if(number.charAt(i) >= 48 || number.charAt(i) <= 57){
                is_num = true;
            }
            else{
                is_num = false;
                break;
            }
        }
        while (b){
            if(number.length() != 10 && !is_num){
                System.out.print("Please enter a valid number : ");
                number = sc.next();
                b=true;
            }
            else{
                String sql = "Select customer_id from customer";
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        String num_check = rs.getString(1);
                        if(num_check.equals(number)){
                            n_c = true;
                            break;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } 
                if(n_c){
                    b=false;
                    throw new Existed_info("Number Already Exist");  
                }
                else{
                    b=false;
                }
            }
        }
        return number;
    }
    public static String mail_check(Connection con,String mail) throws Existed_info{
        mail = mail.toLowerCase();
        boolean m_c = false;
        String sql = "Select customer_email from customer";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String mail_check = rs.getString(1);
                    if(mail_check == mail){
                        m_c = true;
                    }
                }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        if(m_c){
            throw new Existed_info("E-mail Id Already Exist");
        }
        else{
            System.out.println("E-mail Id Inserted Sucessfully");
            return mail;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NetflixDashboard::new);
    }

    public static String pass_check(Connection con,String pass){
        boolean c = true;
        int count_caps = 0;
        int count_low = 0;
        int count_num = 0;
        boolean sym = true ;
        for(int i = 0; i<pass.length();i++){
            char x = pass.charAt(i);
            if(x >= 65 && x <= 90){
                count_caps = count_caps + 1  ;
            }
            else if( x >= 97 && x <= 122 ){
                count_low = count_low + 1;
            }
            else if( x >= 48 && x <= 57){
                count_num = count_num + 1;
            }
            else if (!(x >= 65 && x <= 90) && !(x >= 97 && x <= 122) && !(x >= 48 && x <= 57)){
                sym = false;
            }
        }
        while(c){
            if (pass.length() < 8) {
                System.out.println("Password Length is Not Valid ");
                System.out.print("Please Enter Password Again : ");
                pass = sc.next();
                pass_check(con,pass);
            } else {
                if(count_caps>=1 && count_low >= 1 && count_num >=1){
                    if(sym){
                        System.out.println("Password is valid ");
                        c = false;
                    }
                    else{
                        System.out.println("Don't insert Symbols in Password ");
                        System.out.print("Please Enter Password Again : ");
                        pass = sc.next();
                        pass_check(con,pass);
                    }
                }
                else{
                    System.out.println("Password is invalid , Please follow the terms & Conditions ");// we can write what is invalid 
                    System.out.print("Please Enter Password Again : ");
                    pass = sc.next();
                    pass_check(con,pass);
                }
            }
        }
        return pass;
    }
}

class Existed_info extends Exception{
    Existed_info(String s){
        super(s);
    }
}

