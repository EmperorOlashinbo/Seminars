package se.hkr.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Deck {
    List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }
    public void shuffle() {
        Collections.shuffle(cards); }
    public Card drawCard() {
        return cards.remove(0); }
}
