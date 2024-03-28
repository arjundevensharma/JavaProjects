package ISC4U;

import java.io.*;
import java.util.*;

public class ArjunSharmaCardShuffler3 {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		Scanner in = new Scanner(System.in); 
	    Scanner fileIn = new Scanner(new File("DATA21.txt")); 
	    PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter("output.txt"))); 
	
	    int [] xVals = new int [10];
        int [][] shuffledDeck;

	    while (fileIn.hasNextLine()) { 
	        
	        for (int i = 0; i < xVals.length; i++) {
	        	xVals[i] = Integer.parseInt(fileIn.next());
	        }
	    	
	        shuffledDeck = shuffleDeck(xVals);
	        
	        String formattedData = formatData(shuffledDeck);
	
	        outputFile.println(formattedData);
	    }
	
	    outputFile.close();
	}
	
	public static int [] [] shuffleDeck(int [] xVals) {
		//integrate columns and row lengths of arrays
		
		int [][] cardVals = new int [52][2];
		
		for (int i = 0; i < 13; i++) {
			for (int j = 1; j <= 4; j++) {
				
				cardVals[i + (13*(j-1))][0] = i + 2;
				cardVals[i + (13*(j-1))][1] = j;
			}
		}
		
		int [][] shuffledCardVals = new int [52][2];

		for (int i = 0; i < xVals.length; i++) {
			
			int ind = 0;
			
			//first part
			
			int lastNum = 51;
			
			if (xVals[i] % 2 != 0) {
				lastNum = 50;
			}
			
			for (int j = lastNum; j >= 0; j -= 2) {
				
				int var = j + 1;
				int var2 = xVals[i];
				if ( var % var2 == 0) {
					j--;
				}
				shuffledCardVals[ind][0] = cardVals[j][0];
				shuffledCardVals[ind][1] = cardVals[j][1];
				ind++;
			}
			
			//stackA
			for (int j = xVals[i] - 1; j < 52; j+= xVals[i]) {
				shuffledCardVals[ind][0] = cardVals[j][0];
				shuffledCardVals[ind][1] = cardVals[j][1];
				ind++;
			}
			
			//last part
			for (int j = 0; j < cardVals.length; j += 2) {
				if ( (j+1) % xVals[i] == 0) {
					j--;
				}
				shuffledCardVals[ind][0] = cardVals[j][0];
				shuffledCardVals[ind][1] = cardVals[j][1];
				ind++;
			}
			
			cardVals = shuffledCardVals;
		}
		
		return shuffledCardVals;
	}
	
	public static String formatData(int [][] shuffledDeck) {

		String output = "";
		
		int [][] suitVals = new int [4][13];
		
		for (int i = 3; i < 52; i += 4) {
		int ind = 0;
		
		while (suitVals[shuffledDeck[i][1]][ind] != 0) {
			ind++;
		}
		suitVals[shuffledDeck[i][1]][ind] = shuffledDeck[i][0];
		}
		
		Arrays.sort(suitVals, (a, b) -> Integer.compare(a[0], b[0]));
		
		for (int i = 3; i >= 0; i++) {
			switch (i) {
			case 0:
				output += "clubs: ";
				break;
			case 1:
				output += "diamonds: ";
				break;
			case 2:
				output += "hearts: ";
				break;
			case 3:
				output += "spades: ";
				break;
			}
			for (int j = 0; j < 13; j++) {
				if (suitVals[i][j] != 0) {
					switch (suitVals[i][j]) {
					case 10:
						output += "T ";
						break;
					case 11:
						output += "J ";
						break;
					case 12:
						output += "Q ";
						break;
					case 13:
						output += "K ";
						break;
					case 14:
						output += "A ";
						break;
					default:
						output += suitVals[i][j];
						break;
					}
				} else {
					break;
				}
			}
		}

		return output;
	}

}

/*package ISC4U;

import java.io.*;
import java.util.*;

public class ArjunSharmaCardShuffler3 {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		Scanner in = new Scanner(System.in); 
	    Scanner fileIn = new Scanner(new File("DATA21.txt")); 
	    PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter("output.txt"))); 
	
	    int [] xVals = new int [10];
        int [][] shuffledDeck;

	    while (fileIn.hasNextLine()) { 
	        
	        for (int i = 0; i < xVals.length; i++) {
	        	xVals[i] = Integer.parseInt(fileIn.next());
	        }
	    	
	        shuffledDeck = shuffleDeck(xVals);
	        
	        String formattedData = formatData(shuffledDeck);
	
	        outputFile.println(formattedData);
	    }
	
	    outputFile.close();
	}
	
	public static int [] [] shuffleDeck(int [] xVals) {
		//integrate columns and row lengths of arrays
		
		int [][] cardVals = new int [52][2];
		
		for (int i = 0; i < 13; i++) {
			for (int j = 1; j <= 4; j++) {
				
				cardVals[i + (13*(j-1))][0] = i + 2;
				cardVals[i + (13*(j-1))][1] = j;
			}
		}
		
		int [][] shuffledCardVals = new int [52][2];

		for (int i = 0; i < xVals.length; i++) {
			
			int ind = 0;
			
			//first part
			
			int lastNum = 51;
			
			if (xVals[i] % 2 != 0) {
				lastNum = 50;
			}
			
			for (int j = lastNum; j >= 0; j -= 2) {
				
				int var = j + 1;
				int var2 = xVals[i];
				if ( var % var2 == 0) {
					j--;
				}
				shuffledCardVals[ind][0] = cardVals[j][0];
				shuffledCardVals[ind][1] = cardVals[j][1];
				ind++;
			}
			
			//stackA
			for (int j = xVals[i] - 1; j < 52; j+= xVals[i]) {
				shuffledCardVals[ind][0] = cardVals[j][0];
				shuffledCardVals[ind][1] = cardVals[j][1];
				ind++;
			}
			
			//last part
			for (int j = 0; j < cardVals.length; j += 2) {
				if ( (j+1) % xVals[i] == 0) {
					j--;
				}
				shuffledCardVals[ind][0] = cardVals[j][0];
				shuffledCardVals[ind][1] = cardVals[j][1];
				ind++;
			}
			
			cardVals = shuffledCardVals;
		}
		
		return shuffledCardVals;
	}
	
	public static String formatData(int [][] shuffledDeck) {

		String output = "";
		
		int [][] suitVals = new int [4][13];
		
		for (int i = 3; i < 52; i += 4) {
		int ind = 0;
		
		while (suitVals[shuffledDeck[i][1]][ind] != 0) {
			ind++;
		}
		suitVals[shuffledDeck[i][1]][ind] = shuffledDeck[i][0];
		}
		
		Arrays.sort(suitVals, (a, b) -> Integer.compare(a[0], b[0]));
		
		for (int i = 3; i >= 0; i++) {
			switch (i) {
			case 0:
				output += "clubs: ";
				break;
			case 1:
				output += "diamonds: ";
				break;
			case 2:
				output += "hearts: ";
				break;
			case 3:
				output += "spades: ";
				break;
			}
			for (int j = 0; j < 13; j++) {
				if (suitVals[i][j] != 0) {
					switch (suitVals[i][j]) {
					case 10:
						output += "T ";
						break;
					case 11:
						output += "J ";
						break;
					case 12:
						output += "Q ";
						break;
					case 13:
						output += "K ";
						break;
					case 14:
						output += "A ";
						break;
					default:
						output += suitVals[i][j];
						break;
					}
				} else {
					break;
				}
			}
		}

		return output;
	}

}
*/