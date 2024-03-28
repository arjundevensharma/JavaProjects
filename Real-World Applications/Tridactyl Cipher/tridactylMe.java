package ISC4U;

import java.util.*;
import java.io.*;

public class tridactylMe {
    /**
     * This program decrypts user-entered strings as per specified guidelines through the 
     * use of a user-entered passphrase.
     * 
     * IMPORTANT PRE-REQUISTE INFO: After successfully creating a working program,
     * Ally and I didn't find time after Friday to work together to 
     * comment/refine/edit the code. What is submitted on Replit is Ally's personal
     * rendition of the program, and submitted here is mine. I want to reiterate that the
     * only individual changes made were solely related to documentation and code refinement, 
     * not code synthesis.
     * 
     * @author Arjun Sharma
     */
    
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner in = new Scanner(System.in);

        // Display welcome message
        System.out.println("Welcome to the Double-A Tridactyl (Ally and Arjun).");
        System.out.println("This program decrypts user-entered strings using the tridactyl algorithm.");
        
        // Prompts the user to enter the file name, reprompting the user to input a valid file if an invalid file is inputted   
        String fileName;
	    do
	    {
	    	//prompt user for filename
			System.out.print("\nPlease enter the name of the file containing the messages and passphrase to be used for decryption: ");
		    fileName = in.next(); //store the filename in a variable
	    } while (!(new File(fileName).isFile())); //if the inputted file is not a valid file, the do-while loop iterates once more
	    
	    Scanner fileIn = new Scanner(new File(fileName)); //configures scanner with inputted file

        // Display loading animation for usability
        displayLoadingAnimation("Reading the passphrase from the file", 3, 1);
        
        String passphrase = passToAlphabet(fileIn.nextLine()); // Process the passphrase from the file and create corresponding jumbled alphabet
        System.out.println("\nPassphrase processed successfully!\n"); // Inform the user that the passphrase has been processed (usability)

        // Display header for decrypted messages
        System.out.println("Decrypted Messages:");
        System.out.println("--------------------\n");
        
        // Loop through each remaining line containing messages in the file and output the corresponding decrypted messages
        while(fileIn.hasNextLine()) { // For every line (message) left in the file, do the following process:
            String line = fileIn.nextLine(); // First, read and store the full text contained in the line
            line = line.substring(1, line.lastIndexOf(']')); // Then, extract the message from the line's text, removing square brackets from the line
            System.out.println(decrypt(line, passphrase) + "\n"); // Decrypt the message using the specified algorithm and output the decrypted message
        }

        // Usability to indicate the finished process of decryption
        System.out.println("--------------------");
        System.out.println("Decryption completed!");
    }

    /**
     * Convert the passphrase to a unique alphabet string.
     * 
     * @param passphrase The input passphrase.
     * @return The processed alphabet string.
     */
    public static String passToAlphabet(String passphrase) {
        String stringAlphabet = ""; // Initialize an empty string for the alphabet
        StringBuffer alphabet = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ "); // Create a StringBuffer containing the standard alphabet
        passphrase = passphrase.toUpperCase(); // Standardize the passphrase to the same case

        // Loop through each character in the passphrase
        for (char c : passphrase.toCharArray()) {
            // Check if current character searched in the passphrase hasn't been added to the newAlphabet yet and that it is a letter or space
            if (stringAlphabet.indexOf(c) == -1 && (Character.isLetter(c) || c == ' ')) {
                stringAlphabet += c; // Add the current character searched to the stringAlphabet
                int index = alphabet.indexOf(String.valueOf(c)); // Find the index of the character in the standard alphabet
                alphabet.delete(index, index + 1); // Remove the character from the alphabet to be eventually added to the end of the new alphabet (making up all letters unfound in the passphrase in alphabetical order)
            }
        }
        
        stringAlphabet += alphabet; // Append the remaining characters from the alphabet StringBuffer to the stringAlphabet
        return stringAlphabet; // Return the processed alphabet string
    }

    /**
     * Decrypt a message using the tridactyl algorithm.
     * 
     * @param message The input message to be decrypted.
     * @param passphrase The passphrase used for decryption.
     * @return The decrypted message.
     */
    public static String decrypt(String message, String passphrase) {
        StringBuffer output = new StringBuffer(""); // Initialize an empty StringBuffer for the output
        
        // Loop through each character in the message
        for (char ch : message.toCharArray()) {
            int characterIndex = passphrase.indexOf(ch); // Find the index of the character in the passphrase
            // Turn the index of the character in the passphrase into base-3 and append to the output string
            output.append(characterIndex / 9).append(characterIndex % 9 / 3).append(characterIndex % 3);
        }
        
        int spaceIndex = passphrase.indexOf(' '); // Find the index of the space character in the passphrase
        
        // Add padding to the output to ensure its length is a multiple of 9 and thus the # of base 3 sequences are multiples of 3
        while (output.length() % 9 != 0) {
            // Append the tridactyl representation of the space character to the output
            output.append(spaceIndex / 9).append(spaceIndex % 9 / 3).append(spaceIndex % 3);
        }
        
        // Swap characters in the base-3 sequences as per guidelines as part of decryption process
        for (int i = 0; i < output.length(); i += 9) {
            swapChars(output, i, 6, 3); // Swap characters at positions 6 and 3
            swapChars(output, i, 1, 7); // Swap characters at positions 1 and 7
            swapChars(output, i, 2, 5); // Swap characters at positions 2 and 5
        }

        StringBuilder sequenceBackToLetters = new StringBuilder(); // Initialize an empty StringBuilder for the final decrypted message
        
        // Convert the tridactyl representation back to characters
        for (int i = 0; i < output.length(); i += 3) {
        	int sequenceRepresentedInBaseTen = Integer.parseInt(output.substring(i, i + 3), 3); // Convert each base-3 sequence to an integer in base-10
        	sequenceBackToLetters.append(passphrase.charAt(sequenceRepresentedInBaseTen)); // Append the corresponding character from the passphrase to the final message
    	}
        
        return sequenceBackToLetters.toString(); // Return the decrypted message
    }

    /**
     * Swap two characters in a StringBuffer.
     * 
     * @param sb The StringBuffer containing the characters to be swapped.
     * @param base The base index for the swap.
     * @param pos1 The position of the first character to be swapped.
     * @param pos2 The position of the second character to be swapped.
     */
    public static void swapChars(StringBuffer sb, int base, int pos1, int pos2) {
        char temp = sb.charAt(base + pos1); // Store the character at pos1 in a temporary variable
        sb.setCharAt(base + pos1, sb.charAt(base + pos2)); // Set the character at pos1 to the character at pos2
        sb.setCharAt(base + pos2, temp); // Set the character at pos2 to the character stored in the temporary variable
    }

    /**
     * Displays a loading animation for enhanced user experience.
     *  
     * @param message The message to be displayed before the loading animation.
     * @param max The maximum number of dots that will be displayed in the animation.
     * @param min The minimum number of dots that will be displayed in the animation.
     * @throws InterruptedException Exception: If the thread sleep is interrupted.
     */
    public static void displayLoadingAnimation(String message, int max, int min) throws InterruptedException {
        System.out.print("\n" + message); // Display the input message

        // Calculate the number of dots to be displayed in the animation
        int numberOfDots = (int) Math.floor(Math.random() * (max - min + 1) + min);

        // Display the dots with a delay for better user experience
        for (int i = 0; i < numberOfDots; i++) {
            System.out.print(". ");
            Thread.sleep(500); // Delay of 500 milliseconds between each dot
        }
    }
}