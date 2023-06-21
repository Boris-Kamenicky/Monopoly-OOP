package sk.stuba.fei.uim.oop;

public class Budova extends Policko{

    private int dan;
    private int majitel;
    private int cena;

    public Budova(String name) {
        super(name);
        this.dan=0;
        this.cena=0;
        this.majitel=-1;
    }

    public Budova(String name, int dan, int majitel, int cena) {
        super(name);
        this.dan = dan;
        this.majitel = majitel;
        this.cena = cena;
    }

    public int getDan() {
        return dan;
    }

    public int getMajitel() {
        return majitel;
    }
    public int getCena() {
        return cena;
    }

    public void setMajitel(int majitel){
        this.majitel=majitel;
    }
}
