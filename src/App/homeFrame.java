//homeFrame.java
//This is the main home frame of the application

//This is more of the GUI side of the application and is where the user will interact with the application
package App;

import Solution.CryptoClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class homeFrame extends JFrame {

    //final variables for the display area
    private final JTextArea displayArea;

    //This is the main frame of the application
    public homeFrame() {
        //create a new instance of the CryptoClass as we will be using the methods from that class
        CryptoClass crypto = new CryptoClass();

        //design of the frame
        setTitle("Encryption/Decryption");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color backgroundColor = new Color(18, 18, 18);
        Color buttonColor = new Color(30, 215, 96);
        Color textColor = Color.WHITE;
        Font font = new Font("Proxima Nova", Font.BOLD, 16);


        //we start creating the panels and setting the layouts

        //more design work for the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        displayArea = new JTextArea();
        displayArea.setBackground(backgroundColor);
        displayArea.setForeground(textColor);
        displayArea.setFont(font);
        displayArea.setEditable(false);


        //Start of buttons for the panel
        JButton fileEncryptButton = new JButton("Encrypt from file");
        //design work for the button(s)
        fileEncryptButton.setBackground(buttonColor);
        fileEncryptButton.setForeground(textColor);
        fileEncryptButton.setFont(font);
        fileEncryptButton.setFocusPainted(false);

        //here we start to add the action listener for the button
        //Enables us to perform an action when the button is clicked
        fileEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if button is clicked we will try to encrypt the file
                try {
                    //find the files in the data folder
                    List<String> files = CryptoClass.listFiles();
                    //show input dialog to choose file
                    String fileName = (String) JOptionPane.showInputDialog(null, "Choose file:", "File Selection",
                            JOptionPane.QUESTION_MESSAGE, null, files.toArray(), files.get(0));

                    //need to make sure the file is not null to continue
                    if (fileName != null) {
                        //if its not null we will show another dialog to choose the algorithm we want to use
                        String algorithm = (String) JOptionPane.showInputDialog(null, "Choose algorithm:", "Algorithm Selection",
                                JOptionPane.QUESTION_MESSAGE, null, new String[]{"AES", "Blowfish"}, "AES");

                        //enter new content to encrypt when prompted
                        String newContent = JOptionPane.showInputDialog("Enter new content to encrypt:");

                        //if new input is not null we will update the file with the encrypted content
                        if (newContent != null) {
                            //send in the file name, new content, and the algorithm to use
                            CryptoClass.updateFileWithEncryptedContent(fileName, newContent, algorithm);
                            //display message to the user that the file has been updated with encrypted content
                            displayArea.setText("File " + fileName + " has been updated with encrypted content.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });


        //another button for decrypting the file
        JButton fileDecryptButton = new JButton("Decrypt from file");

        //design work for the button(s)
        fileDecryptButton.setBackground(buttonColor);
        fileDecryptButton.setForeground(textColor);
        fileDecryptButton.setFont(font);
        fileDecryptButton.setFocusPainted(false);

        //action listener for the button
        fileDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //same idea however, we are decrypting the file
                    List<String> files = CryptoClass.listFiles();

                    //list files then choose file
                    String fileName = (String) JOptionPane.showInputDialog(null, "Choose file:", "File Selection",
                            JOptionPane.QUESTION_MESSAGE, null, files.toArray(), files.get(0));

                    //if file is not null we will show another dialog to choose the algorithm we want to use
                    if (fileName != null) {

                        //select the algorithm to use, will not work if the file was encrypted with a different algorithm
                        String algorithm = (String) JOptionPane.showInputDialog(null, "Choose algorithm:", "Algorithm Selection",
                                JOptionPane.QUESTION_MESSAGE, null, new String[]{"AES", "Blowfish"}, "AES");

                        //call the method to decrypt the file
                        CryptoClass.updateFileWithDecryptedContent(fileName, algorithm);
                        displayArea.setText("File " + fileName + " has been updated with decrypted content.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });



        //button for encrypting and decrypting from keyboard input
        JButton inputButton = new JButton("Encrypt/Decrypt from keyboard input");

        //design work for the button(s)
        inputButton.setBackground(buttonColor);
        inputButton.setForeground(textColor);
        inputButton.setFont(font);
        inputButton.setFocusPainted(false);

        //action listener for the button
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //enter text to encrypt
                    String input = JOptionPane.showInputDialog("Enter text to encrypt:");

                    //same thing open dialog to choose the algorithm
                    String algorithm = (String) JOptionPane.showInputDialog(null, "Choose algorithm:", "Algorithm Selection",
                            JOptionPane.QUESTION_MESSAGE, null, new String[]{"AES", "Blowfish"}, "AES");

                    //send in the input and the algorithm to use
                    crypto.processInput(input, algorithm);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });


        //exit button to close the application
        JButton exitButton = new JButton("Exit");
        //design work for the button(s)
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(textColor);
        exitButton.setFont(font);
        exitButton.setFocusPainted(false);

        //action listener for the button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //just exit the application
                System.exit(0);
            }
        });


        //add the buttons to the panel to display
        panel.add(fileEncryptButton);
        panel.add(fileDecryptButton);
        panel.add(inputButton);
        panel.add(exitButton);


        //design work for the display area
        add(panel, BorderLayout.CENTER);
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);
        getContentPane().setBackground(backgroundColor);
    }
}