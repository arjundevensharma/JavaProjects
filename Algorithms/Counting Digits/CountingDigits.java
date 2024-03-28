package ISC4U;

import java.util.*;

public class CountingDigits {

    /**
     * This program counts the number of digits used to number a range of pages,
     * the range of which is defined by user input.
     * 
     * Fun fact: this program's core functionality can be written in 6 lines of code total!
     * 
     * @author Arjun Sharma
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);

        // Output messages to welcome user, describe program functionality, and provide context for input, giving time for the user to read each message
        delayedOutput("Welcome to Arjun's Page Digit Counter!", 0.5);
        delayedOutput("This program will calculate the number of digits required to number a range of pages between two page numbers that you will provide (inclusive).", 1.5);
        delayedOutput("\nTo define the range of pages being analyzed for the calculation...", 1);

        // Get user input: two page numbers forming the range of pages to be calculated on to find total digits making up range, ensuring valid input with separate function
        int[] inputtedPages = getInput(in);

        // Calculate total number of digits in user-defined page range
        int totDigits = calculatePageNumDigits(inputtedPages);

        // Display loading animation for increased usability
        displayLoadingAnimation(3, 1);

        // Display the results
        System.out.println("\n\nThere are " + totDigits + " digits used to number the " + (inputtedPages[1] - inputtedPages[0] + 1) + " pages.");
    }

    /**
     * Delays the output of the given text for a specified number of seconds.
     *
     * @param text The text to be outputted.
     * @param secDelay The number of seconds to delay the output.
     * @throws InterruptedException Exception: if the thread is interrupted while sleeping.
     */
    public static void delayedOutput(String text, double secDelay) throws InterruptedException {
        // Display the text
        System.out.println(text);
        // Delay the execution for specified # seconds (Thread.sleep takes a millisecond value as the parameter, so the necessary unit conversion is done)
        Thread.sleep((int)(secDelay * 1000));
    }
    
    /**
     * Gets the start and end page numbers from the user.
     * 
     * @param in The Scanner object for reading input.
     * @return Array containing the start and end page numbers.
     */
    public static int[] getInput(Scanner in) {
        // Array to store the start and end page numbers defining the range in which calculations are done on
        int[] inputtedPages = new int[2];

        // Loop until two valid inputs are received, store valid inputs in previous array
        for (int i = 0; i < 2;) {
            // Prompt input
            System.out.print("\nPlease input page " + (i + 1) + " of 2 of the page numbers that will form the range of page numbers on which this calculation is done (min 1, max 10000000): ");
            try {
                // If the input is an integer...
                inputtedPages[i] = Integer.parseInt(in.nextLine());
                // Check if input is within problem specified range of 1-10000000 (that is how many pages are supposedly in the book)
                if (inputtedPages[i] > 0 && inputtedPages[i] <= 10000000) {
                    // If both these conditions are met, the valid input counter is incremented
                    i++;
                } else {
                    // If input is an integer but not in specified range, notify user of invalid input
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                // If input is not an integer, notify user of invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // If the first inputted page # is bigger than the second, switch the two values for ease in handling inputs in calculations
        if (inputtedPages[0] > inputtedPages[1]) {
            // Swap the start and end pages if needed
            int temp = inputtedPages[0];
            inputtedPages[0] = inputtedPages[1];
            inputtedPages[1] = temp;
        }

        return inputtedPages; // Return the page range
    }

    /**
     * Calculates the total number of digits needed for page numbering within a user-defined page range (only 3 lines of code!).
     * 
     * @param inputtedPages Array containing the start and end page numbers.
     * @return The total number of digits required.
     */
    public static int calculatePageNumDigits(int[] inputtedPages) {
        // Initialize the total digits counter
        int totDigits = 0;

        // Update the total digits count using an efficient iterative approach that iterates through groups of numbers with the same digits rather than singular numbers
        for (int i = 1; i <= inputtedPages[1]; i *= 10) {
        	// Increment the totDigits count for every number, lower or equal to the end page #, that has a digit count ranging from 1 to the number of digits in the end page
        	// Continue this process, but start from an increasing lower limit of the digit count each time
        	// Keep incrementing totDigits in this process until this lower limit exceeds the number of digits in the end page
        	// Throughout this process, totDigits is not incremented for the numbers that have a digit count between the lower and upper limits but are lower than the start page # of the defined range
        	totDigits += (inputtedPages[1] - i + 1) - Math.max(0, inputtedPages[0] - i);
        }
        
        // Return the totDigits count amassed from previously described iterative approach
        return totDigits; 
    }

    /**
     * Displays a loading animation for enhanced user experience.
     *  
     * @param max The maximum number of dots that will be displayed in the animation.
     * @param min The minimum number of dots that will be displayed in the animation.
     * @throws InterruptedException Exception: If the thread sleep is interrupted.
     */
    public static void displayLoadingAnimation(int max, int min) throws InterruptedException {
        // Begin the loading message
        System.out.print("\nCalculating");

        // Randomly pick a number in the range formed between user-inputted max + min vals to be the number of dots displayed in the animation
        int numberOfDots = (int) Math.floor(Math.random() * (max - min + 1) + min);

        // Display the previously described randomized number of dots with a delay in between the display of each dot for better usability/user experience
        for (int i = 0; i < numberOfDots; i++) {
            System.out.print(". ");
            Thread.sleep(500);
        }
    }
}