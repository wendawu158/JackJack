import jdk.dynalink.beans.StaticClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class dicts {

    // Gets the values of the cards

    public static int cardToValue(char card) {
        switch (card){
            case 'A':
                return 1;
            case 'K': case 'Q': case 'J': case 'T':
                return 10;
            default:
                return Character.getNumericValue(card);
        }
    }

    // The count of the card

    public static HashMap<Character, Integer> cardCount;

    static {
        cardCount = new HashMap<>();
        cardCount.put('A', 0);
        cardCount.put('K', 0);
        cardCount.put('Q', 0);
        cardCount.put('J', 0);
        cardCount.put('T', 0);
        cardCount.put('9', 0);
        cardCount.put('8', 0);
        cardCount.put('7', 0);
        cardCount.put('6', 0);
        cardCount.put('5', 0);
        cardCount.put('4', 0);
        cardCount.put('3', 0);
        cardCount.put('2', 0);
    }

    public static int handToValue(ArrayList<Character> hand) {
        int handValue = 0;
        boolean acePresent = false;

        for (char card: hand) {
            handValue += dicts.cardToValue(card);
            if (card == 'A') {
                acePresent = true;
            }
        }

        if (acePresent && handValue <= 11) {
            handValue += 10;
        }

        return handValue;
    }

    public static String ArrayListToString(ArrayList a) {
        StringBuilder st = new StringBuilder();

        for (Object i: a) {
            st.append(i.toString());
        }

        return st.toString();
    }

    public static int handToValue(String hand) {
        int handValue = 0;
        boolean acePresent = false;

        for (char card: hand.toCharArray()) {
            handValue += dicts.cardToValue(card);
            if (card == 'A') {
                acePresent = true;
            }
        }

        if (acePresent && handValue <= 11) {
            handValue += 10;
        }

        return handValue;
    }

    public static final ArrayList<Character> SINGLE_DECK = new ArrayList<>(Arrays.asList(
            'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2',
            'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2',
            'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2',
            'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'
    ));

    public static final ArrayList<Character> SINGLE_SUIT = new ArrayList<>(Arrays.asList(
            'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'
    ));
}
