package tul.alg2.DU;

/**
 *
 * @author Václav Kurel
 */
public class Kalendar {

    //data
    private int dd;
    private int mm;
    private int rr;

    //konstruktor
    public Kalendar(int dd, int mm, int rr) {
        this.dd = checkDen(dd);
        this.mm = checkMesic(mm);
        this.rr = rr;
    }

    private int checkDen(int dd) {
        if (dd < 1 || dd > 31) {
            throw new IllegalArgumentException("Zadali jste nevalidní den!");
        }
        return dd;
    }

    private int checkMesic(int mm) {
        if (mm < 1 || mm > 12) {
            throw new IllegalArgumentException("Zadali jste nevalidní měsíc!");
        }
        return mm;
    }

    //zeller algoritmus
    public int getDayInWeek() {
        int dd = this.dd;
        int mm = this.mm;
        int rr = this.rr;
        if (mm == 1) {
            mm = 13;
            rr--;
        }
        if (mm == 2) {
            mm = 14;
            rr--;
        }
        int q = dd;
        int m = mm;
        int k = rr % 100;
        int j = rr / 100;
        int h = q + 13 * (m + 1) / 5 + k + k / 4 + j / 4 + 5 * j;
        h = h % 7;
        return ((h + 5) % 7) + 1;
    }

    public String buildKalendar() {
        StringBuilder kalendar = new StringBuilder();
        kalendar.append(String.format("%s %4d %n", displayMM(), rr));
        kalendar.append("Po Ut St Čt Pá So Ne %n");
        int promDD = dd;
        dd = 1;
        int ddStart = getDayInWeek();
        for (int i = 0; i < ddStart - 1; i++) {
            kalendar.append("   ");
        }
        for (int i = 0; i < ddInMM(rr)[mm - 1]; i++) {
            kalendar.append(String.format("%2d ", dd));
            if (getDayInWeek() == 7) {
                kalendar.append("%n");
            }
            dd++;
        }
        int ddEnd = getDayInWeek();
        for (int i = 0; i < 7 - ddEnd + 1; i++) {
            kalendar.append("   ");
        }
        kalendar.append("%n");
        dd = promDD;
        return kalendar.toString();
    }

    private String displayMM() {
        String s = "";
        switch (mm) {
            case 1:
                s = "leden";
                break;
            case 2:
                s = "únor";
                break;
            case 3:
                s = "březen";
                break;
            case 4:
                s = "duben";
                break;
            case 5:
                s = "květen";
                break;
            case 6:
                s = "červen";
                break;
            case 7:
                s = "červenec";
                break;
            case 8:
                s = "srpen";
                break;
            case 9:
                s = "září";
                break;
            case 10:
                s = "říjen";
                break;
            case 11:
                s = "listopad";
                break;
            case 12:
                s = "prosinec";
        }
        return s;
    }

    private static int[] ddInMM(int rr) {
        int[] ddInMM = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        ddInMM[1] = isRRPrechodny(rr) ? 29 : 28;
        return ddInMM;
    }

    public static boolean isRRPrechodny(int rr) {
        if (rr % 4 != 0) {
            return false;
        }
        if (rr % 100 != 0) {
            return true;
        }
        return rr % 400 == 0;
    }

    public static int ddInrr(int rr) {
        return isRRPrechodny(rr) ? 366 : 365;
    }

    public void nextMM() {
        dd = 1;
        if (mm == 12) {
            rr++;
            mm = 1;
        } else {
            mm++;
        }
    }

    public void previousMM() {
        dd = 1;
        if (mm == 1) {
            mm = 12;
            rr--;
        } else {
            mm--;
        }
    }

}
