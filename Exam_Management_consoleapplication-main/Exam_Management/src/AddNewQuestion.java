import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewQuestion extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField, nameField, opt1Field, opt2Field, opt3Field, opt4Field, answerField;

    public AddNewQuestion() {
        initComponents();
        setTitle("Add New Question");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        add(mainPanel);

        idField = new JTextField();
        nameField = new JTextField();
        opt1Field = new JTextField();
        opt2Field = new JTextField();
        opt3Field = new JTextField();
        opt4Field = new JTextField();
        answerField = new JTextField();

        mainPanel.add(new JLabel("ID:"));
        mainPanel.add(idField);
        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Opt1:"));
        mainPanel.add(opt1Field);
        mainPanel.add(new JLabel("Opt2:"));
        mainPanel.add(opt2Field);
        mainPanel.add(new JLabel("Opt3:"));
        mainPanel.add(opt3Field);
        mainPanel.add(new JLabel("Opt4:"));
        mainPanel.add(opt4Field);
        mainPanel.add(new JLabel("Answer (option number):"));
        mainPanel.add(answerField);

        JButton addButton = new JButton("Add Question");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addQuestion();
            }
        });
        mainPanel.add(addButton);
    }

    private void addQuestion() {
        String id = idField.getText();
        String name = nameField.getText();
        String opt1 = opt1Field.getText();
        String opt2 = opt2Field.getText();
        String opt3 = opt3Field.getText();
        String opt4 = opt4Field.getText();
        String answer = answerField.getText();

        try (Connection connection = getConnection()) {
            String query = "INSERT INTO question(id, name, opt1, opt2, opt3, opt4, answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, opt1);
            preparedStatement.setString(4, opt2);
            preparedStatement.setString(5, opt3);
            preparedStatement.setString(6, opt4);
            preparedStatement.setString(7, answer);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Question added successfully!");
                // Close the AddNewQuestion window
                dispose();
                // Open a new instance of the adminHome window
                new adminHome().initComponents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add question.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while adding question: " + ex.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddNewQuestion();
            }
        });
    }
}
