package pkg08_elevengame;

/**
 * Interface by měl být na začátku - pro tým je lepší vytvořit interface a rozdělit si úkoly
 * rozhraní mezi logikou a uživatelskym rozhraním
 * @author Václav Kurel
 */
public interface BoardInterface {

    public String getName();

    public int numberOfCards();

    public int getDeckSize();

    public String getCardDescriptionAt(int index);

    public boolean isAnotherPlayPossible();

    public boolean playAndReplace(String[] selectedCardsPositions);

    public boolean hasWon();

}
