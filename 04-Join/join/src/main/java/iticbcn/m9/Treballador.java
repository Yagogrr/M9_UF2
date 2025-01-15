package iticbcn.m9;

import java.util.Random;

public class Treballador extends Thread {

    private final int NOU_ANUAL_BRUT;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(String nom, int edat_inici_treball, int edat_fi_treball, int NOU_ANUAL_BRUT) {
        super(nom);
        this.NOU_ANUAL_BRUT = NOU_ANUAL_BRUT;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    } 

    public void cobra() {
        this.cobrat += (NOU_ANUAL_BRUT / 12);
    }

    public void pagaImpostos(){
        this.cobrat -= (NOU_ANUAL_BRUT / 12) * 0.24;
    }

    @Override
    public void run(){
        for(int i = edat_inici_treball;i < edat_fi_treball+1;i++){
            this.edat_actual = i;
            for(int j = 0;j!=12;j++){
                this.cobra();
                this.pagaImpostos();
            }
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString(){
        String result = this.getName() + " -> edat: " + this.edat_actual + " / total: " + this.cobrat;
        return result;
    }

    public float getCobra(){
        return this.cobrat;
    }

    public int getEdat(){
        return this.edat_actual;
    }
   
}