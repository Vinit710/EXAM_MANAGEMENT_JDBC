import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminHome extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public adminHome() {
        initComponents();
        setTitle("Admin Home");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        JButton addQuestionButton = new JButton("Add New Question");
        addQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("You selected: Add New Question\n");
                // Call method to handle adding a new question
                new AddNewQuestion();
            }
        });
        buttonPanel.add(addQuestionButton);

        JButton updateQuestionButton = new JButton("Update Question");
        updateQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("You selected: Update Question\n");
                // Call method to handle updating a question
             // Create a new instance of UpdateQuestion and display it
                new UpdateQuestion();
            }
        });
        buttonPanel.add(updateQuestionButton);

        JButton viewQuestionsButton = new JButton("View All Questions");
        viewQuestionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("You selected: View All Questions\n");
                // Call method to handle viewing all questions
                new ViewAllQuestion();
            }
        });
        buttonPanel.add(viewQuestionsButton);

        JButton deleteQuestionButton = new JButton("Delete Question");
        deleteQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("You selected: Delete Question\n");
                // Call method to handle deleting a question
                new DeleteQuestion();
            }
        });
        buttonPanel.add(deleteQuestionButton);

        JButton viewResultsButton = new JButton("View All Student Results");
        viewResultsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("You selected: View All Student Results\n");
                // Call method to handle viewing all student results
                new ViewAllResult();
            }
        });
        buttonPanel.add(viewResultsButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("Logging out...\n");
                dispose(); // Close the admin home window
                new index(); // Open the index UI
            }
        });
        buttonPanel.add(logoutButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.append("Exiting application...\n");
                dispose(); // Close the admin home window
            }
        });
        buttonPanel.add(exitButton);
    }

    public static void main(String[] args) {
        // Create and display the AdminHome UI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new adminHome();
            }
        });
    }
}
