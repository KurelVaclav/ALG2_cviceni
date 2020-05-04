package cmd;

/**
 *
 * @author Václav Kurel
 */
public class Parser { //rozliším ho od ostatních již implementovaných názvem balíčku

    public static Command parse(String line) { //dir -e .java
        //
        String[] p = line.split(" +"); // rozparsuje jeden či více mezer
        //command je abstraktní a pod ní bude třída dir, mkdir, help...
        // p[0] = dir p[1] = -e p[2] = .java "to zadal uživatel"
        //TODO: prázdný příkaz ošetřit
        
        char first = Character.toUpperCase(p[0].charAt(0)); //D
        String name = Command.CMMAND_PACKAGE + "." + first + p[0].substring(1); //cmd.Dir
        //ošetření vyjímky: chytím ji ale nebude mě otravovat - z povinně ošetřených vyjímek udělám nepovinně
        try {
            Class c = Class.forName(name); //tovární metoda
            Command command = (Command) c.newInstance(); //přetypovat c na Command "jak vytvořit z názvu objekt"
            command.setParams(p);
            return command;
        } catch (Exception e) {
            throw new RuntimeException("The command could not be parsed!");
        }
    }

}
