import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;

public class PokemonCardSorter {
	
		/**
		 * This program is a Pokemon card sorter that allows the user to perform several operations
		 * to manipulate a pokemon card deck.
		 *  
		 * Author: Arjun S.
		 * Version: 4/14/2023
		 */

		// Instance variables
		String name;
		int type; //1-grass, 2-fire, 3-water, 4-electric, etc
		int hp;
		int attack;
		
		// Global variable to store the number of cards in the user's Pokemon deck
		public static int numCards = 0;
		
		public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {
			Scanner in = new Scanner(System.in);
			
			// Create an array to hold the Pokemon cards
			Pokemon[] cards = new Pokemon[100];
			// Populate the array with existing cards from a file
			readFile(cards);
			
			int choice=0;
			
			do{
				// Display menu options
				System.out.println("Pokemon Sorter Main Menu:\n" + "Enter a number from 1 to 6 to perform the following functions:\n" +
		                "1. Add a new Pokemon card\n" + "2. Delete a Pokemon card\n" + "3. Find a Pokemon card\n" +
		                "4. View all Pokemon cards\n" + "5. Find the top 5 Pokemon cards\n" + "6. Exit the program");
				System.out.print("Enter your choice: ");
				choice = in.nextInt();

				if(choice==1){
					// Add card
					System.out.println("\nAdd a new Pokemon card.");
					
					if (cards[99] != null) {  // Check if deck is full
						System.out.println("The deck is full.");
					} else {
						// Prompt user for card information
						System.out.print("Enter the name of the Pokemon (a single word): ");
			            String name = in.next();
			            //Prompt user for pokemon type
			            System.out.print("Enter the type of the Pokemon (0 - Normal, 1 - Fighting, 2 - Flying, 3 - Poison, 4 - Ground,\n" +
			                    "5 - Rock, 6 - Bug, 7 - Ghost, 8 - Steel, 9 - Fire, 10 - Water, 11 - Grass, 12 - Electric,\n" +
			                    "13 - Psychic, 14 - Ice, 15 - Dragon, 16 - Dark, 17 - Fairy): ");
			            int type = in.nextInt();
			            //Prompt user for pokemon HP
			            System.out.print("Enter the HP of the Pokemon: ");
			            int hp = in.nextInt();
			            //Prompt user for pokemon attack power
			            System.out.print("Enter the attack power of the Pokemon: ");
			            int attack = in.nextInt();
						
			            //Add a new pokemon to the user's pokemon deck with the specified type, HP, attack power, and name
						Pokemon newCard = new Pokemon(name, type, hp, attack); //create a new card
						addCard(cards, newCard); //add the new card into the user's database of pokemon cards
						
			            System.out.println("\nThe card has been added successfully.");
					}
				}
				else if(choice==2){  //Delete card
					 System.out.println("\nDelete a Pokemon card.");
			            System.out.print("Enter the name of the Pokemon card you want to delete (case sensitive): ");
			            String name = in.next();
				        // Find the index of the Pokemon to delete.
			            int index = findCard(cards, name);
			            if (index == -1) {
			                System.out.println("\nThe card does not exist in the database."); //error message for incorrect user input
			            } else {
			                deleteCard(cards, name, index); //deletes specified pokemon from user pokemon database
			                System.out.println("\nThe card has been deleted successfully.");
			            }
				}
				else if(choice==3){  //Find card in database
					 System.out.println("\nFind a Pokemon card.");
			            System.out.print("Enter the name of the Pokemon card you want to find (case sensitive): ");
			            String name = in.next(); //store given name
			            int index = findCard(cards, name); //locate index within cards array of the pokemon with the given name
			            if (index == -1) {
			                System.out.println("\nThe card does not exist in the database."); //error message for incorrect user input
			            } else {
			                System.out.println("\nThe card has been found:\n" + cards[index]); //specified card is outputted
			            }
				}
				// If the user chooses option 4, display all cards in the database.
				else if(choice==4){  //Output cards
		            System.out.print("\nOutputting all cards.");
		            //Small loading animation before cards are outputted
		            for (int i = 0; i < 3; i++) {
		    			System.out.print(". ");
		    			Thread.sleep(200);
		    		}
		            System.out.println();
		            //Cards outputted
					outputCards(cards);
				}
				else if(choice==5){  //Find Top 5
				System.out.println("\nFind the Top 5 Pokemon cards in the database.");
					System.out.print("\nEnter the types of the 5 opponent pokemon (0 - Normal, 1 - Fighting, 2 - Flying, 3 - Poison, 4 - Ground,\n" +
		                    "5 - Rock, 6 - Bug, 7 - Ghost, 8 - Steel, 9 - Fire, 10 - Water, 11 - Grass, 12 - Electric,\n" +
		                    "13 - Psychic, 14 - Ice, 15 - Dragon, 16 - Dark, 17 - Fairy): ");
					System.out.println();

					//stores types of opponent pokemon to be factored into top 5 calculation
					int[] oppTypes = new int [5];
					//Populates array with opponent pokemon types
					for (int i = 0; i < oppTypes.length; i++) {
						System.out.print("\nOpponent Pokemon " + (i + 1) + " Type:");
						oppTypes[i] = in.nextInt();
					}
					
					//stores various parameters to be factored into the calculation for higher accuracy
					System.out.print("\nRate the importance of HP (1 - 10): ");
					int hp = in.nextInt();
					System.out.print("Rate the importance of Attack (1 - 10): ");
					int att = in.nextInt();

					System.out.println("\nFinding the top 5 cards");
					//loading animation
					for (int i = 0; i < 3; i++) {
		    			System.out.print(". ");
		    			Thread.sleep(200);
		    		}
		            System.out.println();
		            
		            //uses method to find top 5 based on previously inputted specifications
					
					int[] indexes = findTop5(cards, oppTypes, hp, att);
					
					//top 5 pokemon are outputted
					for (int i = 0; i < indexes.length; i++) {
						System.out.println("\nPokemon #" + (i + 1) + ":");
						System.out.println(cards[indexes[i]].toString());
					}
				}
				else if (choice == 6){
					//exit program
					System.out.print("\nExiting the program");
					//loading animation
		            for (int i = 0; i < 3; i++) {
		    			System.out.print(". ");
		    			Thread.sleep(200);
		    		}
		            System.out.print("\n\nThanks for playing!");
		            //update initially given file with user pokemon deck info with updated user pokemon deck info
					writeFile(cards);
				} else {//Invalid input is accounted for
					System.out.println("\nInvalid input. Please enter a number from 1 to 6.");
					}
				System.out.println();
			}while(choice != 6);
		}
		
