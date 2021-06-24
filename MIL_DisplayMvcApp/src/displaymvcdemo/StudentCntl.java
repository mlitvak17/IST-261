package displaymvcdemo;

import java.io.FileNotFoundException;

/**
 * Based on program in Big Java: Late Objects by Cay Horstmann; modified
 * creates a new StudentList(), populated by data in file students.txt
 * creates, makes visible StudentUI, allowing user to select student from list 
*/
public class StudentCntl {

    private static final int STARTING_INDEX_OF_DISPLAY = 0;
    private final StudentList studentList;
    private final StudentUI studentUI;

    public StudentCntl() throws FileNotFoundException {
        studentList = new StudentList();
        studentUI = new StudentUI(this, STARTING_INDEX_OF_DISPLAY); // pass 0 so UI starts at first element in list
        studentUI.setVisible(true);
    }

    // precondition: index is checked for validity before this method is called
    Student getStudent(int index) {
        return studentList.getStudents().get(index);
    }
    
    int getListSize() {
        return studentList.getStudents().size();
    }

}
