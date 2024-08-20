//mainGUI.java
//This is the main class that will run the program / start the GUI


package App;

import javax.swing.*;



public class mainGUI {
    public static void main(String[] args) {

        //we start the main frame of the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //create the home frame and set it to visible
                homeFrame home = new homeFrame();
                home.setVisible(true);
            }
        });
    }
}
