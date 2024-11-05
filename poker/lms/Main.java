package se.hkr.poker.lms;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Hand hand = new Hand();
        for (int i = 0; i < 5; i++) {
            Card drawnCard = deck.drawCard();
            hand.addCard(drawnCard);
        }
        System.out.println("--- Poker Hand Evaluation ---");
        System.out.println("Your Hand:");
        for (Card card : hand.cards) {
            System.out.println(card);
        }
        hand.evaluate();
    }
}