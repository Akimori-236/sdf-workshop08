package carddeck;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class DeckOfCards {
    public static Random rand = new SecureRandom();
    public static void main(String[] args) {
        LinkedList<Card> deck = Card.generateDeck();

        // randomDraw(deck);
        // displayCards(drawHand(5, deck));
        displayCards(deck);
        deck = shuffle(deck);
        displayCards(deck);

        Optional<Card> opt = drawOne(deck);
        if (opt.isPresent()) {
            Card c = opt.get(); // get card out from the box
            System.out.println(c.getSuit());
        }
        opt.ifPresent(c -> System.out.println(c.getSuit())); // DOES THE SAME THING
        
    }


    public static Card drawRandom(LinkedList<Card> deck) {
        
        int r = rand.nextInt(deck.size());
        Card c = deck.get(r);
        deck.remove(r);
        System.out.println("You have drawn a " + c.getName() + " of " + c.getSuit());
        return c;
    }

    public static LinkedList<Card> drawHand(int handSize, LinkedList<Card> deck) {
        LinkedList<Card> hand = new LinkedList<>();

        // Stream
        IntStream.range(0, handSize)
                .forEach(i -> {
                    hand.add(drawRandom(deck));
                });
        return hand;
    }

    public static void displayCards(LinkedList<Card> hand) {
        System.out.println("---HAND---");
        for (Card card : hand) {
            System.out.println(card.getName() + " of " + card.getSuit());
        }
        System.out.println("---END OF HAND---");
    }

    public static LinkedList<Card> shuffle(LinkedList<Card> deck) {
        LinkedList<Card> shuffled = new LinkedList<>();
        
        while (deck.size() != 0) {
            int r = rand.nextInt(deck.size());
            shuffled.add(deck.get(r));
            deck.remove(r);
        }
        return shuffled;
    }

    public static Optional<Card> drawOne(LinkedList<Card> deck) {
        if (deck.size() > 0) {
            Card c = deck.remove(0);
            return Optional.of(c); // return box with card inside
        } else {
            // return null; // DANGER
            return Optional.empty(); // return empty box
        }
    }
}
