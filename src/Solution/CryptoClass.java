//Solution.CryptoClass.java
//This is the class that will handle the encryption and decryption of the input

//Austin Stephens
//Rasmussen University
//CEN4071C
//Professor Zayaz
//08/18/2024

package Solution;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class CryptoClass {

    //Variables for the class, these are the keys and algorithms that will be used for the encryption and decryption
    private static final String AES_ALGORITHM = "AES";
    private static final String BLOWFISH_ALGORITHM = "Blowfish";
    private static final byte[] AES_KEY = "PROFESSORZayass!".getBytes();
    private static final byte[] BLOWFISH_KEY = "ZayazzKey1234".getBytes();
    private static final String DATA_FOLDER = "data"; // This is the folder where our data will be stored

    // process file method that will take in the file name and the algorithm to use
    public static void processFile(String fileName, String algorithm) throws Exception {
        //This will get the file path and read the content of the file
        String filePath = Paths.get(DATA_FOLDER, fileName).toString();
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        //This will encrypt / decrpyt the content of the file by calling the encrypt and decrypt methods
        String encrypted = encrypt(content, algorithm);
        String decrypted = decrypt(encrypted, algorithm);

        //console output of the original, encrypted, and decrypted content
        System.out.println("Original: " + content);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }


    //This method will take in the input and the algorithm to use
    //we use throws Exception to handle any exceptions that may occur
    public void processInput(String input, String algorithm) throws Exception {
        //we need to validate the input to make sure it is a valid input
        String validatedInput = validateInput(input);

        //we then encrypt the input and decrypt the input
        String encrypted = encrypt(validatedInput, algorithm);
        String decrypted = decrypt(encrypted, algorithm);

        //console output of the original, encrypted, and decrypted content for reference
        System.out.println("Original: " + validatedInput);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }


    //List Array of files that will be used to store the files in the data folder
    public static List<String> listFiles() throws Exception {

        //try to get the files in the data folder - points to the data folder variable
        try (Stream<Path> paths = Files.walk(Paths.get(DATA_FOLDER))) {

            //return the paths of the files in the data folder if they are regular files
            return paths.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }

    }


    //This method will update the file with the encrypted content
    //we bring in the file name, the new content, and the algorithm to use
    public static void updateFileWithEncryptedContent(String fileName, String newContent, String algorithm) throws Exception {

        //get the file path and validate the new content
        String filePath = Paths.get(DATA_FOLDER, fileName).toString();
        //validate the new data
        String validatedContent = validateInput(newContent);

        //once the data is validated, we encrypt the data and write it to the file
        String encrypted = encrypt(validatedContent, algorithm);

        //then we write the encrypted data to the file
        Files.write(Paths.get(filePath), encrypted.getBytes());
    }


    //This method will update the file with the decrypted content
    //also brining in the file name and the algorithm to use
    public static void updateFileWithDecryptedContent(String fileName, String algorithm) throws Exception {

        //same as above, get the file path then read the content of the file but - decrypt it
        String filePath = Paths.get(DATA_FOLDER, fileName).toString();
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        String decrypted = decrypt(content, algorithm);
        Files.write(Paths.get(filePath), decrypted.getBytes());
    }


    //This method will encrypt the data that is passed in
    //we bring in the data and the algorithm to use
    private static String encrypt(String data, String algorithm) {

        //try to encrypt the data
        try {

            //we use the cipher class to encrypt the data and use the algorithm that is passed in
            Cipher cipher = Cipher.getInstance(algorithm);

            //we use the algorithm to get the key and then initialize the cipher based on the encryption mode
            byte[] key = algorithm.equals(AES_ALGORITHM) ? AES_KEY : BLOWFISH_KEY;


            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, algorithm));
            //we then return the encrypted data
            return "encrypted_" + Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception ex) {
            //if the encryption fails, throw an exception
            throw new RuntimeException(ex);
        }
    }


    //This method will decrypt the data that is passed in, reverse of the encrypt method
    private static String decrypt(String data, String algorithm) {

        //try to decrypt the data
        try {
            //we use the cipher class to decrypt the data and use the algorithm that is passed in
            Cipher cipher = Cipher.getInstance(algorithm);

            //we use the algorithm to get the key and then initialize the cipher based on the decryption mode
            byte[] key = algorithm.equals(AES_ALGORITHM) ? AES_KEY : BLOWFISH_KEY;

            //we then return the decrypted data
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, algorithm));

            //return the decrypted data by decoding the base64 encoded data
            return new String(cipher.doFinal(Base64.getDecoder().decode(data.replace("encrypted_", ""))));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //This method will validate the input that is passed in if it is valid
    private static String validateInput(String input) {

        //we use a regular expression to validate the input and remove any characters that are not valid per requirments
        return input.replaceAll("[^a-zA-Z0-9\\p{Punct}]", "");
    }
}