package displaymvcdemo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Based on program in Big Java: Late Objects by Cay Horstmann; modified to show
 * MVC towards the style we'll be using in our class
 */
public class StudentUI extends JFrame {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 350;

    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;
    
    private static final int FIELD_WIDTH = 10;

    private JLabel rateLabel;
    private JTextField studentNumberField;
    private JButton button;
    private JTextArea resultArea;

    private final StudentCntl cntl;

    // position of Student in ArrayList students in class StudentList
    private int index;

    public StudentUI(StudentCntl studentCntl, int startingIndexOfDisplay) {
        this.cntl = studentCntl;
        this.index = startingIndexOfDisplay;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Student Viewer");
        
        rateLabel = new JLabel("Student (an integer from 0 through 2): ");
        studentNumberField = new JTextField(FIELD_WIDTH);
        
        // view calls controller to get default student at startingIndex of display
        studentNumberField.setText(Integer.toString(index));
        
        // create button and add its action handler, lambda style
        button = new JButton("Show student");
        button.addActionListener(event -> showStudent());
        
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        
        // View calls controller to get first Student from the ArrayList students in StudentList
        // Then, view can display that student
        if (isValid(index)) {
            resultArea.setText(cntl.getStudent(index).toString() + "\n");
        } 
        resultArea.setEditable(false);
        
        
        // create Panel and add components to it
        JPanel panel = new JPanel();
        panel.add(rateLabel);
        panel.add(studentNumberField);
        panel.add(button);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        add(panel);
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // button-handler code
    private void showStudent() {
        
        // view gets the index of student to display from the view itself
        // Note: if user enters a non-integer, program will give a runtime exception
        index = Integer.parseInt(studentNumberField.getText());

        // if the index is valid, view displays the student at that index
        if (isValid(index)) {
            Student studentToDisplay = cntl.getStudent(index);
            // https://stackoverflow.com/questions/15798532/how-to-clear-jtextarea
            resultArea.setText("");
            resultArea.append(studentToDisplay.toString() + "\n\n\n");
        } else {
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "index must be an integer between 0 and 2 inclusive");
        }
    }


    private boolean isValid(int index) {
        // https://www.baeldung.com/java-check-string-number
        return studentNumberField.getText().matches("\\d+(\\.\\d+)?")
                && index >= 0
                && index < cntl.getListSize();
    }

}
