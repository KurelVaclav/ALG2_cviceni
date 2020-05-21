package recursion;

/**
 *
 * @author Václav Kurel
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("iterativně: " + fac(4));
        System.out.println("rekurzivně: " + facR(4));
        System.out.println("Mystery: " + mystery(3, 11));
        int[] a = {123, 45, 21, 74};
        int n = a.length;
        System.out.println("maximum z pole: " + fm(a, n));
    }

    //factorial iterative 4! = 1*2*3*4
    public static int fac(int n) {
        int fac;
        fac = 1;
        for (int i = 2; i < n + 1; i++) {
            fac = fac * i;
        }
        return fac;
    }

    //factorial rekurzivně, rekuzce = devide and conquere = rozděl a panuj - najdu šeření na nejmenší problém a pak jdu na zpět
    public static int facR(int n) {
        if (n <= 1) { //base case - nejmenší problém 1! = 1
            return 1;
        } else {
            return n * facR(n - 1);
        }
    }

    //debug facR
    /*
    facR(4) = 
    else:  4*facR(3) = 4*6=24
    else:   3*facR(2) = 3*2 = 6
    else:    2*facR(1) = 2
    if:       facR(1) = 1
     */
    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        return mystery(a + a, b / 2) + a;
    }

    //debug mistery
    /*
    m(3,11) = 33
    |
    m(6,5) +3 = 33
    |
    m(12,2) +6 = 30
    |
    m(24,1) = 24
    |
    m(48,0) +24 = 24
    |
    0
     */
    //findmaximum
    public static int fm(int[] a, int n) {
        if (n == 1) { //n==2
            return a[0]; //return Math.max(a[0],a[1]);
        }
        return Math.max(a[n - 1], fm(a, n - 1));
    }

    //debug findMax
    /*
    {23,45,21,34}
    fm(a,4) = 45
    max(34,fm(a,3)) = 45
           max(21,fm(a,2)) = 45
                  max(45,fm(a,1) = 45
                         fm(a,1) = 23
     */
    //hledání maxima v jedné části druhé části
    public static int fm1(int[] a, int from, int to) {
        if (from > to) {
            return a[from];
        }
        int half = (from + to) / 2;
        int leftMax = fm1(a, from, half);
        int rightMax = fm1(a, half + 1, to);
        if (leftMax >= rightMax) {
            return leftMax;
        } else {
            return rightMax;
        }
    }

    //debug fm1
    /*
    {23,45,21,34}
    fm1(a,0,3) = 
    |
    half = 3/2 = 1
    leftMax = fm1(a,0,1) = 
              half = 1/2 = 0
              leftMax = fm1(a,0,0) = 23
                        fm(a,0,0) = a[0] = 23
              rigthMax = fm1(a,1,1) = 45
                         fm1(a,1,1) = a[1] = 45
          ... 45 //45>2
          ... 45
    
 ...rightMax = fm1(a,2,3) = 
               half = 5/2 = 2
               leftMax = fm1(a,2,2) = 21
                         fm1(a,2,2) = a[2] = 21
            ...rightMax = fm1(a,3,3) = 34
                          fm1(a,3,3) = a[3] = 34
            ...34 //34>21
 ...45 //45>21
     */
    
    //TODO
    //do CMD editoru výpis obsah adresáře - dir a zároveň podadresáře dir s podadresáří
}
