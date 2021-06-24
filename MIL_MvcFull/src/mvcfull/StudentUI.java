package mvcfull;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * class to display the fields of a single Student object from list of Students
 * includes methods to add new student, show next/previous student, delete
 * currently displayed student, and save changes to displayed student
 */
public class StudentUI extends JFrame {

    // if you use this variable outside of the constructor, 
    // you probably don't want to make it final
    private int index;

    private final JTextField firstNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField gpaTextField;

    private final StudentCntl cntl;

    /**
     * creates a new view/UI to show fields for a Student, along with buttons to
     * show next/previous student, to add/delete a student, and to edit/save
     * changes to a student
     *
     * @param studentCntl - the controller in the MVC pattern
     * @param startingIndexOfDisplay - which element student list shows at start
     */
    public StudentUI(StudentCntl studentCntl, int startingIndexOfDisplay) {
        this.cntl = studentCntl;
        index = startingIndexOfDisplay;
        firstNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        gpaTextField = new JTextField();
        initComponents();
        setTextFields(index);//start the display at the first student (John) in list        

    }

    private void initComponents() {
        setTitle("Student Viewer");
        setSize(600, 350);
        // center the frame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel studentPanel = new JPanel(new GridLayout(5, 1));
        studentPanel.add(new JLabel("First Name"));
        studentPanel.add(firstNameTextField);
        studentPanel.add(new JLabel("Last Name"));
        studentPanel.add(lastNameTextField);
        studentPanel.add(new JLabel("GPA"));
        studentPanel.add(gpaTextField);

        JButton deleteButton = new JButton("Delete Current Item");
        JButton nextButton = new JButton("Next");
        JButton previousButton = new JButton("Previous");
        JButton newButton = new JButton("New");
        JButton saveButton = new JButton("Save Changes");

        deleteButton.addActionListener(event -> delete());
        nextButton.addActionListener(event -> displayNextItem());
        previousButton.addActionListener(event -> displayPreviousItem());
        newButton.addActionListener(event -> createNew());
        saveButton.addActionListener(event -> saveChanges());

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(previousButton);
        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(nextButton);

        getContentPane().add(studentPanel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void delete() {
        if (cntl.getListSize() == 0) {
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    showEmptyListMsg());
        }
        else {
            int response = JOptionPane.showConfirmDialog(null, "Delete "
                    + cntl.getStudent(index)
                    + " ?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                if (cntl.getListSize() == 1) {
                    cntl.delete(index);
                    firstNameTextField.setText("");
                    lastNameTextField.setText("");
                    gpaTextField.setText("");
                    JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                            showEmptyListMsg());
                }
                else if (index == cntl.getListSize() - 1) {
                    cntl.delete(index);
                    index = 0;
                    setTextFields(index);
                }
                else {
                    cntl.delete(index);
                    setTextFields(index);
                }
            }
        }
    }

    private void displayNextItem() {
        int listSize = cntl.getListSize();

        if (listSize == 0) {
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    showEmptyListMsg());
        }
        else if (listSize == 1) {
            indexNext(listSize - 1);
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "Only one item in list; no next item");
        }
        else {
            indexNext(listSize - 1);
            setTextFields(index);
        }
    }

    private void displayPreviousItem() {
        int listSize = cntl.getListSize();

        if (listSize == 0) {
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    showEmptyListMsg());
        }
        else if (listSize == 1) {
            indexPrevious(listSize - 1);
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "Only one item in list; no previous item");
        }
        else {
            indexPrevious(listSize - 1);
            setTextFields(index);
        }

    }

    private void createNew() {
        int listSize = cntl.getListSize();

        cntl.addNewItemToEndOfList();
        JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                "Adding new 'Default' Student to end of list");
        index = listSize;
        setTextFields(index);
    }

    private void saveChanges() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String gpa = gpaTextField.getText();
        
        if (cntl.getListSize() == 0) {
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
            "Can't Save Changes:" + showEmptyListMsg());
        }
        
        else {
        cntl.replaceElementInArrayLast(index, firstName, lastName, gpa);
        JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                "Changes saved for " + cntl.getStudent(index));
        }
    }

    private String showEmptyListMsg() {
        return "The list is empty";
    }

    private void setTextFields(int index) {
        firstNameTextField.setText(cntl.getStudent(index).getFirstName());
        lastNameTextField.setText(cntl.getStudent(index).getLastName());
        gpaTextField.setText(cntl.getStudent(index).getStringGpa());
    }

    private void indexNext(int listSize) {
        if (index == listSize) {
            index = 0;
        }
        else {
            index++;
        }
    }

    private void indexPrevious(int listSize) {
        if (index == 0) {
            index = listSize;
        }
        else {
            index--;
        }
    }

}
