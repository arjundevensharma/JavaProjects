package ISC4U;

import java.io.*;
import java.util.*;

public class ArjunSharmaCardShuffler2 {

    private static final String[] CARD_SYMBOLS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K" };
    private static final String[] SUIT_SYMBOLS = { "clubs", "diamonds", "hearts", "spades" };

    public static void main(String[] args) throws IOException {
        try (Scanner fileIn = new Scanner(new File("DATA21.txt"));
             PrintWriter outputFile = new PrintWriter("output.txt")) {

            while (fileIn.hasNextLine()) {
                int[] xVals = new int[10];
                for (int i = 0; i < xVals.length; i++) {
                    xVals[i] = Integer.parseInt(fileIn.next());
                }

                ArrayList<Integer> shuffledDeck = shuffleDeck(xVals);
                String formattedData = formatData(shuffledDeck);

                outputFile.println(formattedData);
            }
        }
    }

    public static ArrayList<Integer> shuffleDeck(int[] xVals) {
        ArrayList<Integer> cardDeck = new ArrayList<>();
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 0; rank < 13; rank++) {
                cardDeck.add((rank + 2) * 10 + suit);
            }
        }

        ArrayList<Integer> newCardDeck = new ArrayList<>();
        for (int i = 0; i < xVals.length; i++) {
            int[] stackA = new int[52 / xVals[i]];
            int ind = 0;
            for (int j = xVals[i] - 1; j < cardDeck.size(); j += xVals[i]) {
                stackA[ind] = cardDeck.get(j);
                cardDeck.remove(j);
            }

            ArrayList<Integer> stackB = new ArrayList<>();
            for (int j = 1; j < cardDeck.size(); j += 2) {
                stackB.add(cardDeck.get(j));
            }
            Collections.reverse(stackB);

            newCardDeck.addAll(stackB);
            
            for (int j = 0; j < stackA.length; j++) {
	        	newCardDeck.add(stackA[j]);
	        }
            
            for (int j = 0; j < cardDeck.size(); j += 2) {
				newCardDeck.add(cardDeck.get(j));
			}

            cardDeck = newCardDeck;
            newCardDeck = new ArrayList<>();
        }

        return cardDeck;
    }

    public static String formatData(ArrayList<Integer> shuffledDeck) {
        ArrayList<String>[] suits = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            suits[i] = new ArrayList<>();
        }

        for (int card : shuffledDeck) {
            int suit = card % 10;
            int rank = card / 10;

            String symbol = CARD_SYMBOLS[rank - 2];
            suits[suit].add(symbol);
        }

        StringBuilder formattedData = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            formattedData.append(SUIT_SYMBOLS[i]).append(": ").append(String.join(" ", suits[i])).append(" ");
        }

        return formattedData.toString().trim();
    }
}
