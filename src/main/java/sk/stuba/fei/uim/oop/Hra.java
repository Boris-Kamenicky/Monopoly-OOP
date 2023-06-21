package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Random;
import java.util.*;

public class Hra {
    public static final int PLOCHA_VELKOST = 24;
    public static final int KAPITAL = 100000;
    public static final int VAZANIE = 2;     //pocet kol vo vazani
    public static final int DAN= -10000;     //dan na policku platba dane
    private final Plocha plocha;

    public Hra() {
        this.plocha = new Plocha(PLOCHA_VELKOST);
    }

    public void hrat()
    {
        int sanca_poradie=0;
        int pocet_hracov=-1;

        while (pocet_hracov < 2) {
            try {
                pocet_hracov = ZKlavesnice.readInt("Zadaj pocet hracov: ");
                if (pocet_hracov < 2) {
                    throw new IllegalArgumentException("Nespravny vstup (najmenej 2 hraci)");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        Hrac h[] = new Hrac[pocet_hracov];
        for (int i=0 ; i<pocet_hracov ;i++)   //vytvorenie hracov a ulozenie do pola
        {
            h[i]= new Hrac(KAPITAL,i+1,ZKlavesnice.readString("zadaj meno hraca cislo: "+(i+1)));
        }
        Random gen = new Random();

        while(pocet_hracov>1)   //priebeh hry
        {
            for (int i=0 ; i<pocet_hracov ;i++)    //tah jednotlivych hracov
            {
                System.out.println("aktualna pozicia " + h[i].getPozicia() + ". " + plocha.getPolickoNazov(h[i].getPozicia()));
                System.out.println("peniaze: "+ h[i].getPeniaze() + " eur");

                if(h[i].getHracStatie()>0)   //vezenie
                {
                    System.out.println("[" + h[i].getNazov() + "]");
                    System.out.println("si vo vazani, stojis este:" + h[i].getHracStatie()+ " krat");
                    h[i].setHracStatie(h[i].getHracStatie()-1);
                }
                else if (h[i].getHracStatie()==0)
                    {
                    int kocka = gen.nextInt(6) + 1;
                    System.out.println("hrac [" + h[i].getNazov() + "] hodil " + kocka);
                    h[i].zmenPozicia(kocka);

                    if (h[i].getPozicia() > (PLOCHA_VELKOST - 1)) {
                        h[i].zmenPeniaze(10000);  //+ love ked prejde startom
                        System.out.println("Presiel si startom +" + 10000 + " eur");
                    }
                    h[i].setPozicia(h[i].getPozicia() % PLOCHA_VELKOST);
                    System.out.println("nova pozicia " + h[i].getPozicia() + ". " + plocha.getPolickoNazov(h[i].getPozicia()));

                    if (plocha.getPolicko(h[i].getPozicia()) instanceof Budova) //overuje ci policko je budova musis to castnut a potom sa dostanes k atributom
                    {
                        if (((Budova) plocha.getPolicko(h[i].getPozicia())).getMajitel() == -1) {
                            int odpoved = ZKlavesnice.readInt("Budova nema majitela, chces ju kupit? Ano=1 nie=0 \n cena: " + ((Budova) plocha.getPolicko(h[i].getPozicia())).getCena()
                                    + " eur \n dan: " + ((Budova) plocha.getPolicko(h[i].getPozicia())).getDan() + " eur ");
                            if (odpoved == 1 && h[i].getPeniaze() > ((Budova) plocha.getPolicko(h[i].getPozicia())).getCena())
                            {
                                ((Budova) plocha.getPolicko(h[i].getPozicia())).setMajitel(h[i].getHracCislo());
                                h[i].zmenPeniaze(-((Budova) plocha.getPolicko(h[i].getPozicia())).getCena());
                            } else if (odpoved == 1 && h[i].getPeniaze() < ((Budova) plocha.getPolicko(h[i].getPozicia())).getCena()) {
                                System.out.println("nemas dost penazi");
                            }
                        }
                        else if((h[i].getHracCislo())==((Budova) plocha.getPolicko(h[i].getPozicia())).getMajitel())
                        {
                            System.out.println("Tuto budovu vlastnis ty ");
                        }
                        else {
                            System.out.println("Budovu vlastni hrac [" + h[(((Budova) plocha.getPolicko(h[i].getPozicia())).getMajitel() - 1)].getNazov()
                                    + "] musis mu zaplatit dan: " + ((Budova) plocha.getPolicko(h[i].getPozicia())).getDan());
                            if (h[i].getPeniaze() < -(((Budova) plocha.getPolicko(h[i].getPozicia())).getDan())) {
                                h[((Budova) plocha.getPolicko(h[i].getPozicia())).getMajitel() - 1].zmenPeniaze(h[i].getPeniaze());
                            } else {
                                h[((Budova) plocha.getPolicko(h[i].getPozicia())).getMajitel() - 1].zmenPeniaze(-((Budova) plocha.getPolicko(h[i].getPozicia())).getDan());
                            }
                            h[i].zmenPeniaze(((Budova) plocha.getPolicko(h[i].getPozicia())).getDan());  //menis peniaze
                        }
                    }
                    if (plocha.getPolicko(h[i].getPozicia()) instanceof Sanca) {
                        if (sanca_poradie == 5) {
                            sanca_poradie = 0;
                        }
                        System.out.println(((Sanca) plocha.getPolicko(h[i].getPozicia())).getHodnota(sanca_poradie).getNazov()
                                + ((Sanca) plocha.getPolicko(h[i].getPozicia())).getHodnota(sanca_poradie).getHodnota());
                        h[i].zmenPeniaze(((Sanca) plocha.getPolicko(h[i].getPozicia())).getHodnota(sanca_poradie).getHodnota());
                        sanca_poradie++;
                    }
                    if (h[i].getPozicia()==12)   //vazanie
                    {
                        h[i].setPozicia(6);
                        h[i].setHracStatie(VAZANIE);
                        System.out.println("Ides do vezenia na " + VAZANIE + " kola" );
                    }
                    if (h[i].getPozicia()==18)   //dan
                    {
                        System.out.println("Musis zaplatit banke dan " +(-DAN) );
                        h[i].zmenPeniaze(DAN);
                    }
                    System.out.println("peniaze: "+ h[i].getPeniaze()+ " eur");
                }

                if(h[i].getPeniaze()<=0)
                {
                    System.out.println("Prehral si, dosli ti peniaze");
                    for (int j=0; j<PLOCHA_VELKOST; j++)
                    {
                        if (plocha.getPolicko(j) instanceof  Budova)   //prejdes cely board a vymazes majitela v budovach ktore ma prehraty hrac
                        {
                            if (((Budova) plocha.getPolicko(j)).getMajitel()==h[i].getHracCislo())
                            {
                                ((Budova) plocha.getPolicko(j)).setMajitel(-1);
                            }
                            if (((Budova) plocha.getPolicko(j)).getMajitel()>i)   //vsetci hraci nad prehratym maju novy index ktory sa da do budovy
                            {
                                ((Budova) plocha.getPolicko(j)).setMajitel(((Budova) plocha.getPolicko(j)).getMajitel()-1);
                            }
                        }
                    }
                    for (int j = i; j<pocet_hracov-1; j++)
                    {
                        h[j]=h[j+1];
                        h[j+1].setHracCislo(j+1);    // nastavia sa nove indexi hracom
                    }
                    pocet_hracov--;
                    i--;

                    if(pocet_hracov==1) //koniec hry
                    {
                        break;
                    }
                }
                Scanner keyIn = new Scanner(System.in);
                System.out.print("\nStlac enter pre pokracovanie\n");
                keyIn.nextLine();
            }
        }
        System.out.println("Koniec hry vyhral hrac [" + h[0].getNazov() + "]");
    }
}
