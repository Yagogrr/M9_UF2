package iticbcn.m9;

import java.util.Random;

public class Filosof extends Thread{
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int gana;
    private int nComensal;

    public Filosof(String nom,Forquilla forquillaDreta,Forquilla forquillaEsquerra, int nComensal){
        super(nom);
        this.gana = 0;
        this.forquillaDreta = forquillaDreta;
        this.forquillaEsquerra = forquillaEsquerra;
        this.nComensal = nComensal;
    }

    public void menjar(){
        this.setGana(0);
        Random r = new Random();
        int sleepTime = 1000 + r.nextInt(1001);
        try {
            sleep(sleepTime);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void pensar(){
        System.out.println("Filòsof: "+ getName() + " pensant");
        Random r = new Random();
        int sleepTime = 1000 + r.nextInt(1001);
        try {
            sleep(sleepTime);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public int getnComensal() {
        return nComensal;
    }

    public void setnComensal(int nComensal) {
        this.nComensal = nComensal;
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public int getGana() {
        return gana;
    }

    public void setGana(int gana) {
        this.gana = gana;
    }

    public boolean agafaForquillaEsquerra() {
        if (!this.forquillaEsquerra.getEnUs()) {
            this.forquillaEsquerra.setEnUs(true);
            System.out.println("Filòsof: "+ getName() + " agafa la forquilla esquerra "+this.forquillaEsquerra.getNumeroF());
            return true;
        }
        return false;
    }

    public boolean agafaForquillaDreta() {
        if (!this.forquillaDreta.getEnUs()) {
            this.forquillaDreta.setEnUs(true);
            System.out.println("Filòsof: "+ getName() + " agafa la forquilla dreta "+this.forquillaDreta.getNumeroF());
            return true;
        }
        return false;
    }

    public void deixarForquilles() {
        this.forquillaDreta.setEnUs(false);
        this.forquillaEsquerra.setEnUs(false);
    }

    public void agafarForquilles() {
        if (agafaForquillaEsquerra()) {
            if (agafaForquillaDreta()) {
                System.out.println("Filòsof: "+ getName() + " menja");
                this.menjar();
                System.out.println("Filòsof: "+ getName() + " ha acabat de menjar");
                this.pensar();
                deixarForquilles();
            } else {
                this.forquillaEsquerra.setEnUs(false);
                System.out.println("Filòsof: "+ getName() + " deixa la forquilla esquerra("+this.forquillaEsquerra.getNumeroF()+") i espera(dreta ocupada)");
                this.gana++;
                System.out.println("Filòsof: "+ getName() + " - Gana: "+this.gana);
            }
        } else {
            this.gana++;
            System.out.println("Filòsof: "+ getName() + " - Gana: "+this.gana);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Random r = new Random();
                int sleepTime = 500 + r.nextInt(501);
                sleep(sleepTime);
                agafarForquilles();
            }
        } catch (Exception e) {
            System.err.println("Error en filosofo " + getName() + ": " + e.getMessage());
        }
    }
    
}
