package ISC4U;
import java.util.*;

public class cardShuffler {

	 /**
     * Simulates the shuffling of a deck of cards and displays the hand of the
     * fourth player in a game played with that deck.
     * 
     * @author Arjun Sharma
     */
	
	public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);

        //Welcome messages, program description
        System.out.println("Welcome to Arjun's Card Shuffler!");
        System.out.println("This program simulates the shuffling of a deck of cards and displays the hand of the fourth player in a game played with that deck.");
        System.out.println("You will be asked to shuffle 5 decks by entering a number (between 2 and 9) 10 times to shuffle each deck.\n");

        //Per problem specifications, loop handles 5 deck of cards
        for (int i = 0; i < 5; i++) {
        	//Arraylist stores pre-shuffled "card deck" consisting of numbers 0-51
            ArrayList<Integer> cardDeck = new ArrayList<Integer>();

            //Populate the card deck with pre-shuffled order of "cards" (0-51)
            for (int j = 0; j < 52; j++) {
                cardDeck.add(j);
            }

            //Contextualize the following 10-number input with a purpose statement (to shuffle the current deck)
            System.out.println("To shuffle deck " + (i + 1) + "...");

            //Nested loop the shuffle the deck 10 times based on user-given shuffle numbers
            for (int j = 0; j < 10; j++) {
                int shuffleNumber; //Variable to store user-inputted shuffle numbers
                do {
                    //Prompt user input, store in previously defined variable
                    System.out.print("\nEnter Number #" + (j + 1) + " (range: 2-9): ");
                    shuffleNumber = in.nextInt();
                    //If input is not within specified range, reprompt for input
                    if (shuffleNumber < 2 || shuffleNumber > 9) {
                        System.out.println("Invalid number. Please enter a number between 2 and 9.");
                    }
                } while (shuffleNumber < 2 || shuffleNumber > 9); //User input reprompted in the case of invalid initial user input

                cardDeck = shuffle(cardDeck, shuffleNumber); //Shuffle the deck as per problem specifications with the user-provided shuffle number
            }
            
            //Array stores fourth player's hand with a fixed length being a fourth of the original shuffled deck's length
            int[] westDeck = new int[cardDeck.size() / 4];

            //Distribute cards to the fourth player
            for (int j = 3; j < cardDeck.size(); j += 4) {
            	//receive every fourth card of shuffled card deck and add in order to westDeck array
                westDeck[(j - 3) / 4] = cardDeck.get(j);
            }

            //Sorts the fourth player's hand using quick sort algorithm
            sort(westDeck, 0, westDeck.length - 1); 

            //Creates an array of ArrayLists to categorize cards by suit
            //4 arraylists- one per suit
            //Concept inspired from Mr. Vojnov's week without walls algorithm
            ArrayList<Integer>[] suits = new ArrayList[4];

            //Initializing the 4 ArrayLists in the suits array
            for (int j = 0; j < suits.length; j++) {
                suits[j] = new ArrayList<Integer>();
            }

            //Distributing cards into the respective suit ArrayLists in the suits array
            for (int card : westDeck) {
            	//Decks initialized so values from 0-12, 13-25, 26-38, 39-51 are of spades, hearts, diamonds, and clubs suits respectively
            	//Given every 13 values correspond to suit, division + modulus used to correctly classify values to each ArrayList
                suits[card / 13].add(card % 13);
            }

            //Output player's hand
            System.out.println("\nWith that shuffled deck, the fourth player received a hand with the following cards in the following suits...");

            Thread.sleep(1000); //Pause a second to build suspense

            String[] labels = {"Clubs: ", "Diamonds: ", "Hearts: ", "\nSpades: "}; //Labels for each suit (more convenient output system to input these along with values into the output function)
            //Printing the cards in each suit of the westDeck in descending order through output function
            for (int j = suits.length - 1; j >= 0; j--) {
                output(suits[j], labels[j]);
            }

            numRummyCombinations(suits); //analyzes the hand for potential Rummy combinations and outputting this

            System.out.println(); //formatting

            Thread.sleep(1000); //Pause another second before going to next deck for suspense/user experience
        }
    }

    /**
     * Shuffles a given deck of cards.
     * 
     * @param deck The deck of cards to be shuffled.
     * @param x The "shuffle number" or interval at which cards are selected into stack A during shuffling as per problem specifications.
     * @return ArrayList<Integer> The shuffled deck.
     */
    public static ArrayList<Integer> shuffle(ArrayList<Integer> deck, int x) {
        ArrayList<Integer> stackA = new ArrayList<Integer>(); //Creating a stack to temporarily hold selected cards of the interval x in the deck
        
        //Select and remove every xth card from the deck
        for (int i = x - 1; i < deck.size(); i += x) {
            stackA.add(deck.get(i)); //add xth card to stackA
            deck.remove(i); //remove from original deck 
            i--;//reduce i so that the removal of the card from the original deck does not result unnecessarily increment i
        }
        
        //Arraylist to be outputted, containing the to be shuffled deck
        ArrayList<Integer> shuffledDeck = new ArrayList<Integer>();

        //Remove every second card from the remaining deck starting from the top of the deck and adding to shuffledDeck in that order (top to bottom)
        for (int i = deck.size() - 1 - (deck.size() % 2); i > 0; i -= 2) {
            shuffledDeck.add(deck.get(i)); //add every second card from top to bottom to new deck
            deck.remove(i); //remove these cards from original
        }

        shuffledDeck.addAll(stackA); //Adding the cards from stackA to shuffledDeck
        shuffledDeck.addAll(deck); //Adding the remaining cards from deck to shuffledDeck

        return shuffledDeck; //Returning the correctly shuffled deck as per problem specifications
    }

    /**
     * Print out the values in a suit as per proper output specifications with labels.
     * 
     * @param suit The ArrayList containing the values corresponding to the cards in a suit.
     * @param label The label for the suit to accompany output.
     */
    public static void output(ArrayList<Integer> suit, String label) {
        if (!suit.isEmpty()) { //The method shouldn't run if the suit doesn't have cards in it
            System.out.print(label); //Start the output by printing the suit label

            //Loop through the cards in reverse order and printing them
            for (int i = suit.size() - 1; i >= 0; i--) {
                switch (suit.get(i)) {
                    case 8:
                        System.out.print("T "); //Printing 'T' for 10
                        break;
                    case 9:
                        System.out.print("J "); //Printing 'J' for Jack
                        break;
                    case 10:
                        System.out.print("Q "); //Printing 'Q' for Queen
                        break;
                    case 11:
                        System.out.print("K "); //Printing 'K' for King
                        break;
                    case 12:
                        System.out.print("A "); //Printing 'A' for Ace
                        break;
                    default:
                        System.out.print((suit.get(i) + 2) + " "); //Print the card number
                        //Since the range of input in the suit ArrayList is (0-51) % 13 or 0-12  increment of 2 necessary because card values start at 2 not 0 as per problem specifications)
                }
            }
        }
    }

    /**
     * Implements the quick sort algorithm to order an array of integers.
     * This method recursively sorts sub-arrays divided around a pivot.
     *
     * @param nums The array to be sorted.
     * @param start The initial index for sorting.
     * @param finish The final index for sorting.
     */
    public static void sort(int[] nums, int start, int finish) {
        if (start < finish) {
            int divideIndex = divide(nums, start, finish); //divide array around pivot which is the last element array, elements bigger than the pivot are on the left of the partition index outputted, smaller elements are on the right

            sort(nums, start, divideIndex - 1); //Sorting the left sub-array (the numbers bigger than pivot)
            sort(nums, divideIndex + 1, finish); //Sorting the right sub-array (the numbers smaller than pivot
        }
    }

    /**
     * Reorganizes an array's elements around a pivot.
     * This is a supporting method for the optimized quick sort algorithm.
     * It functions similarly to the original partition method but uses different variable names.
     *
     * @param nums The array to be rearranged.
     * @param start The beginning index for the reorganization.
     * @param finish The ending index for the reorganization.
     * @return int The position of the pivot element after reorganization.
     */
    public static int divide(int[] nums, int start, int finish) {
        int pivotElement = nums[finish]; //Last element of the array is the "pivot"
        int smallerElementIndex = (start - 1); //Starts search at the first index of the array

        for (int i = start; i < finish; i++) {
            // Move elements smaller than the pivot to the left side.
            if (nums[i] <= pivotElement) {
                smallerElementIndex++;

                //swap elements
                int temp = nums[smallerElementIndex];
                nums[smallerElementIndex] = nums[i];
                nums[i] = temp;
            }
        }
        //place the pivot element after the last smaller element
        int temp = nums[smallerElementIndex + 1];
        nums[smallerElementIndex + 1] = nums[finish];
        nums[finish] = temp;

        return smallerElementIndex + 1;
    }
    
    public static void numRummyCombinations(ArrayList<Integer>[] suits) {
        int potentialCombinations = 0;
        String combinationDescriptions = "";
        String[] suitNames = {"Clubs", "Diamonds", "Hearts", "Spades"};
        List<List<String>> rankToSuitList = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            rankToSuitList.add(new ArrayList<>());
        }

        for (int i = 0; i < suits.length; i++) {
            for (int card : suits[i]) {
                int rank = card % 13;
                rankToSuitList.get(rank).add(suitNames[i]);
            }
        }

        for (int rank = 0; rank < rankToSuitList.size(); rank++) {
            if (rankToSuitList.get(rank).size() >= 3) {
                potentialCombinations++;
                combinationDescriptions += "Set of " + rankToString(rank) + "s in " + rankToSuitList.get(rank) + "\n";
            }
        }

        for (int i = 0; i < suits.length; i++) {
            Collections.sort(suits[i]);
            for (int j = 0; j < suits[i].size() - 2; j++) {
                if (suits[i].get(j + 1) - suits[i].get(j) == 1 && suits[i].get(j + 2) - suits[i].get(j + 1) == 1) {
                    potentialCombinations++;
                    combinationDescriptions += "Sequence in " + suitNames[i] + ": " + rankToString(suits[i].get(j)) + ", " + rankToString(suits[i].get(j + 1)) + ", " + rankToString(suits[i].get(j + 2)) + "\n";
                }
            }
        }

        String describingWord = potentialCombinations > 0 ? "already" : "unfortunately";
        System.out.println("\n\nWith that deck, you would " + describingWord + " have " + potentialCombinations + " combinations possible without playing a single turn in a game of Rummy (my favourite card game).");
        if (potentialCombinations > 0) {
            System.out.print("\nCombinations:\n\n" + combinationDescriptions);
        }
    }

    private static String rankToString(int rank) {
        switch (rank) {
            case 9: return "Jack";
            case 10: return "Queen";
            case 11: return "King";
            case 12: return "Ace";
            default: return String.valueOf(rank + 2);
        }
    }
}