		/**

		Adds a new card to the array of Pokemon cards.
		@param cards the array of Pokemon cards
		@param newCard the new Pokemon card to add
		*/
		public static void addCard (Pokemon[] cards, Pokemon newCard) {
			//global number of cards variable updated; within the cards array a given pokemon object is added to the end of the array
			cards[numCards] = newCard;
			numCards++;
		}
		
		/**
	     * Outputs all cards in the given array of Pokemon objects to the console.
	     *
	     * @param cards An array of Pokemon objects.
	     */
		public static void outputCards(Pokemon[] cards){
			//to ensure the global variable of numCards is not altered, a separate variable is used to loop through each card in the user deck
			int numCases = numCards;
			while (numCases --> 0) {
				//each card within the user deck is outputted
				System.out.print(cards[numCases].toString());
			}
			System.out.println();
		}
		
		/**
	     * Searches the given array of Pokemon objects for a Pokemon with the given name and returns its index if found.
	     *
	     * @param cards An array of Pokemon objects.
	     * @param name  The name of the Pokemon to search for.
	     * @return The index of the Pokemon in the array if found, or -1 if not found.
	     */
		public static int findCard(Pokemon[] cards, String name) {
			//to ensure the global variable of numCards is not altered, a separate variable is used to loop through each card in the user deck
	        int numCases = numCards;
	        // Loop through each card in the array of cards in reverse order and check if it has the given name.
	        while (numCases-- > 0) {
	        	//if the given name matches the currently checked pokemon name in the user's deck, the index of this checked pokemon in the user deck is returned
	            if (cards[numCases].name.equals(name)) {
	                return numCases;
	            }
	        }
	        // The Pokemon with the given name was not found in the array.
	        return -1;
	    }
		
