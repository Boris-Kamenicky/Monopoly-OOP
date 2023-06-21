package sk.stuba.fei.uim.oop;

public class Hrac {

    private int peniaze;
    private int hracCislo;
    private int pozicia;
    private int statie;
    private final String nazov;


    public Hrac(int peniaze, int hracCislo, String nazov) {
        this.pozicia=0;
        this.statie=0;
        this.peniaze = peniaze;
        this.hracCislo= hracCislo;
        this.nazov = nazov;
    }

    public int getPeniaze() {
        return peniaze;
    }

    public void zmenPeniaze(int peniaze) { this.peniaze+=peniaze; }

    public int getHracCislo() {
        return hracCislo;
    }

    public void setHracCislo(int hracCislo) {
        this.hracCislo = hracCislo;
    }

    public int getHracStatie() {
        return statie;
    }

    public void setHracStatie(int statie) {
        this.statie = statie;
    }

    public int getPozicia() {
        return pozicia;
    }

    public void zmenPozicia(int pozicia) { this.pozicia+=pozicia; }

    public void setPozicia(int pozicia) {this.pozicia=pozicia;}

    public String getNazov() {
        return nazov;
    }
}
