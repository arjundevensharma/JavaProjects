import java.util.*;
import java.io.*;

public class cipherProject {
	
	/**
	 * The cipherProject class contains the main method and various helper methods
	 * that allow the user to encrypt or decrypt a file using the Vigenère cipher.
	 * The Vigenère cipher is a method of encoding text by “shifting” the letters in a word.
	 * It was originally described by an Italian cryptologist (Bellaso) in 1553. Vigenère
	 * published his description of a similar cipher in 1586, and Bellaso’s invention was
	 * misattributed to him in the 19th century. It is notoriously difficult to break, and
	 * many have called it “unbreakable,” but some cryptoanalysts could occasionally break it
	 * as early as the 16th century (it was broken entirely in the 19th century).
	 * @author Arjun S.
	 * @version 2/21/2023
	 */
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		Scanner in = new Scanner(System.in);
		
		// Display information about the Vigenère cipher
		System.out.println("Welcome to Arjun's Vigenere Cipher. The Vigenère cipher is a method of encoding text by “shifting” the letters in a word. It was originally\n"
				+ "described by an Italian cryptologist (Bellaso) in 1553. Vigenère published his description of a similar\n"
				+ "cipher in 1586, and Bellaso’s invention was misattributed to him in the 19 th century. It is notoriously\n"
				+ "difficult to break, and many have called it “unbreakable,” but some cryptanalysts could occasionally\n"
				+ "break it as early as the 16 th century (it was broken entirely in the 19 th century).");
		
		System.out.print("\nPlease enter the file name to be encoded:");

		// Get file name from user input
		String filename = in.next();
		Scanner fileIn = new Scanner(new File (filename)); // Create a Scanner object to read from the input file
		
		// Create output file and writer
		FileWriter out = new FileWriter("outFile.txt");
		BufferedWriter bw = new BufferedWriter(out);
		PrintWriter outputFile = new PrintWriter(bw);
		
		// Prompt user to choose encryption or decryption
		System.out.print("Would you like to encrypt or decrypt? (1=encrypt, 2=decrypt)");
		int direction = in.nextInt();
		
		// Get the keyword for the cipher that will be used to encyrpt/decrypt the file
		System.out.print("Please enter a keyword:");
		String key = in.next();
		
		// Process each line of the file for encryption/decryption + to be added to the output file
		while (fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			// Use cipher method to encrypt/decrypt the current line of the file
			String newWord = cipher(line, direction, getKeyArray(key));
			outputFile.println(newWord); 
		} 
		outputFile.close();
		
		// Print a message indicating where the output file can be found
		System.out.print("The output file can be found at outFile.txt in the project directory.");
	}
	
	/**
	 * Shifts a given letter to the right by a given amount, wrapping around to the beginning of the ASCII table if necessary.
	 * 
	 * @param letter the letter to be shifted
	 * @param shift the amount to shift the letter
	 * @return the shifted letter
	 */

	public static char shiftRight (char letter, int shift) {
		int givenChar = (int)letter;
		givenChar += shift;
		if (givenChar > 126) {
	        // if the ASCII value goes beyond the range of printable characters, wrap around to the beginning of the range
			givenChar -= 95;
		}
		return (char) givenChar;
	}
	
	/**
	 * Takes a character and a shift value and returns the result of shifting the character to the left by the specified amount.
	 * @param letter the character to be shifted
	 * @param shift the number of positions to shift the character to the left
	 * @return the shifted character
	 */
	
	public static char shiftLeft (char letter, int shift) {
		int givenChar = (int)letter;
		givenChar -= shift;
		if (givenChar < 32) {
	        // if the ASCII value goes below the range of printable characters, wrap around to the end of the range
			givenChar += 95;
		}
		return (char) givenChar;
	}
	
	/**
	 * Takes a string, a direction (1 for encrypt or 2 for decrypt), and an array of integers representing the key and returns the result of applying the Vigenere cipher to the string using the specified key.
	 * @param word the string to be encrypted or decrypted
	 * @param direction 1 for encryption or 2 for decryption
	 * @param keynums an array of integers representing the key for the cipher
	 * @return the encrypted or decrypted string
	 */
	
	public static String cipher (String word, int direction, int[]keynums) {
		String newWord = "";
		if (direction == 1) {
			//encryption process
			for (int i = 0; i < word.length(); i++) {
				char character = word.charAt(i);
	            // calculate the shift amount for this character based on the key
	            int shift = keynums[i % keynums.length];
	            // apply the shift using the shiftRight method
	            char newChar = shiftRight(character, shift);
	            // add the shifted character to the new string
	            newWord += newChar;
			}
		} else {
			//decryption process
			for (int i = 0; i < word.length(); i++) {
				char character = word.charAt(i);
	            // calculate the shift amount for this character based on the key
	            int shift = keynums[i % keynums.length];
	            // apply the shift using the shiftLeft method
	            char newChar = shiftLeft(character, shift);
	            // add the shifted character to the new string
	            newWord += newChar;
			}
		}
		return newWord;
	}
	
	/**
	 * Takes a string representing the key and returns an array of integers representing the numerical values of the letters in the key.
	 * @param key the key to be converted into an array of integers
	 * @return an array of integers representing the key
	 */
	public static int[] getKeyArray (String key) {
		int[] keyArr = new int [key.length()];
		for (int i = 0; i < key.length(); i++) {
	        // subtract the ASCII value of 'a' to get the numerical value of the letter in the key
			keyArr[i] = key.charAt(i) - 'a';
		}
		return keyArr;
	}
	
}