		 /**
	     * Deletes the Pokemon with the given name from the given array of Pokemon objects.
	     *
	     * @param cards An array of Pokemon objects.
	     * @param name  The name of the Pokemon to delete.
	     */
	    public static void deleteCard(Pokemon[] cards, String name, int index) {
	    	//the card at the specified index is deleted by being set to a null value
	    	cards[index] = null;
	    	
	    	// Shift all elements in the array that come after the Pokemon to be deleted one index to the left.
	        for (int i = index; i < numCards - 1; i++) {
	            cards[i] = cards[i + 1];
	        }

	        // Update the number of cards global variable
	        numCards--;
	    }
	    /**
	     * Reads Pokemon data from a text file and adds the corresponding Pokemon objects to the given array of Pokemon objects.
	     *
	     * @param cards An array of Pokemon objects.
	     * @throws IOException If an I/O error occurs while reading the file.
	     */
	    public static void readFile(Pokemon[] cards) throws IOException {
	        // Create a Scanner object to read from the input file.
	        Scanner inFile = new Scanner(new File("cardDatabase.txt"));

	        String line; // Each line from the file contains the info for one card.
	        while (inFile.hasNextLine()) {
	            line = inFile.nextLine();
	            String[] fields = line.split(","); // Fields[0]=name, fields[1]=type, etc.

	            // With the file data, which has then been categorized in the fields array, pokemon objects are accordingly added to an array to store the user's pokemon deck
	            Pokemon card = new Pokemon(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));

	            // Add the new Pokemon object to the array of Pokemon objects.
	            addCard(cards, card);
	        }

