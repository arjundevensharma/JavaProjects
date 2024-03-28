import java.util.Scanner;

/** Determines the cost in dollars of an inputted amount of eggs, based on given 
 *  costs for a dozen eggs, which vary based on the amount of egg dozens bought.
 *  @author arsharma 
 **/

public class arjunEggEmporium {	
	
	/*Function that returns a given double value, rounded to two decimal places,
	 * as a string */
	static <value> String decimalRounding(double givenValue) {
			
			//Multiplies given value by 100.0
			double valueTimesOneHundred = (givenValue * 100.0);
			
			//Rounds the product of 100.0 and the given value
			int roundedValueTimesOneHundred = (int) (valueTimesOneHundred + (0.5) );
			
			/*Divides by 100.0 the rounded product of 100 and the given value, thus
			 *storing the given value rounded to either one or two decimal places */
			double roundedValue = roundedValueTimesOneHundred / 100.0;
			
			/*If the rounded version of the given value is only three digits, an extra
			 *0 is returned at the end of it as a string */
			if (roundedValueTimesOneHundred % 10 == 0) {
				return (roundedValue + "0");
			}
			
			/*If the rounded version of the given value is four digits, the rounded
			 *version of the given value is returned as a string */
			return (String.valueOf(roundedValue));
		  }

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//Outputs text to welcome the user to the program
		System.out.println("Welcome to Arjun's Egg Emporium!\n");
		
		/*Prompts the user to enter an integer that is 0 or higher, representing
		 *the # eggs that the user would like */
		System.out.print("Please enter the number of eggs you would like: ");
		
		//Stores inputted value
		int numEggs = in.nextInt();
		
		//Stores the # egg dozens that the inputted value represents
		int numDozens = numEggs / 12;
		 
		//initializes double variable to store the egg price per dozen
		double pricePerDozen = 0;
		
		/*stores price per dozen eggs based on the amount of egg dozens that the
		 *inputted egg amount represents */
		switch (numDozens) {
		  case 0:
		  case 1:
			pricePerDozen = 1.85;
			break;
		  case 2:
		  case 3:
		  case 4:
			pricePerDozen = 1.60;
			break;
		  case 5:
		  case 6:
		  case 7:
			pricePerDozen = 1.52;
			break;
		  case 8:
		  case 9:
		  case 10:
			pricePerDozen = 1.39;
		    break;
		  default:
			pricePerDozen = 1.25;
			break;
		}
		
		//Stores the # extra eggs outside of egg dozens represented by user input
		int numExtraEggs = numEggs % 12;
		
		/*Stores the cost of the eggs outside of egg dozens within the user's inputted
		 *egg amount*/
		double priceExtraEggs = numExtraEggs * (pricePerDozen / 12);
		
		//Stores the cost of the egg dozens within the user's inputted egg amount
		double priceEggDozens = numDozens * pricePerDozen;
		
		//Stores the total cost of the user's inputted egg amount
		double totalPrice = numEggs * (pricePerDozen / 12);
		
		/*Rounds the cost of the egg dozens within the user's inputted egg amount to two
		 *decimal places */
		String priceEggDozensRounded = decimalRounding(priceEggDozens);
		
		/*Rounds the cost of the eggs outside of egg dozens within the user's inputted
		 *egg amount to two decimal places */
		String priceextraEggsRounded = decimalRounding(priceExtraEggs);
		
		//Rounds the total cost of the user's inputted egg amount to two decimal places
		String totalPriceRounded = decimalRounding(totalPrice);
		
		/*Outputs the cost for egg dozens, the cost for eggs outside of egg dozens, and
		 * the total cost for the user's inputted egg amount*/
		System.out.println("\nCost for dozens: $" + priceEggDozensRounded
		+ "\nCost for extra eggs: $" + priceextraEggsRounded
		+ "\n\nYour total cost is: $" + totalPriceRounded);
	}

}
