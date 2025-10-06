import java.util.*;

// The machine to play BlackJack

public class JackJack {

    // The main function
    /*
    public static char determinePlay(ArrayList<Character> seenCardsArray, int deckSize, player p, player d) {
        double bustChance = bustProbability(seenCardsArray, deckSize, p);

    }
    */

    // How likely is the next card to bust us

    public static double bustProbability(ArrayList<Character> seenCardsArray, int deckSize, player p) {
        return bustProbability(seenCardsArray, deckSize, p.handValue);
    }


    public static double bustProbability(ArrayList<Character> seenCardsArray, int deckSize, int handValue) {

        // The unseen cards (i.e. the cards remaining in the deck)
        HashMap<Character, Integer> unseenCards = getUnseenCards(seenCardsArray, deckSize);
        int cardsUnseen = deckSize * 52 - seenCardsArray.size();

        // What is the smallest card that will bust the hand
        int bustMinimum = 21 - handValue;

        // How many of the remaining cards will bust the hand
        int bustCards = 0;

        for (char card: unseenCards.keySet()) {
            if (dicts.cardToValue(card) > bustMinimum) {
                bustCards += unseenCards.get(card);
            }
        }

        // The probability of the next card busting us
        double bustProbability = (double) bustCards / cardsUnseen;

        System.out.println(unseenCards);

        return bustProbability;
    }

    // Expected value of hitting, then standing

    public static ArrayList<HashMap<String, Integer>> hitEV(ArrayList<Character> seenCardsArray, int deckSize, player p, player d) {
        return hitEV(seenCardsArray, deckSize, p.hand, d.hand);
    }

    public static ArrayList<HashMap<String, Integer>> hitEV(ArrayList<Character> seenCardsArray, int deckSize, ArrayList<Character> playerHand, ArrayList<Character> dealerHand) {

        // The unseen cards
        HashMap<Character, Integer> unseenCards = getUnseenCards(seenCardsArray, deckSize);

        // We count the player cards as unseen, for simplicity
        for (char i: playerHand) {
            unseenCards.put(i, unseenCards.get(i) + 1);
        }

        // The possible runouts
        ArrayList<HashMap<String, Integer>> playerRunouts = new ArrayList<>();

        // A storage variable. How many cards are left?
        HashMap<Character,Integer> unseenStorage;

        // The run being used
        String run;

        // Adding the first hand
        playerRunouts.add(new HashMap<>());
        playerRunouts.get(0).put(dicts.ArrayListToString(playerHand), 1);

        // Are all hands in the current iteration bust?
        boolean allBust = false;

        for (int i = 0; !allBust; i++) {

            playerRunouts.add(new HashMap<>());
            allBust = true;

            for (String runRoot: playerRunouts.get(i).keySet()) {

                if (dicts.handToValue(runRoot) >= 21) {
                    continue;
                } else {
                    allBust = false;
                }

                for (char card: unseenCards.keySet()) {

                    // Resetting our variables
                    unseenStorage = new HashMap<>(unseenCards);

                    for (char handCard: runRoot.toCharArray()) {
                        unseenStorage.put(handCard, unseenStorage.get(handCard) - 1);
                    }

                    if (unseenStorage.get(card) == 0) {
                        break;
                    }

                    run = runRoot + card;

                    playerRunouts.get(i + 1).put(run, unseenStorage.get(card));
                }
            }

        }

        return playerRunouts;

    }

    // Expected value of standing
    public static double standEV(ArrayList<Character> seenCardsArray, int deckSize, player p, player d) {
        return standEV(seenCardsArray, deckSize, p.hand, dealer.play.hand);
    }