	        // Close the input file.
	        inFile.close();
	    }
	    
	    /**
	     * Writes the data of the given array of Pokemon objects to a text file.
	     *
	     * @param cards An array of Pokemon objects.
	     * @throws IOException If an I/O error occurs while writing the file.
	     */
		public static void writeFile(Pokemon[] cards) throws IOException, FileNotFoundException{
				// Create a FileWriter object to write to the output file.
				FileWriter out = new FileWriter("cardDatabase.txt");
				BufferedWriter bw = new BufferedWriter(out);
				PrintWriter outFile = new PrintWriter(bw);

				//loop through cards array
				for (int i = 0; i < cards.length; i++) {
					//when there are no more pokemon objects in the array/no more cards in the deck, file writing stops
					if (cards[i] == null) {
						break;
					}
					//formatting each pokemon
					String output = cards[i].name + "," + cards[i].type + "," + cards[i].hp + "," + cards[i].attack;
					outFile.println(output); //writes each pokemon to the original file
				}
				//output each field from the card with a \t in between

				outFile.close();
			}
		/**
		 * Calculates the strength multiplier of a Pokemon's type against the opposing Pokemon's type(s).
		 * @param pokemonType the type of the Pokemon (0-17)
		 * @param oppTypes an array of the types of the opposing Pokemon
		 * @return the strength multiplier of the Pokemon's type against the opposing Pokemon's type(s)
		 */
		public static double strengthMultiplierCalc (int pokemonType, int[] oppTypes){
			
			//type multipliers are stored in an array based on the data given from the following source: https://bulbapedia.bulbagarden.net/wiki/Type#Type_chart9
			double[][] multipliers = {
				    {1, 1, 1, 1, 1, 0.5, 1, 0, 0.5, 1, 1, 1, 1, 1, 1, 1, 1, 1},  // Normal
				    {2, 1, 0.5, 0.5, 1, 2, 0.5, 0, 2, 1, 1, 1, 1, 0.5, 2, 1, 2, 0.5},  // Fighting
				    {1, 2, 1, 1, 1, 0.5, 2, 1, 0.5, 1, 1, 2, 0.5, 1, 1, 1, 1, 1},  // Flying
				    {1, 1, 1, 0.5, 0.5, 0.5, 1, 0.5, 0, 1, 1, 2, 1, 1, 1, 1, 2, 2},  // Poison
				    {1, 1, 0, 2, 1, 2, 0.5, 1, 2, 2, 1, 0.5, 2, 1, 1, 1, 1, 1},  // Ground
				    {1, 0.5, 2, 1, 0.5, 1, 2, 1, 0.5, 2, 1, 1, 1, 1, 2, 1, 1, 1},  // Rock
				    {1, 0.5, 0.5, 0.5, 1, 1, 1, 0.5, 0.5, 0.5, 1, 2, 1, 2, 1, 1, 2, 0.5},  // Bug
				    {0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 0.5, 1},  // Ghost
				    {1, 1, 1, 1, 1, 0.5, 1, 1, 0.5, 0.5, 0.5, 1, 0.5, 1, 2, 1, 1, 2},  // Steel
				    {1, 1, 1, 1, 1, 2, 0.5, 1, 2, 0.5, 0.5, 2, 1, 1, 2, 0.5, 1, 1},  // Fire
				    {1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 0.5, 0.5, 1, 1, 1, 0.5, 1, 1},  // Water
				    {1.0, 1.0, 0.5, 0.5, 2.0, 2.0, 0.5, 1.0, 0.5, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0}, // Grass
				    {1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0}, //Electric
				    {1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 0.0, 1.0}, //Psychic
				    {1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0}, //Ice
				    {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.0}, //Dragon
				    {1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5}, //Dark
				    {1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0}  //Fairy
			};
			
			double sum = 0.0; 
			
			//the individual multiplier values between a given pokemon and the opponent types are added up
			for (int i = 0; i < oppTypes.length; i++) {
				sum+= multipliers[pokemonType][oppTypes[i]];
			}
			
			//the average multiplier value between a given pokemon and the opponent types is returned
			return sum / oppTypes.length;
		}
		
		/**
		 * Calculates the type effectiveness of each Pokemon card against the given opponent types.
		 * @param cards An array of Pokemon cards to calculate the type effectiveness of.
		 * @param oppTypes An array of opponent types to calculate the type effectiveness against.
		 * @return An array of doubles representing the type effectiveness of each Pokemon card.
		 */
		public static double[] findPokemonTypeEffectiveness(Pokemon[] cards, int[] oppTypes){
			//stores array that holds the average multiplier value between each of the given pokemon in the user card deck and the 5 opponent types
			double[] typeEff = new double [numCards];
			for (int i = 0; i < numCards; i++) {
				//the array is populated by the average multiplier value between each of the pokemon in the user deck and the 5 given opponent pokemon type 
				typeEff[i] = strengthMultiplierCalc(cards[i].type, oppTypes);
			}
			
			return typeEff;
		}
		
		/**
		 * Finds the top 5 Pokemon cards based on their type effectiveness, attack power, and HP.
		 * @param cards An array of Pokemon cards to evaluate.
		 * @param oppTypes An array of opponent types to calculate the type effectiveness against.
		 * @param attPriority An int representing the priority given to attack power.
		 * @param HPPriority An int representing the priority given to HP.
		 * @return An array of ints representing the indices of the top 5 Pokemon cards.
		 */
		public static int[] findTop5(Pokemon[] cards, int[] oppTypes, int attPriority, int HPPriority){
			//stores an array that holds the stat value of each pokemon
			//Initialized with the average multiplier value between all of the pokemon cards in the user's deck and the given 5 opponent pokemon types
			double[] stats = findPokemonTypeEffectiveness(cards, oppTypes);
			//based on user specified priority on attack power and hp, the stat value of each pokemon is adjusted accordingly
			for (int i = 0; i < stats.length; i++) {
				//each pokemon's attack power is added to the stat value of each pokemon, magnified by user-specified priority on attack power
				stats[i] *= cards[i].attack * attPriority;
				//each pokemon's HP is added to the stat value of each pokemon, magnified by user-specified priority on HP
				stats[i] += cards[i].hp * HPPriority;
			}
			
			// Initialize an element list to hold the stat value of each pokemon and their indexes in the cards user pokemon databse
			List<Element> elements = new ArrayList<Element>();
			for (int i = 0; i < stats.length; i++) {
				//the element list is populated with the stat value of each pokemon, as well as their index in the cards array
			    elements.add(new Element(i, stats[i]));
			}

			//the list is sorted in terms of the stat value of each pokemon, in descending order
			Collections.sort(elements);
			Collections.reverse(elements); 

			//the indexes of the first five pokemon in the list (the five with the highest stat values) are stored in an array
			int[] topFive = new int [5];
			
			int count = 0;
			for (Element element: elements) {
				topFive[count] = (int)element.index;
				count ++;
				if (count >= topFive.length) { //once the top five pokemon have been stored, the indices of these top 5 are retrned
					return topFive;
				}
			}
			
			return topFive;
		}
}

/**
 * Represents an element with an index and a value.
 */
class Element implements Comparable<Element> {

    double index, value;

    /**
     * Initializes an Element with an index and a value.
     * @param index A double representing the index of the element.
     * @param value A double representing the value of the element.
     */
    Element(double index, double value){
        this.index = index;
        this.value = value;
    }

    /**
     * Compares this Element to another Element.
     * @param e The other Element to compare to.
     * @return An int representing the comparison result.
     */
    public int compareTo(Element e) {
        return (int) (2 * this.value - 2 * e.value);
    }
}