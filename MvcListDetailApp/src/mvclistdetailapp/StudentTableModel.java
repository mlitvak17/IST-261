package mvclistdetailapp;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    
    private final String[] columnNames = {"Change Me", "Change Me", "Change Me"};

    private final List<Student> studentList;
    
    public StudentTableModel(List<Student> studentList) {
        this.studentList = studentList;
    }
    
    @Override
    public String getColumnName(int i ) { // to put column names in column headers
        return "column name";      
    }
    
    // you need only overide/implement the next 3 methods of AbstractTableModel
    // https://docs.oracle.com/en/java/javase/14/docs/api/java.desktop/javax/swing/table/AbstractTableModel.html
    @Override
    public int getRowCount() {        
        return 2;
    }
    
    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        switch(col){
            case 0: return (Object) studentList.get(row).getFirstName();
            case 1: return (Object) studentList.get(row).getLastName();
            case 2: return (Object) studentList.get(row).getGpa();
            default: return null;
        }
    }

}
