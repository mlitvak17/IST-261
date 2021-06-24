package mvclistdetailapp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Displays the fields of a single Student object, along with a button to save
 * changes to fields and a button to delete a Student
 */
public class StudentUI extends JFrame {

    // make sure there's no warning that index can be final
    private int index; 

    private final JTextField firstNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField gpaTextField;

    private final StudentCntl cntl;

    /**
     * A view class for Student objects
     *
     * @param studentCntl - the controller in the MVC pattern
     * @param startingIndexOfDisplay - which element of student list to show
     */
    public StudentUI(StudentCntl studentCntl, int startingIndexOfDisplay) {
        this.cntl = studentCntl;
        index = startingIndexOfDisplay;
        firstNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        gpaTextField = new JTextField();
        initComponents();
        displayFieldValues();
    }

    private void initComponents() {
        setTitle("Student Viewer");
        setSize(600, 350);
        // center the frame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel studentPanel = new JPanel(new GridLayout(5, 1));
        studentPanel.add(new JLabel("First Name"));
        studentPanel.add(firstNameTextField);
        studentPanel.add(new JLabel("Last Name"));
        studentPanel.add(lastNameTextField);
        studentPanel.add(new JLabel("GPA"));
        studentPanel.add(gpaTextField);

        JButton saveButton = new JButton("Save Changes & Return to List");
        JButton deleteButton = new JButton("Delete Student & Return to List");

        saveButton.addActionListener(event -> {
            saveChanges();
        });
        deleteButton.addActionListener(event -> {
            delete();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);

        getContentPane().add(studentPanel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void displayFieldValues() {
        firstNameTextField.setText(cntl.getStudent(index).getFirstName());
        lastNameTextField.setText(cntl.getStudent(index).getLastName());
        String gpa = Double.toString(cntl.getStudent(index).getGpa());
        gpaTextField.setText(gpa);
    }

    private void saveChanges() {
        if (cntl.getListSize() == 0) {
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "List is empty; use New button to add new item.");
        } else {
            // give warning if can't parse gpa as a double
            // https://www.baeldung.com/java-check-string-number
            if (gpaTextField.getText().matches("\\d+(\\.\\d+)?")) {
                // your method to save changes will probably need some arguments
                cntl.saveChanges();
            } else {
                JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                        "gpa must be a nonnegative number; "
                        + "returning to list display.");
            }
        }
    }

    // always best to make sure we have a valid index value
    private void delete() {
        int response = JOptionPane.showConfirmDialog(null, "Delete "
                + cntl.getStudent(index) + "?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            cntl.delete(index);
        }
    }

}
