package se.hkr.poker.lms;

import java.util.ArrayList;
import java.util.List;

class Hand {
    List<Card> cards;
    public Hand() { cards = new ArrayList<>(); }
    public void addCard(Card card) { cards.add(card); }
    public void evaluate() {
        List<String> highCards = new ArrayList<>();
        List<String> pairs = new ArrayList<>();
        for (Card card : cards) {
            if (card.rank == Rank.ACE || card.rank == Rank.KING || card.rank == Rank.QUEEN ||
                    card.rank == Rank.JACK || card.rank == Rank.TEN) {
                highCards.add(card.toString());
            }
            else if (cards.stream().filter(c -> c.rank == card.rank).count() == 2 && !pairs.contains(card.toString())) {
                pairs.add(card.toString());
            }
        }
        System.out.println("Evaluation Result:");
        if (!highCards.isEmpty()) {
            System.out.println("High Cards: " + String.join(",", highCards));
        }
        else {
            System.out.println("High Cards: None");
        }
        if (!pairs.isEmpty()) {
            System.out.println("Pair: " + String.join(",", pairs));
        }
        else {
            System.out.println("Pair: None");
        }
    }
}
