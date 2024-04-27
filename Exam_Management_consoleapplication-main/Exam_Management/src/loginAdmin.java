import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginAdmin extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;

    public loginAdmin() {
        initComponents();
        setTitle("Admin Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        inputPanel.add(usernameLabel);

        usernameField = new JTextField();
        inputPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        inputPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        mainPanel.add(loginButton, BorderLayout.SOUTH);
    }

    public void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("qems") && password.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome, Admin.");
            // Proceed to admin home
            adminHome adminHome = new adminHome();
            adminHome.initComponents();
            dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Create and display the login UI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new loginAdmin();
            }
        });
    }
}
