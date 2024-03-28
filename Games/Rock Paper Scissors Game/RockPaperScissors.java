import java.util.*;

public class RockPaperScissors {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Welcome to Rock, Paper, Scissors");
		
		System.out.print("Enter your throw (1=Rock, 2=Paper, 3=Scissors): ");
		int inputtedThrow = in.nextInt();
		
		String playerThrowTextForm = "";
		
		switch (inputtedThrow) {
		  case 1:
			playerThrowTextForm = "ROCK";
		    break;
		  case 2:
			playerThrowTextForm = "PAPER";
		    break;
		  case 3:
			playerThrowTextForm = "SCISSORS";
			break;
		}
		
		int computerThrow = (int)(Math.random()*(3)+1);
		String computerThrowTextForm = "";
		
		switch (computerThrow) {
		  case 1:
			computerThrowTextForm = "ROCK";
		    break;
		  case 2:
			computerThrowTextForm = "PAPER";
		    break;
		  case 3:
			computerThrowTextForm = "SCISSORS";
			break;
		}
		
		System.out.println("Player throws " + playerThrowTextForm + ".");
		System.out.println("Computer throws " + computerThrowTextForm + ".");
		
		if ( (computerThrow == 1 && inputtedThrow == 2) || 
		     (computerThrow == 2 && inputtedThrow == 3) || 
		     (computerThrow == 3 && inputtedThrow == 1) ) 
		{
			
			System.out.print("Player wins!");
			
		} else if ( (computerThrow == 2 && inputtedThrow == 1) || 
				    (computerThrow == 3 && inputtedThrow == 2) ||
				    (computerThrow == 1 && inputtedThrow == 3) ) 
		{
			
			System.out.print("Computer wins!");
			
		} else {
			System.out.print("The computer and player tie!");
		}
	}

}
