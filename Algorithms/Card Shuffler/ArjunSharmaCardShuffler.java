package ISC4U;
import java.util.*;

public class ArjunSharmaCardShuffler {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		for (int i = 0; i < 5; i++) {
			ArrayList<Integer> cardDeck = new ArrayList<Integer>(52);
			
			for (int j = 0; j < 52; j++)
				cardDeck.add(j);
			
			for (int j = 0; j < 10; j++) {
				int x;
				do {
                    x = in.nextInt();
                } while (x < 2 || x > 9);
				cardDeck = shuffle(cardDeck, x);
			}
			
			ArrayList<Integer> westDeck = new ArrayList<>();
			
            for (int j = 3; j < cardDeck.size(); j += 4) {
                westDeck.add(cardDeck.get(j));
            }

            Collections.sort(westDeck);

            ArrayList<Integer> spades = new ArrayList<>();
            ArrayList<Integer> hearts = new ArrayList<>();
            ArrayList<Integer> diamonds = new ArrayList<>();
            ArrayList<Integer> clubs = new ArrayList<>();
            
			for (Integer card: westDeck) {
				if (card / 13 == 0)
					clubs.add(card % 13);
				else if (card / 13 == 1)
					diamonds.add(card % 13);
				else if (card / 13 == 2)
					hearts.add(card % 13);
				else
					spades.add(card);
			}
			
			outputFormat(spades, "spades: ");
			outputFormat(hearts, "hearts: ");
			outputFormat(diamonds, "diamonds: ");
			outputFormat(clubs, "clubs: ");
			
			System.out.println();
		}

	}

	public static ArrayList<Integer> shuffle (ArrayList<Integer> deck, int x) {
		ArrayList<Integer> stackA = new ArrayList<Integer>();
		
		for (int i = x - 1; i < deck.size(); i+=x) {
			stackA.add(deck.get(i));
			deck.remove(i);
			i--;
		}
		
		ArrayList<Integer> stackB = new ArrayList<Integer>();
		
		for (int i = 0; i < deck.size(); i+=2) {
			stackB.add(deck.get(i));
			deck.remove(i);
			i--;
		}
		
		ArrayList<Integer> newDeck = new ArrayList<Integer>();

		Collections.reverse(deck);
		
		newDeck.addAll(deck);
		newDeck.addAll(stackA);
		newDeck.addAll(stackB);

		return newDeck;
	}
	
	public static void outputFormat (ArrayList<Integer> suit, String label) {
		
		Collections.reverse(suit);
		
		System.out.print(label);
		
		for (Integer card: suit) {
			
			switch(card%13) {
			case 8:
				System.out.print("T ");
				break;
			case 9:
				System.out.print("J ");
				break;
			case 10:
				System.out.print("Q ");
				break;
			case 11:
				System.out.print("K ");
				break;
			case 12:
				System.out.print("A ");
				break;
			default:
				System.out.print(card + 2 + " ");
			}
		}
	
	}
} 
