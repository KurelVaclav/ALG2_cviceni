package TUL.ALG2;

/**
 *
 * @author Václav Kurel
 */
public class Company extends Client {

    //data
    private String name;

    public Company(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String callName() {
        return "Firma " + name;
    }

}
