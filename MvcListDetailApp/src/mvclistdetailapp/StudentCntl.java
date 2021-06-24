package mvclistdetailapp;

public class StudentCntl {

    private static final int STARTING_INDEX_OF_DISPLAY = 0;
    private final StudentList studentList;
    private final StudentUI studentUI;

    // next two lines added for MvcListDetail assignment
    private final StudentTableModel studentTableModel;
    private final StudentListUI studentListUI;

    /**
     * Creates a new StudentList, StudentUI, and StudentListUI
     * makes visible StudentListUI
     */
    public StudentCntl() {
        studentList = new StudentList();
        studentUI = new StudentUI(this, STARTING_INDEX_OF_DISPLAY); // pass 0 so UI starts at first element in list

        // added for MvcListDetail assignment   
        studentTableModel = new StudentTableModel(studentList.getStudents());
        studentListUI = new StudentListUI(this, STARTING_INDEX_OF_DISPLAY);
        studentListUI.setVisible(true);
    }

    Student getStudent(int index) {
        return studentList.getStudents().get(index);
    }

    //add new dummy student with hard-coded values to end of student list
    void addNewDummyStudent() {
    }

    int getListSize() {
        return studentList.getStudents().size();
    }

    // your method to save changes will probably need some arguments
    void saveChanges() {
    }

    // always best to make sure we have a valid index value
    void delete(int index) {
        // See API:
        // https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/ArrayList.html#remove(int)
        studentList.getStudents().remove(index);
    }

    // added for MvcListDetail assignment
    StudentTableModel getStudentTableModel() {
        return studentTableModel;
    }

    // added for MvcListDetail assignment
    void showFieldsInStudentUI(int index) {
        studentUI.setVisible(true);
    }

}
