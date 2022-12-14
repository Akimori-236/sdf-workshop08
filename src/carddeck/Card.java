package carddeck;

import java.util.LinkedList;

public class Card {
    public static final String[] suits = { "Spades", "Hearts", "Clubs", "Diamonds" };
    private final int num;
    private final String suit;

    public Card(int num, String suit) {
        this.num = num;
        this.suit = suit;
    }

    public int getNum() {
        return this.num;
    }

    public String getSuit() {
        return this.suit;
    }

    public String getColour() {
        if (this.suit.equalsIgnoreCase("spades") || this.suit.equalsIgnoreCase("clubs")) {
            return "Black";
        } else {
            return "Red";
        }
    }

    public String getName() {
        if (this.num == 1) {
            return "Ace";
        } else if (this.num == 11) {
            return "Jack";
        } else if (this.num == 12) {
            return "Queen";
        } else if (this.num == 13) {
            return "King";
        } else {
            return Integer.toString(this.num);
        }
    }

    public static LinkedList<Card> generateDeck() {
        LinkedList<Card> deck = new LinkedList<>();

        for (int i = 1; i <= 13; i++) {
            for (String suit : suits) {
                deck.add(new Card(i, suit));
            }
        }
        System.out.println("Generated deck of " + deck.size() + " cards.");
        return deck;
    }
}
