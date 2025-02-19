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

    public synchronized void menjar(){
        this.setGana(0);
        try {
            wait(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void pensar(){
        System.out.println("Filòsof: "+ getName() + " pensant");
        try {
            wait(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
        if (this.forquillaEsquerra.getLLIURE()==this.forquillaDreta.getPropietari()) {
            this.forquillaEsquerra.setPropietari(this.nComensal);
            System.out.println("Filòsof: "+ getName() + " agafa la forquilla esquerra "+this.forquillaEsquerra.getNumeroF());
            return true;
        }
        return false;
    }

    public boolean agafaForquillaDreta() {
        if (this.forquillaDreta.getLLIURE()==this.forquillaDreta.getPropietari()) {
            this.forquillaDreta.setPropietari(this.nComensal);
            System.out.println("Filòsof: "+ getName() + " agafa la forquilla dreta "+this.forquillaDreta.getNumeroF());
            return true;
        }
        return false;
    }

    public void deixarForquilles() {
        this.forquillaDreta.setPropietari(this.forquillaDreta.getLLIURE());
        this.forquillaEsquerra.setPropietari(this.forquillaDreta.getLLIURE());
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
                this.forquillaEsquerra.setPropietari(this.forquillaDreta.getLLIURE());
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
    public synchronized void run() {
        try {
            while (true) {
                wait(1000);
                agafarForquilles();
                notifyAll(); //avisa a los filosofos de que las cucharas estan disponibles
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error en filosofo " + getName() + ": " + e.getMessage());
        }
    }
    
}  
 