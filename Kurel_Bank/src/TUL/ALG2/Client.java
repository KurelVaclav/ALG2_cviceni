package TUL.ALG2;

import java.util.ArrayList;

/**
 *
 * @author Václav Kurel
 */
public abstract class Client {

    //data
    private String name;
    private ArrayList<Account> accounts = new ArrayList<>();

    //konstruktor
    public Client(String name) {
        this.name = name;
    }

    //metody
    public void newAccount(double sum) {
        this.accounts.add(new Account(sum));
    }

    public double sumAll() {
        double sum = 0;
        for (Account account : accounts) {
            sum += account.getSum();
        }
        return sum;
    }
    
    public abstract String callName();

}
