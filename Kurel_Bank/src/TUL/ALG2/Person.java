package TUL.ALG2;

/**
 *
 * @author Václav Kurel
 */
public class Person extends Client {

    private String name;

    public Person(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String callName() {
        String ova = name.substring(name.length() - 3);
        if ("ova".equals(ova)) {
            return "paní " + name;
        } else {
            return "pan " + name;
        }
    }

}
