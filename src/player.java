import java.util.ArrayList;

// The player object
// Essentially, each one is used to represent each hand (including the dealer's)

public class player {

    // List of all players that AREN'T the dealer

    public static ArrayList<player> players = new ArrayList<>();

    // Constructor adds players to the player list

    public player() {
        players.add(this);
        this.isDealer = false;
    }

    // Constructor for the dealer (dealer is NOT added to list)

    public player(boolean isDealer) {
        if (!isDealer) {
            players.add(this);
            this.isDealer = false;
        } else {
            this.isDealer = true;
        }
    }

    // If the player object in question is referring to the dealer's hand

    public boolean isDealer;

    // The hand of the player

    public ArrayList<Character> hand = new ArrayList<>();

    // If the player discards their hand (for example if they go bust, or at the beginning of another hand)

    public void newHand() {
        hand = new ArrayList<>();
        handValue = 0;
        softHand = false;
        bust = false;
        standing = false;
    }

    // The current soft value of the hand. Used to calculate if the player has gone bust

    public int handValue = 0;

    // Used to get the maximum value of the hand. Used to calculate if the player wins the showdown

    public int getHandValue() {
        if (softHand) {
            return handValue + 10;
        } else {
            return handValue;
        }
    }

    // Used if the player has a soft hand (i.e. they have an Ace and their hand total is less than 12)

    public boolean softHand = false;

    // Used if the player has gone bust

    public boolean bust = false;

    // Used if the player has expressed a desire to stand

    public boolean standing = false;

    // Used to stand. The handValue is set to the true hand value as the value of the hand can no longer change

    public void stand() {
        standing = true;
        if (softHand) {
            handValue += 10;
            softHand = false;
        }
    }

    // Used to hit another card into the hand

    public char hit() {
        char card = dealer.deal();
        hand.add(card);
        handValue += dicts.cardToValue(card);
        if (handValue > 11) {
            softHand = false;
        } else if (card == 'A') {
            softHand = true;
        }

        if (handValue > 21) {
            bust = true;
        }

        return card;
    }

}
