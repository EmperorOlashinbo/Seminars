package se.hkr.poker;

class Card {
    Rank rank;
    Suit suit;
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }
    public String toString() {
        return rank + " of " + suit.name();
    }
}

