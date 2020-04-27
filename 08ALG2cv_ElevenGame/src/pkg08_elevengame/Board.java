package pkg08_elevengame;

/**
 * hrací stůl
 *
 * @author Václav Kurel
 */
public class Board implements BoardInterface {

    //data ve hře:
    Card[] cards; //9 karet na stole
    Deck deck; //balíček karet

    //konstruktor
    public Board(Card[] cards, Deck deck) {
        this.cards = cards;
        this.deck = deck;
    }

    public Board() {
    }

    @Override
    public String getName() {
        return "Hra jedenáctka";
    }

    @Override
    public int getDeckSize() {
        return deck.getDeckSize();
    }

    @Override
    public int numberOfCards() {
        return cards.length;
    }

    @Override
    public String getCardDescriptionAt(int index) {
        if (cards[index] == null) {
            return "               ";
        }
        return cards[index].toString();
    }

    @Override
    public boolean playAndReplace(String[] selectedCardsPositions) {
        int nPoints = 0;
        int sel[] = new int[selectedCardsPositions.length];
        for (int i = 0; i < sel.length; i++) {
            sel[i] = Integer.parseInt(selectedCardsPositions[i]) - 1;
        }
        if (checkSelectedCardsPositions(sel)) {
            for (int i : sel) {
                nPoints = nPoints + cards[i].getnPoints();
            }
        } else {
            return false;
        }
        if ((nPoints == 1 && sel.length == 2) || (nPoints == 0 && sel.length == 3)) {
            for (int i : sel) {
                cards[i] = deck.dealCard();
            }
            return true;
        }
        return false;
    }

    private boolean checkSelectedCardsPositions(int sel[]) {
        for (int i : sel) {
            if (i < 0 || i > 8) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isAnotherPlayPossible() {
        int nJQK = 0;
        int nEmpty = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                nEmpty++;
            } else {
                if (cards[i].getnPoints() == 0) {
                    nJQK++;
                }
                for (int j = i + 1; j < cards.length; j++) {
                    if (i < cards.length - 1 && cards[j] != null && cards[i].getnPoints() + cards[j].getnPoints() == 1) {
                        return true;
                    }
                }
            }
        }
        return nJQK > 2;
    }

    @Override
    public boolean hasWon() {
        return deck.getnCards() == 0;
    }

    public static Board newBoard() {
        Deck deck = Deck.packOnDeck();
        deck.shuffle();
        Card[] cards = new Card[9];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.dealCard();
        }
        return new Board(cards, deck);
    }
}
