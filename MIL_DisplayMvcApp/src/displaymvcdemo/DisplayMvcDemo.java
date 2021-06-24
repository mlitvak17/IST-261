package displaymvcdemo;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Based on program in Big Java: Late Objects by Cay Horstmann; modified
 */
public class DisplayMvcDemo {

    public static void main(String[] args) {
        // https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        SwingUtilities.invokeLater(() -> {
            try {
                StudentCntl cntl = new StudentCntl();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplayMvcDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
