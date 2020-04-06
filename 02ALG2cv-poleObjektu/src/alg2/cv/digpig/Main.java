package alg2.cv.digpig;

public class Main {

    /**
     * vytvořte pokl "Alice" s nějakou vstupní sumou vytvořte prázdnou pokl
     * "Bob" vlož k Bobovi - po jedné minci 2x korun, po jedné minci
     * 3xdvoukorun, najednou hrst mincí 5x korun a 10xdvoukorun vypište kolik
     * peněz má Alice a kolik Bob vypište všechny info o obou pokl zjistěte kdo
     * si může koupit hamburger za 39Kč Bob přepsal všechny svoje úspory na
     * Alici
     *
     * @param args
     */
    public static void main(String[] args) {
        DigiPig alice = new DigiPig("Alice", 20, 20);
        DigiPig bob = new DigiPig("Bob");
        bob.vlozitKorunu();
        bob.vlozitKorunu();
        bob.vlozitDvouKoruny();
        bob.vlozitDvouKoruny();
        bob.vlozitDvouKoruny();
        bob.vlozitMince(5, 10);
        System.out.println(alice.getJmeno() + " má: " + alice.getSuma() + " Kč");
        System.out.println(bob.getJmeno() + " má: " + bob.getSuma() + " Kč");
        System.out.println(alice.toString());
        System.out.println(bob.toString());
        System.out.println(alice.getJmeno() + " si " + (alice.jeDostPenez(39) ? "může" : "nemůže") + " koupit burgera");
        System.out.println(bob.getJmeno() + " si " + (bob.jeDostPenez(39) ? "může" : "nemůže") + " koupit burgera");
//        transfer(alice, bob);
//        DigiPig.transfer(alice, bob);
        bob.transferTo(alice);
        System.out.println(alice.toString());
        System.out.println(bob.toString());
        
    }
    
    //hodili jsme to do DigiPig a přistupovali jsme přes this. k objektům
    public static void transfer(DigiPig alice, DigiPig bob){
        alice.vlozitMince(bob.getnKorun(), bob.getnDvouKorun());
        bob.vynulovat();
    }

}
