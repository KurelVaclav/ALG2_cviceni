package hurricane;

/**
 *
 * @author Václav Kurel
 */
public interface HurricaneInterface {

    public void loadHurricanes();

    public String info(int yearFrom, int yearTo);

    public String info(String name);

    public String infoSortedBySpeed();
    
}
