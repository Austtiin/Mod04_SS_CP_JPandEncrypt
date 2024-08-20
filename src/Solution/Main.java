//main.java
//This is where we will have the main method to run the program

//Austin Stephens
//Rasmussen University
//CEN4071C
//Professor Zayaz
//08/18/2024

//This is the main class that will run the program
//We have moved over our code and cleaned it up to make it more readable.


/*
This week you will continue with the Java benchmarking application for different encryption algorithms, which will take file and keyboard input, perform data validation on the input, implement existing Java APIs to encrypt and decrypt the input, and provide the results of performance-based test cases.
Extend your Java program so that it also:

integrates AES to encrypt and then decrypt using keyboard input
integrates AES to encrypt and then decrypt using file input
integrates Blowfish to encrypt and then decrypt using keyboard input
integrates Blowfish to encrypt and then decrypt using file input
displays appropriate messages during execution to inform the user of progress
*/


//This week we will be adding the ability to encrypt and decrypt using the AES and Blowfish algorithms
//We also transfered our application to be GUI based for a better user experience
//main area of program to start. This will also run the main frame of the application
package Solution;
import App.homeFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //start main frame of the application
                //set to visible
                homeFrame mainFrame = new homeFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}