package App;

import Solution.CryptoClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homeFrame extends JFrame {

    public homeFrame() {
        //new instance of the CryptoClass
        CryptoClass crypto = new CryptoClass();
        setTitle("Encryption/Decryption");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //setting the colors for the buttons and text
        Color backgroundColor = new Color(18, 18, 18);
        Color buttonColor = new Color(30, 215, 96);
        Color textColor = Color.WHITE;
        Font font = new Font("Proxima Nova", Font.BOLD, 16);

        //starting new panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));



        //creating buttons for the user to interact with
        //setting the colors and font
        JButton fileButton = new JButton("Encrypt/Decrypt from file");
        fileButton.setBackground(buttonColor);
        fileButton.setForeground(textColor);
        fileButton.setFont(font);
        fileButton.setFocusPainted(false);

        //action listener for button to encrypt and decrypt from file
        fileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("Enter file name:");
                if (fileName != null) {
                    try {
                        CryptoClass.processFile(fileName);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "File Not Found: " + ex.getMessage());
                    }
                }
            }
        });


        //creating button for the user to interact with
        //setting the colors and font
        JButton inputButton = new JButton("Encrypt/Decrypt from keyboard input");
        inputButton.setBackground(buttonColor);
        inputButton.setForeground(textColor);
        inputButton.setFont(font);
        inputButton.setFocusPainted(false);

        //action listener for button to encrypt and decrypt from keyboard input
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = JOptionPane.showInputDialog("Enter text to encrypt:");
                    crypto.processInput(input);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        //creating button for the user to interact with
        //setting the colors and font
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(textColor);
        exitButton.setFont(font);
        exitButton.setFocusPainted(false);

        //action listener for button to exit the program
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //adding buttons to the panel
        panel.add(fileButton);
        panel.add(inputButton);
        panel.add(exitButton);

        //adding panel to the frame to` display
        add(panel, BorderLayout.CENTER);

        //saet frame background color
        getContentPane().setBackground(backgroundColor);
    }
}