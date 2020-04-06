package alg2.cv.digpig;

/**
 * pokladnička vytvořit pojmenovanou prázdnout pokladinčku vytvořit pojmenovanou
 * pokladničku se zadanou vstupní sumou změnit/nastavit pojmenování majitele
 * pokladničky
 *
 * @author MP
 */
public class DigiPig {

    //identigikace dat
    private String jmeno;
    private int nKorun = 0;
    private int nDvouKorun = 0;
    //sumu nahradím metodou getSuma()!!!!!!
//    private int suma = 0; // problém že vždycky musíme přepočítávát sumu :/
    //spočítat jen když se mě někdo zeptá, jaká je suma tak to spočítám :)
    //hned v kontruktoru vypočítat a*b a pak jen vrátím pomocí getteru vrat obsah

    //kontruktory - "vytvořte pokladničku"
    public DigiPig(String jmeno) {
        this.jmeno = jmeno;
    }

    //přetížený kontruktor "overloaded" - "vytvořit pokladničku se zadanou vstupní sumou"
    public DigiPig(String jmeno, int nKorun, int nDvouKorun) {
        //validace vstupu VZDY!!!
//        if (nKorun < 0) {
//            throw new IllegalArgumentException("Musí být kladný!!!");
//        }
//        if (nDvouKorun < 0) {
//            throw new IllegalArgumentException("Musí být kladný!!!");
//        }
        this.nKorun = checkPositive(nKorun);
        this.nDvouKorun = checkPositive(nDvouKorun);
        this.jmeno = jmeno;

//        suma = this.nKorun + 2 * this.nDvouKorun;
    }

    //validace vstupů VZDY přes metodu abych to mohl použít všude!!!!
    private int checkPositive(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("nesmí být záporný");
        }
        return value;
    }

    //metody
    //"změnit/nastavit jméno pokladničky" - setter
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getJmeno() {
        return jmeno;
    }

    public int getnKorun() {
        return nKorun;
    }

    public int getnDvouKorun() {
        return nDvouKorun;
    }

    public void vynulovat() {
        this.nKorun = 0;
        this.nDvouKorun = 0;
    }

    //metoda na sečtění dvou pokladniček - voláme na první pokladničku a v () bude druhá pokladnička volám bob Do alice
    public void transferTo(DigiPig pokladnaDo) {
        pokladnaDo.vlozitMince(this.nKorun, this.nDvouKorun); //přímý přístup k hodnotám, nemusím mít metodu :)
        this.vynulovat();
    }

    //getter  - vrátí počet korun
//    public int getSuma() {
//        return suma;
//    }
    public int getSuma() {
        return this.nKorun + 2 * this.nDvouKorun;
    }

    public void vlozitKorunu() {
//        suma++;
        nKorun++;
    }

    public void vlozitDvouKoruny() {
//        suma = suma + 2;
        nDvouKorun++;
    }

    //vždy stejné jména v metodě i v proměnných a použít this.zelené = černé!!!
    public void vlozitMince(int nKorun, int nDvouKorun) {
//        suma = suma + nKorun + (2 * nDvouKorun);
        this.nKorun = this.nKorun + checkPositive(nKorun);
        this.nDvouKorun = this.nDvouKorun + checkPositive(nDvouKorun);
        //nebo
//      this.nKorun += nKorun;
//      this.nDvouKorun += nDvouKorun;
//      suma = this.nKorun + 2*this.nDvouKorun;

    }

    //vypsat
    @Override
    public String toString() {
        return jmeno + " má v pokladničce " + getSuma() + "Kč - " + nKorun + " x 1Kč, " + nDvouKorun + " x 2Kč";
    }

    //ošetřit i cenu!!
    public boolean jeDostPenez(int cena) {
//        return suma >= cena;
        return getSuma() >= checkPositive(cena);
    }

    //test main
    public static void main(String[] args) {
        DigiPig p1 = new DigiPig("Petr", 5, 10);
        System.out.println(p1);
    }
    
    public static void transfer(DigiPig alice, DigiPig bob){
        alice.vlozitMince(bob.getnKorun(), bob.getnDvouKorun());
        bob.vynulovat();
    }
}
