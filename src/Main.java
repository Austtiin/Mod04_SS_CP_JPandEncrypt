//main.java
//This is where we will have the main method to run the program

//Austin Stephens
//Rasmussen University
//CEN4071C
//Professor Zayaz
//08/18/2024

//This is the main class that will run the program
//We have moved over our code and cleaned it up to make it more readable.
//I.E we have move some input and output to the main to clean it up and make it more readable.

//Instructions
//This week you will continue with the Java benchmarking application for different encryption algorithms which will take file and keyboard input,
// perform data validation on the input,
// implement existing Java APIs to encrypt and decrypt the input,
// and provide the results of performance-based test cases.

//Extend your Java program so that it also:

//integrates ChaCha to encrypt and then decrypt using keyboard input
//integrates ChaCha to encrypt and then decrypt using file input
//displays appropriate messages during execution to inform the user of progress


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CryptoClass cryptoHelper = new CryptoClass();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Encrypt/Decrypt from file");
            System.out.println("2. Encrypt/Decrypt from keyboard input");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter file name: ");
                        String fileName = scanner.nextLine();
                        CryptoClass.processFile(fileName);
                        break;
                    case 2:
                        cryptoHelper.processInput();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}