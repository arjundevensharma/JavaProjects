import java.io.IOException;
import java.util.*;

public class passThePigs{

	/**
	 * Pass the Pigs
	 * A two player game where players roll virtual pigs to score points.
	 * 
	 * @author Arjun S.
	 * @version 1/13/2023
	 */
	
    public static void main(String[] args) throws IOException, InterruptedException{
        //create a scanner object to read input from the user
        Scanner in = new Scanner(System.in);
        
        //array to store the point values of each pig roll
        int [] values = {1,1,5,5,10,15};
        
        //variable to keep track if the user wants to play again
        int playAgain = 1;
        
        //stores an array of the player names
        String [] names = new String [2];
        Arrays.fill(names, "");
        
        //ask for the name of player 1
        for (int i = 1; i <= names.length; i ++) {
            System.out.print("Player " + i + "'s name (1 word):");
            names[i - 1] += in.next();
        }
        
        do{

       	   //array to store the scores of the two players
           int [] bank = new int[2];
           
           //variable to store the total points for the current turn
           int turnTot = 0;

           //variable to keep track of the current player's turn
           int turn = 0;
           
           //variable to store the index of the winner of the game in arrays bank and names
           int winner = -1;
           
            do{
                //output the scores of both players and the current turn total
            	System.out.println();
            	for (int i = 0; i < names.length; i++) {
            		System.out.println(names[i] + " has a total of " + bank[i] + " points.");
            	}
            	
                System.out.println("\nIt is currently " + names[turn] + "'s turn. " + names[turn] + ", roll the pigs by pressing enter.");
                System.in.read();
                for (int i = 0; i < 3; i++) {
                    System.out.print(". ");
                    Thread.sleep(500);
                }
                
                //roll the pigs
                int roll1 = (int)(Math.random()*100+1);
                int pos1 = getPosition(roll1);
                int roll2 = (int)(Math.random()*100+1);
                int pos2 = getPosition(roll2);
                int turnPts = getPoints(pos1, pos2, values);
                //output the results of the roll
                System.out.println("\n\n" + names[turn] + ", you rolled a " + getName(pos1) + " and a " + getName(pos2) + " to receive a total of " + turnPts + " points.");

                //if the roll is worth 0 points, switch to the other player's turn
                if (turnPts == 0) {
                	System.out.println("Your turn has been terminated.");
                    turn = switchTurn(turn);
                    turnTot = 0;
                } 
                //otherwise, add the roll's points to the turn total and ask if the player wants to roll again
                else {
                    turnTot += turnPts;
                    System.out.print("\nWill you roll the pigs again on this turn or deposit the points gained on this turn and allow " + names[switchTurn(turn)] + " to take their turn\n(1 = roll, 2 = deposit)?");
                    int input = in.nextInt();
                    if (input == 2) {
                        //add the turn total to the current player's score
                        bank[turn] += turnTot;
                    	System.out.print("\nYou deposited " + turnTot + " points into your bank!");
                        turn = switchTurn(turn);
                        turnTot = 0;
                    } else {
                        System.out.println("\nIn the current turn, a total of " + turnTot + " points have been gained.");
                    }
                }
                

                //check if either player has reached a score of 100
                if (bank[0] >= 100) {
                    winner = 0;
                }
                if (bank[1] >= 100) {
                    winner = 1;
                }
                
                //if a winner has been determined, announce the winner
                if (winner >= 0) {
                    System.out.println("\n\n" + names[winner] + " has won the game with " + bank[winner] + " points!");
                }

            } while(winner == 0); //loop until a winner is determined
            //ask the user if they want to play again
            System.out.print("Play again? (1 = yes, 2 = no)");
            playAgain = in.nextInt();
        } while (playAgain == 1); //loop until the user chooses not to play again
    }

    /**
     * roll - generates a random number between 1 and 100, representing a roll of the pigs
     * 
     * @return (int) - the result of the roll (1-100)
     */

    public static int roll(){
        return (int)(Math.random()*100+1);
    }

    /**
     * getPosition - Determine the position of the roll in the array 'values' based on the roll number
     * from 1-100 from the function roll()
     * 
     * @param rollVal (int) - result of the roll from function() (1-100)
     * @return (int) - 0-5 (index in array values corresponding to roll's point value)
     */
    
    public static int getPosition(int rollVal){
        if (rollVal <= 28) {
            return 0;
        } else if (rollVal <= 56) {
            return 1;
        } else if (rollVal <= 71) {
            return 2;
        } else if (rollVal <= 86) {
            return 3;
        } else if (rollVal <= 95) {
            return 4;
        } else {
            return 5;
        }
    }

    /**
     * getName - gets the name of the roll based on the roll's index in the array 'values'
     *
     * @param pig (int) - the roll type (0-5)
     * @return (String) - the name of the roll type
     */
    
    public static String getName (int pig) {
        switch (pig) {
            case 0:
            return "Left side";
            case 1:
            return "Right side";
            case 2:
            return "Razorback";
            case 3:
            return "Trotter";
            case 4:
            return "Snouter";
            case 5:
            return "Leaning Jowler";
            default:
            return "Invalid roll";
        }
    }
    
    /**
     * getPoints - gets the total point value of a roll
     *
     * @param pos1 (int) - the roll type of the first pig (0-5)
     * @param pos2 (int) - the roll type of the second pig (0-5)
     * @param values (int[]) - the array mapping point values to the position of a pig derived from getPosition()
     * @return (int) - the total point value of the roll
     */
    public static int getPoints(int pos1, int pos2, int[] values) {
        //return the total point value of the roll
    	if (pos1 == pos2) {
    		return pos1 <= 1 ? 1 : values[pos1] * 4;
    	} else if ( (pos1 == 1 && pos2 == 0) || (pos1 == 0 && pos2 == 1) ){
    		return 0;
    	}
        return values[pos1] + values[pos2];
    }
    
    /**
     * switchTurn - switch turn to the other player
     *
     * @param turn (int) - current turn of the player
     * @return (int) - next turn of the player
     */
    public static int switchTurn(int turn) {
        //change the turn to the other player
        return turn == 0 ? 1 : 0;
    }
}