    public static double standEV(ArrayList<Character> seenCardsArray, int deckSize, ArrayList<Character> playerHand, ArrayList<Character> dealerHand) {


        // The unseen cards (i.e. the cards remaining in the deck)
        HashMap<Character, Integer> unseenCards = getUnseenCards(seenCardsArray, deckSize);
        unseenCards.put(dealerHand.get(0), unseenCards.get(dealerHand.get(0)) + 1);

        // The total of the player's hand
        int playerTotal = dicts.handToValue(playerHand);

        // The runouts where the dealer could keep going
        ArrayList<HashMap<String, Integer>> dealerRunouts = new ArrayList<>();

        // The runouts where the hand is concluded
        ArrayList<HashMap<String, Integer>> dealerFinalHands = new ArrayList<>();

        // A storage variable. How many cards are left?
        HashMap<Character,Integer> unseenStorage;

        // How many hands are still incomplete?
        int incompleteRunouts;

        // The run being used
        String run;

        // Combinations
        int combinations;

        // If the hand is soft (i.e. has an ace and has a value below 12)
        boolean acePresent;

        // The total number of runs
        int runTotal;

        // The initial card being added
        dealerRunouts.add(new HashMap<>());
        dealerRunouts.get(0).put(String.valueOf(dealerHand.get(0)), 1);

        // Adding an empty HashMap so they match
        dealerFinalHands.add(new HashMap<>());
        dealerFinalHands.get(0).put("Incomplete", 1);


        // Looping through all runouts at the current length of runout, including the dealer's first

        // What does the stuff in the for loop mean?
        // Every HashMap in both dealerRunouts and dealerFinalHands represents a runout/hand of a different length,
        // incrementing as you go along the ArrayLists.
        // This loop will stop when the previous HashMap in dealerRunouts is empty (there are no more continuable hands)
        // This loop will also stop when the handSize reaches 10 cards, as that is incredibly unlikely and will have
        // little to no effect on the probabilities
        for (int i = 0; !dealerRunouts.get(i).isEmpty(); i++) {
            // Adding more containers
            // Risk of memory overload?
            dealerFinalHands.add(new HashMap<>());
            dealerRunouts.add(new HashMap<>());
            incompleteRunouts = 0;

            // Looping for every length. Each loop will iterate for every runout at that length
            for (String runRoot: dealerRunouts.get(i).keySet()){

                // Okay, so for some reason, the java foreach loop does not work if there is
                // "concurrent" modification. This may be useful for future reference.
                // Do we have to do concurrent modification?
                // Looping for every card remaining in the deck
                for (char card: unseenCards.keySet()) {

                    // Resetting our variables
                    unseenStorage = new HashMap<>(unseenCards);
                    acePresent = card == 'A';
                    runTotal = dicts.cardToValue(card);

                    // Basically checking. Is this run possible?
                    for (char handCard: runRoot.toCharArray()) {
                        unseenStorage.put(handCard, unseenStorage.get(handCard) - 1);
                        runTotal += dicts.cardToValue(handCard);
                        if (handCard == 'A') {
                            acePresent = true;
                        }
                    }

                    // Determining the number of combinations with that hand
                    // This is done by determining the number of cards of that card remaining in the deck
                    // BEFORE we take a card of that card type.
                    combinations = unseenStorage.get(card);

                    // If there are no longer any cards of that type remaining, skip this hand
                    if (combinations == 0) {
                        continue;
                    }

                    // If the run is a valid run, then create the run
                    run = runRoot.concat(String.valueOf(card));

                    boolean softHand = false;

                    // Dealing with soft hands
                    if (acePresent && runTotal <= 11) {
                        runTotal += 10;
                        softHand = true;
                    }

                    // Does the dealer hit or stand or have they gone bust??
                    if (
                            (softHand && runTotal == 17) ||
                            runTotal <= 16) {

                        dealerRunouts.get(i + 1).put(run, dealerRunouts.get(i).get(runRoot) * combinations);
                        incompleteRunouts += dealerRunouts.get(i).get(runRoot) * combinations;

                    } else {

                        dealerFinalHands.get(i + 1).put(run, dealerRunouts.get(i).get(runRoot) * combinations);

                    }


                }

            }

            dealerFinalHands.get(i + 1).put("Incomplete", incompleteRunouts);
        }

        // Now comes the issue. How do we convert this into useful information?

        double dealerWin = 0.0;
        double dealerLose = 0.0;
        double dealerDraw = 0.0;

        int handTotal;
        boolean softHand = false;
        int currentLengthDenominator;
        double continuingFraction = 1.0;

        // Loops through every hand
        for (HashMap<String, Integer> hands: dealerFinalHands) {
            currentLengthDenominator = 0;
            for (String hand: hands.keySet()) {
                currentLengthDenominator += hands.get(hand);
            }

            for (String hand: hands.keySet()) {
                if (Objects.equals(hand, "Incomplete")) {
                    continue;
                }


                handTotal = 0;
                for (char handCard : hand.toCharArray()) {
                    handTotal += dicts.cardToValue(handCard);
                    if (handCard == 'A') {
                        softHand = true;
                    }
                }

                if (softHand && handTotal <= 11) {
                    handTotal += 10;
                }

                if (handTotal > 21 || handTotal < playerTotal) {
                    dealerLose += ((double) hands.get(hand) / currentLengthDenominator) * continuingFraction;
                } else if (handTotal == playerTotal) {
                    dealerDraw += ((double) hands.get(hand) / currentLengthDenominator) * continuingFraction;
                } else if (handTotal > playerTotal) {
                    dealerWin += ((double) hands.get(hand) / currentLengthDenominator) * continuingFraction;
                } else {
                    System.out.println("ERROR");
                }
            }

            continuingFraction *= (double) hands.get("Incomplete") / currentLengthDenominator;
        }

        System.out.println(dealerWin);
        System.out.println(dealerDraw);
        System.out.println(dealerLose);

        return dealerLose;

    }

    private static HashMap<Character, Integer> getUnseenCards(ArrayList<Character> seenCardsArray, int deckSize) {
        // The remaining cards
        HashMap<Character, Integer> unseenCards = new HashMap<>(dicts.cardCount);

        // Counting which cards have already been seen
        for (char card: dicts.SINGLE_SUIT) {
            unseenCards.put(card, deckSize * 4);
        }

        for (char card: seenCardsArray) {
            unseenCards.put(card, unseenCards.get(card) - 1);
        }

        return unseenCards;
    }

}
