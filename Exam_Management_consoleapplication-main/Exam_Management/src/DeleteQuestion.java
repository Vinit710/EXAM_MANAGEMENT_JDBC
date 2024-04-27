import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteQuestion extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField;

    public DeleteQuestion() {
        initComponents();
        setTitle("Delete Question");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        idField = new JTextField();
        mainPanel.add(idField, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteQuestion();
            }
        });
        mainPanel.add(deleteButton, BorderLayout.SOUTH);
    }

    private void deleteQuestion() {
        String id = idField.getText();

        try (Connection connection = getConnection()) {
            // Check if the question exists in the database
            String selectQuery = "SELECT * FROM question WHERE id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Delete the question from the database
                String deleteQuery = "DELETE FROM question WHERE id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setString(1, id);

                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Question with ID " + id + " deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete question with ID " + id);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Question with ID " + id + " not found.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while deleting data: " + ex.getMessage());
        } finally {
            // After deletion, navigate back to admin home UI
            dispose(); // Close the delete question window
            new adminHome(); // Open the admin home window
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "root");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DeleteQuestion();
            }
        });
    }
}
