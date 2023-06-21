package sk.stuba.fei.uim.oop;

public class SancaParametre {
    private String nazov;
    private int hodnota;

    public SancaParametre(String nazov, int hodnota)
    {
        this.nazov=nazov;
        this.hodnota=hodnota;
    }

    public String getNazov() {
        return nazov;
    }
    public int getHodnota()
    {
        return hodnota;
    }
}
