package src;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class First_Interface extends JFrame {
    private JProgressBar progressBar;
    private Timer timer;

    public First_Interface() {
        // Load the image
        ImageIcon icon = new ImageIcon("assets/img/NEXTGENFLIX.png");
        Image backgroundImage = icon.getImage();

        // Create a custom JPanel to draw the background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null); // Use absolute positioning

        // Add the "Loading..." text label
        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        loadingLabel.setBounds(10, 550, 100, 20); // Position above the loading line
        panel.add(loadingLabel);

        // Add the loading line (JProgressBar)
        progressBar = new JProgressBar();
        progressBar.setBounds(10, 570, 980, 20); // Position at the bottom of the window
        progressBar.setValue(0); // Start with 0 progress
        panel.add(progressBar);

        // Set the custom panel as the content pane of the JFrame
        setContentPane(panel);

        // Set the frame properties
        setTitle("Nextgenflix");
        setSize(1025, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Initialize and start the loading timer
        timer = new Timer(10, e -> updateProgress());
        timer.start();
    }

    private void updateProgress() {
        SwingUtilities.invokeLater(() -> {
            int value = progressBar.getValue();
            if (value < 100) {
                progressBar.setValue(value + 1);
            } else {
                timer.stop();
                showLoginPage();
            }
        });
    }

    public static void main(String[] args) {    
        SwingUtilities.invokeLater(() -> {
            First_Interface frame = new First_Interface();
            frame.setVisible(true);
        });
    }

    private void showLoginPage() {
        // Load the login page image
        ImageIcon icon = new ImageIcon("assets/img/Login_page_bg.png");
        Image loginBackgroundImage = icon.getImage();

        // Create a custom JPanel to draw the login background image
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the login background image
                g.drawImage(loginBackgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        loginPanel.setLayout(null); // Use absolute positioning

        JLabel loginLabel = new JLabel("Welcome to the Nextgenflix!");
        loginLabel.setFont(new Font("Serif", Font.BOLD, 22));
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setBounds(420, 206, 500, 30); // Position above the loading line
        loginPanel.add(loginLabel);

        PlaceholderTextField usernameField = new PlaceholderTextField("Username");
        usernameField.setBounds(400, 250, 300, 30); // Example positioning, adjust as needed
        loginPanel.add(usernameField);

        PlaceholderPasswordField passwordField = new PlaceholderPasswordField("Password");
        passwordField.setBounds(400, 300, 300, 30); // Example positioning, adjust as needed
        loginPanel.add(passwordField);

        JButton loginButton = new JButton(new ImageIcon("assets/img/btn/login.png"));
        loginButton.setBounds(500, 350, 100, 50);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            boolean authenticated = UserAuthenticator.authenticate(username, password);
            if (authenticated) { 
                this.dispose();
                NextgenflixDashboard netflixDashboard = new NextgenflixDashboard();
                netflixDashboard.setVisible(true);
                System.out.println("Authentication Succesfull");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginPanel.add(loginButton);

        // Set the custom panel as the content pane of the frame
        setContentPane(loginPanel);
        revalidate();
    }
}

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/nextgenflix";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {    
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

class UserAuthenticator {
    public static boolean authenticate(String username, String password) {
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

class PlaceholderPasswordField extends JPasswordField {
    private String placeholder;

    public PlaceholderPasswordField(String placeholder) {
        this.placeholder = placeholder;
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getPassword().length == 0 && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }
}


class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }
}

