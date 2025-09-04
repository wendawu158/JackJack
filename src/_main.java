import java.util.ArrayList;
import java.util.Scanner;

public class _main {
    public static void main(String[] args) {
        dealer.newDeck(8);
        player player1 = new player();

        ArrayList<Character> seenCards = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        String action;

        while (true) {
            seenCards.add(dealer.play.hit());

            System.out.println(dealer.play.hand);
            System.out.println(dealer.play.getHandValue());

            seenCards.add(player1.hit());
            seenCards.add(player1.hit());

            while (!player1.standing){
                System.out.println(player1.hand);
                System.out.println(player1.getHandValue());
                if (player1.bust) {
                    System.out.println("bust");
                    break;
                }

                action = input.nextLine();

                if (action.equalsIgnoreCase("h")) {
                    seenCards.add(player1.hit());
                } else {
                    player1.stand();
                }
            }

            while (dealer.play.getHandValue() < 17) {
                seenCards.add(dealer.play.hit());
                System.out.println(dealer.play.hand);
                System.out.println(dealer.play.getHandValue());
                if (dealer.play.bust) {
                    System.out.println("bust");
                    break;
                }
            }

            System.out.println();
            System.out.println("Seen Cards");
            System.out.println(seenCards);
            System.out.println();
            System.out.println("New Hand");

            dealer.play.newHand();
            player1.newHand();
        }
    }
}
