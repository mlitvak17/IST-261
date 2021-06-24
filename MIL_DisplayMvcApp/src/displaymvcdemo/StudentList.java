package displaymvcdemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;

/**
 * class to hold a list of type Student and associated methods
 */
public class StudentList {

    private final List<Student> students;

    /**
     * creates a new ArrayList to hold students and then
     * fills that list with three students
     * @throws java.io.FileNotFoundException
     */
    public StudentList() throws FileNotFoundException {
        students = new ArrayList<>();
        addInitialStudentsToList();
    }

    private void addInitialStudentsToList() throws FileNotFoundException {
        // TODO: replace the three hard-coded lines below
        // with code that reads the data from the (included) file students.txt
        // and populates the ArrayList students appropriately
    
        File textFile = new File("students.txt");
        try (Scanner scan = new Scanner(textFile).useDelimiter(", ")) {
            {
                
                while(scan.hasNextLine()){
                    String firstName = scan.next().replaceAll("\\n", ""); //the replaceAll serves to remove the line breaks
                    String lastName = scan.next();
                    String gpa = scan.findInLine("\\d...");
                    
                    
                    if (gpa == null) {       
                        gpa = scan.findInLine("\\d..");
                    }
                    
                    students.add(new Student(firstName, lastName, gpa));
                    
                }
            }    
        }
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
}


