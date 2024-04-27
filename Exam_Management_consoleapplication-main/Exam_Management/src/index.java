import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class index extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton studentButton;
    private JButton adminButton;
    private JButton exitButton;

    public index() {
        initComponents();
        setTitle("Welcome to the Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));
        add(mainPanel);

        JLabel welcomeLabel = new JLabel("Welcome to the Application!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(welcomeLabel);

        studentButton = new JButton("STUDENT");
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleStudentButton();
            }
        });
        mainPanel.add(studentButton);

        adminButton = new JButton("ADMIN");
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAdminButton();
            }
        });
        mainPanel.add(adminButton);

        exitButton = new JButton("EXIT");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleExitButton();
            }
        });
        mainPanel.add(exitButton);
    }

    private void handleStudentButton() {
        int studentChoice = JOptionPane.showOptionDialog(null,
                "You selected STUDENT.\nChoose an option:",
                "Student Options",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"LOGIN", "REGISTER"},
                "LOGIN");

        if (studentChoice == JOptionPane.YES_OPTION) {
            // Call method to handle student login
            StudentLogin studentLogin = new StudentLogin();
            studentLogin.initLogin();
        } else if (studentChoice == JOptionPane.NO_OPTION) {
            // Call method to handle student registration
            StudentRegister studentRegister = new StudentRegister();
            studentRegister.initRegister();
        }
    }

    private void handleAdminButton() {
        // Call method to handle admin login
        loginAdmin loginAdmin = new loginAdmin();
        loginAdmin.login();
    }

    private void handleExitButton() {
        System.out.println("Exiting application...");
        System.exit(0);
    }

    public static void main(String[] args) {
        // Create and display the UI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new index();
            }
        });
    }
}
