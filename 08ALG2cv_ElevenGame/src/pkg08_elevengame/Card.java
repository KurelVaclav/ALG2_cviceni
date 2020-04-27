package pkg08_elevengame;

/**
 * reprezentuje kartu, i pro jiné hry
 *
 * @author Václav Kurel
 */
public class Card {

    private String symbol; //suit
    private String value; //rank
    private int nPoints; //A - 1, J,Q,K - 0 // může být i byte

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getnPoints() {
        return nPoints;
    }

    public void setnPoints(int nPoints) {
        this.nPoints = nPoints;
    }

    @Override
    public String toString() {
        return String.format("%7s %2s (%2d)", symbol, value, nPoints);
    }

    private static int valueToPoints(String value) {
        switch (value) {
            case "A":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            default:
                return 0;
        }
    }

}
