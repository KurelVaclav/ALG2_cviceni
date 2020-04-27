package pkg08_elevengame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * reprezentuje balíček karet obsahuje pole karet
 *
 * @author Václav Kurel
 */
public class Deck {

    //pole karet - dynamické lepší aby to mohlo fungovat i pro hru 10...
    private List<Card> deckCards;
    private int nCards;

    private Deck(List<Card> deckCards, int nCards) {
        this.deckCards = deckCards;
        this.nCards = nCards;
    }

    public Deck() {
    }

    public void shuffle() {
        Collections.shuffle(deckCards);
    }

    public Card dealCard() {
        if (nCards == 0) {
            return null;
        }
        Card card = deckCards.get(nCards - 1);
        nCards--;
        return card;
    }

    public static Deck packOnDeck() {
        String[] symbols = {"červená", "černá", "žalud", "kule"};
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        List<Card> deckCards = new ArrayList<>();
        for (String symbol : symbols) {
            for (String value : values) {
                Card card = new Card(symbol, value);
                deckCards.add(card);
            }
        }
        return new Deck(deckCards, deckCards.size());
    }

    public int getnCards() {
        return nCards;
    }

}
