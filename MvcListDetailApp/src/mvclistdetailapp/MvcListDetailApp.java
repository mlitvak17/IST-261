package mvclistdetailapp;

import javax.swing.SwingUtilities;

/**
 * Demonstrates List Detail MVC pattern
 */
public class MvcListDetailApp {

    public static void main(String[] args) {
        // https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        SwingUtilities.invokeLater(() -> {
            StudentCntl cntl = new StudentCntl();
        });
    }

}
