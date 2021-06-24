package mvclistdetailapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane; // If you don't need this for code you add, remove it
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * view class for the list of Students
 */
public class StudentListUI extends JFrame {

    private final StudentCntl cntl;

    JTable studentTable; // populated and accessed via cntl; 

    JButton newButton;
    JButton editButton;
    JButton quitButton;
    
    int startingFocus;

    public StudentListUI(StudentCntl studentCntl, int startingFocus) {
        this.cntl = studentCntl;
        this.startingFocus = startingFocus;
        initComponents();
    }

    private void initComponents() {
        setTitle("Student List");

        // get student table from controller
        studentTable = new JTable(cntl.getStudentTableModel());
        setFocus(startingFocus);
        // allow table to expand as new rows are added
        studentTable.setFillsViewportHeight(true);

        // set up scroller and add it to table panel
        JScrollPane tableScroller = new JScrollPane(studentTable);
        tableScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScroller.setPreferredSize(new Dimension(300, 100));
        JPanel tablePanel = new JPanel();
        tablePanel.add(tableScroller);

        // create buttons
        newButton = new JButton("New");
        editButton = new JButton("Edit (Make changes or Delete...)");
        quitButton = new JButton("Quit");

        // set action listeners for buttons
        newButton.addActionListener(event -> {
            addNew();
        });
        editButton.addActionListener(event -> {
            edit();
        });
        quitButton.addActionListener(event -> exitOnQuit());

        // add buttons to button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newButton);
        buttonPanel.add(editButton);
        buttonPanel.add(quitButton);

        // set frame size and center it
        setSize(500, 400);
        setLocationRelativeTo(null);

        // add panels to frame
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addNew() {
        cntl.addNewDummyStudent();
    }

    // edit item in StudentUI
    private void edit() {
        int selectedTableRow = studentTable.getSelectedRow();
        int selectedModelRow = studentTable.convertRowIndexToModel(selectedTableRow);
        cntl.showFieldsInStudentUI(selectedModelRow);
    }

    private void exitOnQuit() {
    }

    private void setFocus(int index) {
        // https://stackoverflow.com/questions/11365294/how-to-set-focus-to-the-first-row-in-a-jtable-which-is-inside-a-jscrollpane
        studentTable.changeSelection(index, index, false, false);
    }

}
