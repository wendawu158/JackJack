import java.util.ArrayList;
import java.util.HashMap;

public class tester {

    public static void main(String[] args) {
        player player1 = new player();

        ArrayList<Character> seenCards = new ArrayList<>();

        dealer.newDeck(1);

        for (int i = 0; i < 5; i++) {
            seenCards.add(dealer.deal());
        }

        seenCards.add(player1.hit());
        seenCards.add(player1.hit());
        seenCards.add(dealer.play.hit());
        System.out.println(player1.hand);
        System.out.println(dealer.play.hand);
        System.out.println(seenCards);
        System.out.println(dealer.getDeck());

        JackJack.standEV(seenCards, 1, player1, dealer.play);

        for (HashMap<String, Integer> i: JackJack.hitEV(seenCards, 1, player1, dealer.play)) {
            System.out.println(i);
        }
    }

}
