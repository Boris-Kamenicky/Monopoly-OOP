package sk.stuba.fei.uim.oop;

public class Sanca extends Policko{


    private SancaParametre [] sance={new SancaParametre("Pokazene auto ",-6000),new SancaParametre("Havaria ",-8000)
            ,new SancaParametre("Platis elektriku ",-5000),new SancaParametre("Vyhral si loto ",10000),
            new SancaParametre("Nasiel si na zemi ",3000)};

    public Sanca(String nazov) {
        super(nazov);

    }
    public SancaParametre getHodnota(int poradie) {
        return sance[poradie];
    }
}
