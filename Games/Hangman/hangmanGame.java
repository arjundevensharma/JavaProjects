import java.util.*;

/** A 2 player game that involves the guessing of a randomly selected word based on 
 *  user-selected genre and difficulty. The game involves a challenge between the computer
 *  and the user, where the computer wins if the user fails to guess 5 of the word's letters. 
 *  However, if the user does guess the word successfully, the computer loses and the user wins.
 *  Further game rules and specifications are described within the program.
 *  @author arsharma 
 **/

public class hangmanGame {
	
	//returns an inputted string with all of its characters accompanied by a space
	public static String outputWithSpaces(String word) {
		String newWord = "";
		
		//the returned word will include a space followed by each of the characters within the inputted word
		for (int i = 0; i < word.length(); i++) {
			newWord += word.charAt(i) + " ";
		}
		return newWord;
	}
	
	//returns an inputted string without any spaces
	public static String outputWithoutSpaces(String word) {
		String newWord = "";
		
		/*the returned word will be identical to the inputted word, except, for every character in the inputted
		 *word containing the character ' ', those characters will be excluded from the returned word */
		for (int i = 0; i < word.length(); i++) {
			/*for each character in the inputted word, if is a space, it will not be returned, else it will be
			*returned in the same order as it is in the inputted word*/
			if (word.charAt(i) != ' ') {
				newWord += word.charAt(i);
			};
		}
		return newWord;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int playAgain = 1;
		
		System.out.println("Welcome to Arjun's Hangman Game!");
		
		boolean correctInput = false;
		int showRules = 0;
		String [] possibleMovieGenreAnswers = {"Avatar", "Titanic", "Gandhi", "Top Gun: Maverick", "Avengers: Endgame", "Frozen", "The Descendants", "Captain America: The Winter Soldier", "Home Alone", "Guardians of the Galaxy", "Shang-Chi and the Legend of the Ten Rings", "Star Wars: The Rise of Skywalker"};
		int[] difficultyMovieGenreAnswers = new int[]{1,1,1,2,2,1,2,3,2,3,3,3}; 
		String [] possibleTVShowGenreAnswers = {"Friends", "Sherlock", "Loki", "Gossip Girl", "Gotham", "Orange Is The New Black", "Grey's Anatomy", "The Woman in the House Across the Street from the Girl in the Window", "Breaking Bad", "Masterchef", "Brooklyn Nine-Nine", "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe"};
		int[] difficultyTVShowGenreAnswers = new int[]{1,1,1,2,1,3,2,3,2,2,3,3}; 
		String [] possibleFamousPeopleGenreAnswers = {"Zendaya", "Arnold Schwarzenegger", "Adele", "LeBron James", "Zach Galifianakis", "Drake", "Michael Jackson", "Dwayne Johnson", "Mohammad Ali", "Mahatma Gandhi", "Maggie Gyllenhaal", "Hayden Panettiere"};
		int[] difficultyFamousPeopleGenreAnswers = new int[]{1,3,1,1,3,1,2,2,2,2,3,3};
		
		/*asks the user to input 1 or 2 to indicate if they would like to see the rules; asks again
		 *if incorrect input is received*/
		while (correctInput == false) {
			System.out.print("\nWould you like to see the rules of 'Hangman' (1 = yes, 2 = no)? ");
			showRules = in.nextInt();
			//if user input is correct, continue to the rest of the code, otherwise repeat the loop
			if (showRules == 1) {//if the user inputted 1, the rules of the game are shown
				correctInput = true;
				System.out.println("\nThe rules are as follows: You, the program user, will try to guess what letters are in a word that I, the computer, have selected.\n"
						+ "If you guess a correct letter, that letter is revealed (i.e. t _ _ _ _ _ _ t _ t _ _ _). If you guess incorrectly, a body part within\n"
						+ "the 'hangman' materializes. You, the guesser, win if you can finish guessing my word without having the 'hangman' gain 5 body\n"
						+ "parts and finish forming his body. If you don't guess the word before having the 'hangman' gain 5 body parts, I, the computer, win.");
			} else if (showRules == 2) {
				correctInput = true;
			} 
		}
		
		//this do-while loop contains the code for the gameplay portion of this program, including the program's play again function
		do {
		String guesses = "";
		int [] traversedList = new int[12];  
		String [] genreList = new String [12];
		String [] difficultyFilteredAnswers = {"", "", "", ""};
		int difficulty = 0;
		int numStrikes = 5;
		int lettersGuessed = 0;
		String newWord = "";
		String inputtedLetter = "";
		boolean correctUserInputGenre = false;
		
		//the user is prompted the choose the genre of the word they would like to guess; incorrect input is accounted for
		while (correctUserInputGenre == false) {
			System.out.print("\nChoose a genre of my word by pressing 1, 2, or 3, and then the enter key (1 = movies, 2 = TV shows, 3 = famous people): ");
			int userChoice = in.nextInt();
			
			//if they input 1, the genre of the word will be related to movies
			if (userChoice == 1) {
				traversedList = difficultyMovieGenreAnswers;
				genreList = possibleMovieGenreAnswers;
				correctUserInputGenre = true;
			} //if they input 2, the genre of the word will be related to TV shows
			else if (userChoice == 2) {
				traversedList = difficultyTVShowGenreAnswers;
				genreList = possibleTVShowGenreAnswers;
				correctUserInputGenre = true;
			} //if they input 3, the genre of the word will be related to famous people
			else if (userChoice == 3) {
				traversedList = difficultyFamousPeopleGenreAnswers;
				genreList = possibleFamousPeopleGenreAnswers;
				correctUserInputGenre = true;
			} //if the user doesn't input 1, 2, or 3, the user is prompted again to choose the genre of the word that they will guess
			else {
				correctUserInputGenre = false;
			}
			
		}
		
		boolean correctUserInputDifficulty = false;
		
		//the user is prompted the choose the difficulty level of the word they would like to guess; incorrect input is accounted for
		while (correctUserInputDifficulty == false) {
			System.out.print("\nChoose the difficulty level of my word by pressing 1, 2, or 3, and then the enter key (1 = easy, 2 = medium, 3 = difficult): ");
			int userChoice = in.nextInt();
			
			//the user's input is stored for future use in selecting the process of selecting a word for the user to guess, if user input is correct
			if (userChoice == 1 || userChoice == 2 || userChoice == 3) {
				difficulty = userChoice;
				correctUserInputDifficulty = true;
			} else {
				correctUserInputDifficulty = false;
			}
			
		}
		
		int filterIndex = 0;

		//stores the four words related to the genre and of the difficulty chosen by the user in an array
		for (int i = 0; i < traversedList.length; i++) {
			//the four index elements from the list of words of the genre chosen by the user, of the difficulty also chosen by the user, are added to a final filtered list 
			if (traversedList[i] == difficulty) {
				difficultyFilteredAnswers[filterIndex] += genreList[i];//for each of the array elements within the difficulty array corresponding to the array of strings adhering to the user-selected genre, the four array elements that adhere to the user-selected difficulty are added to a filtered list
				filterIndex++; //whenever an element is added to this filtered list, this variable keeps track of the fact that the program should add the next element's value to the following index within this filtered list
			}
		}
		
		//randomly chooses the word that will be guessed by the user throughout the game's duration
		String answer = difficultyFilteredAnswers[(int)Math.floor(Math.random()*(4))];
		
		//updates the clue for the hangman game to have underscores instead of letters
		for (int i = 0; i < answer.length(); i++) {
			//all characters in the word that will be guessed by the user that is of the alphabet will be replaced by an underscore within the clue given to the user
			if (Character.isAlphabetic(answer.charAt(i))) {
				newWord += "_";
			} else {
				newWord += answer.charAt(i);
			}
		}
			
		//the following do-while loop contains the gameplay function of the program, excluding the program's play again function
		do {

		boolean letterInWord = false;	
		boolean letterGuessedPrior = false;
		
		System.out.println("\nYour clue: " + outputWithSpaces(newWord) );
		//if no letters have been guessed by the user so far, a custom output message is displayed
		if (guesses.length() != 0) {
		System.out.print("\nLetters used: ");
		
		for (int i = 0; i < guesses.length(); i++) {
			//for every letter that has been guessed, the letter is outputted with a space and a comma, unless it is the last letter that has been guessed
			if (guesses.length() - 1 == i) {
				System.out.println(guesses.charAt(i));
				break;
			} else {
				System.out.print(guesses.charAt(i) + ", ");
			}
		}
		
		} else {
			System.out.println("\nNo letters guessed so far.");
		}
			
			boolean correctLetterInput = false;
			
			//the user chooses a letter as part of their turn in hangman, accounting for incorrect input
			while (correctLetterInput == false) {
				System.out.print("\nPlease input a letter you think is in my word, then press the enter key (or input $ and the enter key to solve the word;\nif your solve is wrong, you will lose immediately): ");
				inputtedLetter = in.next().toLowerCase();
				
				//if the user inputs more than one character, they are prompted to again select a letter
				if (inputtedLetter.length() == 1) {
					correctLetterInput = true;
				}
			}
			//regular gameplay occurs unless the user has chosen to solve the word
			if (inputtedLetter.equals("$")) {
				System.out.print("\nInput your solution:");
		        in.nextLine();
				String guess = in.nextLine();
				//if their guess is correct, output indicates this accordingly, and the do-while loop containing the gameplay function of the program is exited
				if (guess.toLowerCase().equals(answer.toLowerCase() ) ) {
					System.out.print("\nYour solution is correct!");
					break;
				} else {
					numStrikes = 0;
				}
			} else {
				//checks if the inputted letter has already been guessed
				if (guesses.indexOf(inputtedLetter) != -1) {
						letterGuessedPrior = true;
					}
				
				//if the inputted letter hadn't been guessed previously, the program notes that that letter can now be considered one that had been previously guessed
				if (letterGuessedPrior == false) {
				guesses += inputtedLetter.toLowerCase();//to keep track of guessed letters, guessed letters are added to a string
				}
				
				//updates the clue to account for the inputted letter's presence in the answer
				for (int i = 0; i < newWord.length(); i++) {
					//In all of the places within the word guessed by the user in which the letter guessed by the user exists, the clue replaces the pre-existing underscores in these places with the user-inputted letters
					if (newWord.charAt(i) == '_') {
						if (answer.substring(i, i + 1).toLowerCase().equals(inputtedLetter.toLowerCase() )) {
							newWord = newWord.substring(0,i) + answer.charAt(i) + newWord.substring(i + 1);
							letterInWord = true;
							lettersGuessed ++;//the amount of letters guessed is stored and updated for later use in an output statement
						}
					}
				}
				
				//with correct grammar, outputs an indication if the inputted letter is in the word, not in the word, or had been previously guessed
				if (letterInWord == true && letterGuessedPrior == false) {
					System.out.print("\nYes! The answer has ");
					
					//allows for proper grammar in the output statement based on the user-inputted letter
					if(inputtedLetter.equals("a") || inputtedLetter.equals("A")  || inputtedLetter.equals("e") 
					|| inputtedLetter.equals("E")  || inputtedLetter.equals("f")  || inputtedLetter.equals("F")  
					|| inputtedLetter.equals("h")  || inputtedLetter.equals("H")  || inputtedLetter.equals("i") 
					|| inputtedLetter.equals("I")  || inputtedLetter.equals("L")  || inputtedLetter.equals("l") 
					|| inputtedLetter.equals("M")  || inputtedLetter.equals("m")  || inputtedLetter.equals("n") 
					|| inputtedLetter.equals("N")  || inputtedLetter.equals("o")  || inputtedLetter.equals("O") 
					|| inputtedLetter.equals("R")  || inputtedLetter.equals("r")  || inputtedLetter.equals("s") 
					|| inputtedLetter.equals("S")  || inputtedLetter.equals("X")  || inputtedLetter.equals("x") ) {
						System.out.print("an");
					} else {
						System.out.print("a");
					}
					
				System.out.println(" ‘" + inputtedLetter + "’." + " No body parts have been gained by the 'hangman.'");
				
				} else if (letterInWord == false && letterGuessedPrior == false){
					System.out.println("\nSorry, the letter " + inputtedLetter + " is not in the answer. The 'hangman' has gained a body part!");
					numStrikes -= 1;
					//an extra output statement is issued if the user only has one more failed guess before the computer wins and the hangman forms all of its body parts
					if (numStrikes == 1) {
						System.out.println("\nWARNING! If the 'hangman' gains one more body part, you will lose the game! Be careful with this guess.");
					}
				} else {
					System.out.println("\nThe letter " + inputtedLetter + " has been guessed previously. Try again.");
				}	
				
				System.out.println("______\n" + "  |");
				/*outputs the state of the hangman with the correct amount of body parts based on the amount of times the user has failed to guess a letter within the randomly selected word.
				 *After 5 failed letter guesses, the hangman's full body is formed (and the player then loses the game)*/
				switch (numStrikes) {
					case 4:
						System.out.println("  o");
						break;
					
					case 3:
						System.out.println(" _o");
					break;

					case 2:
						System.out.println(" _o_");
						break;
						
					case 1:
						System.out.println(" _o_");
						System.out.println(" /");
						break;
						
					case 0:
						System.out.println(" _o_");
						System.out.println(" / \\");
						break;
						
					default:
						System.out.println("\n[The 'hangman' has no body parts]");
				}
			}
		}
		//if the answer had been guessed or the user has run out of strikes, the gameplay function of the program ends
		while (numStrikes > 0 && newWord.indexOf('_') != -1);
		
		//outputs an indication of if the user won or loss, and personalizes the message to the user if they won based on the number of strikes they had remaining
		if (numStrikes < 1) {
			System.out.println("\nGame over. You lose. The 'hangman' has gained 5 body parts and finished forming its body! Unfortunately, you only guessed " + lettersGuessed + " of my word's " + outputWithoutSpaces(answer).length() + " letters. The answer was " + answer + ".");
		} else {
			System.out.println("\nGame over. You win.");
			if (numStrikes == 5) {
				System.out.println("\nImpressive! You guessed my word without the 'hangman' gaining body parts!");
			} else if (numStrikes == 4) {
				System.out.println("\nSolid work! You guessed my word with the 'hangman' gaining only " + (5 - numStrikes) + " body part!");
			} else {
				System.out.println("\nClose one! You guessed my word after the 'hangman' had already gained " + (5 - numStrikes) + " body parts!");
			} 
		}
		
		boolean correctPlayAgainInput = false;
		
		/*asks the player if they would like to play again: if 2 is inputted, the program ends; if 1 is
		 *inputted, the program re-iterates the do-while loop containing the program's gameplay function.*/
		while (correctPlayAgainInput == false) {
			System.out.print("\nWould you like to play again? 1 = Yes, 2 = No: ");
			playAgain = in.nextInt();
			//if user input is correct, continue to the rest of the code, otherwise repeat the loop
			if (playAgain == 1 || playAgain == 2) {
				correctPlayAgainInput = true;
			}
		}
		
		}
		while (playAgain != 2);
		
	}

}