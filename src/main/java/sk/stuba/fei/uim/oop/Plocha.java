package sk.stuba.fei.uim.oop;

import java.util.Random;

public class Plocha {
    private int velkost;

    private Policko[] board;
    private String [] pole={"Start","stredna skola","radnica","sanca","stanica","hotel","vazanie","mrakodrap","chata",
            "sanca","aquacity","zakladna skola","policia","aupark","kostol","sanca","Surprise","futbalovy stadion",
            "platba dane","Hrad","Majkin legendarny dom","sanca","Rock Fabric","chicago"};

    public Plocha(int velkost) {
        this.velkost=velkost;
        this.board = new Policko[velkost];
        vyplnPlochu(pole);
    }

    private void vyplnPlochu(String [] pole) {
        for (int i = 0; i < velkost; i++) {
            if (i==0  || i==6  || i==12  || i==18)
            {
                this.board[i] = new Policko (pole[i]);
            }
            else if(i==3 || i==9 || i==15 || i==21)
            {
                this.board[i] = new Sanca(pole[i]);
            }
            else
            {
                Random gen = new Random();
                int nahodDan = gen.nextInt(17)+10;
                int nahodCena = gen.nextInt(14)+7;
                this.board[i]= new Budova(pole[i],-(nahodDan*1000),-1,(nahodCena*1000));

            }
        }
    }

    public String getPolickoNazov(int num) {
        return this.board[num].getNazov();
    }

    public Policko getPolicko(int num) {
        return this.board[num];
    }
}
