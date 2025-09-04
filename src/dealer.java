import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// The dealer object
// Static, as there is only one dealer

public class dealer {

    // The player object belonging to the dealer

    public static player play = new player(true);

    // A single deck, without suits

    private static final ArrayList<Character> SINGLE_DECK = dicts.SINGLE_DECK;

    // The number of decks used in the game

    public static int deckSize = 1;

    // The deck being used

    private static ArrayList<Character> deck;

    // Getting the deck

    public static ArrayList<Character> getDeck() {
        return deck;
    }

    // Resetting the deck

    public static void newDeck(int size) {
        deckSize = size;
        deck = new ArrayList<>();
        deckPointer = 0;
        for (int i = size; i > 0; i--) {
            deck.addAll(SINGLE_DECK);
        }
        shuffle();
    }

    // Resetting the deck, but quickly

    public static void newDeck() {
        newDeck(deckSize);
    }

    // Ensuring the program always starts off with a shuffled deck

    static {
        newDeck();
    }

    // Shuffling the deck

    private static void shuffle() {
        Random rand = new Random();
        int j;
        char temp;

        for (int i = deck.size() - 1; i > 0; i--) {
            j = rand.nextInt(i + 1);
            temp = deck.get(j);
            deck.set(j, deck.get(i));
            deck.set(i, temp);
        }
    }

    // Which card is next?

    private static int deckPointer = 0;

    // Return a single card

    public static char deal() {
        char card = deck.get(deckPointer);
        deck.set(deckPointer, '0');
        deckPointer++;
        return card;
    }
}
