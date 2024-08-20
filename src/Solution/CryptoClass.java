package Solution;//Solution.CryptoClass.java
//This is the class that will handle the encryption and decryption of the input

//Austin Stephens
//Rasmussen University
//CEN4071C
//Professor Zayaz
//08/18/2024

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CryptoClass {
    //setting variables for the algorithm and key
    private static final String ALGORITHM = "AES";
    //key for the encryption
    private static final byte[] KEY = "PROFESSORZayass!".getBytes();


    public static void processFile(String fileName) throws Exception {
        //read our file
        String content = new String(Files.readAllBytes(Paths.get(fileName)));


        //encrypt and decrypt the data
        String encrypted = encrypt(content);
        String decrypted = decrypt(encrypted);
        //show results to the user
        System.out.println("Original: " + content);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }


    //Process the input from the user method
    //throws exception if there is an error
    public void processInput(String input) throws Exception {
        //new scanner object
        Scanner scanner = new Scanner(System.in);

        //grab the input from the user
        System.out.print("Enter text to encrypt: ");
        String input = scanner.nextLine();

        //we go ahead and encrypt and decrypt the data
        String encrypted = encrypt(input);
        String decrypted = decrypt(encrypted);

        //show our output to the user
        System.out.println("Original: " + input);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    private static String encrypt(String data) {
        //TODO: encryption logic
        return "encrypted_" + data;
    }

    private static String decrypt(String data) {
        ///TODO decryption logic
        return data.replace("encrypted_", "");
    }
}