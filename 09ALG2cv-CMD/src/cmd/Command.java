package cmd;

import java.io.File;

/**
 * abstract protože: každý objekt bude dělat různě execute
 *
 * @author Václav Kurel
 */
public abstract class Command {

    //pro rozlišení Parseru musím mít název balíčku
    public static String CMMAND_PACKAGE = "cmd";

    //data, protected abych nemusel používat gettery
    protected String[] params; //př.: pole Stringů - 3 prvky |dir|-e|.java|

    //metoda s defenzivní kopií
    public void setParams(String[] params) {
        this.params = new String[params.length];
        System.arraycopy(params, 0, this.params, 0, params.length);
    }

    //metoda pro vyhodnocení příkazu
    public abstract Status execute(File actualDir);
    //tady switch kod když tak udělej... XXX lepší je, přídávat vždy novou třídu, abych někomu nekazil jeho kód
//        switch (params[0]) {
//            case "dir":
//                dir();
//                break;
//            case "cs":
//                cd();
//                break;
//
//        }
    // chci přidávat třídy - vede na abstract třídu!!!
}
