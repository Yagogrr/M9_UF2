package iticbcn.m9;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Filosof extends Thread {
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private long Gana;
    private int nComensal;
    private long iniciGana;
    private long fiGana;

    public Filosof(String nom, Forquilla forquillaDreta, Forquilla forquillaEsquerra, int nComensal) {
        super(nom);
        this.Gana = 0;
        this.forquillaDreta = forquillaDreta;
        this.forquillaEsquerra = forquillaEsquerra;
        this.nComensal = nComensal;
        this.iniciGana = 0;
        this.fiGana = 0;
        this.Gana = 0;
    }

    public void calcularGana() {
        this.Gana = (this.fiGana - this.iniciGana) / 1000;
    }

    public void resetGana() {
        this.iniciGana = 0;
    }

    public void menjar() {
            try {
                agafarForquilles();
                this.fiGana = System.currentTimeMillis();
                calcularGana();
                System.out.println("Filòsof: " + this.getName() + " menja amb " + this.Gana + " gana");
                Thread.sleep(new Random().nextInt(1000) + 1000);
                resetGana();
                System.out.println("Filòsof: " + this.getName() + " ha acabat de menjar");
                deixarForquilles();
                this.pensar();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
    }

    public void pensar() {
        try {
            this.iniciGana = System.currentTimeMillis();
            System.out.println("Filòsof: " + getName() + " pensant");
            Thread.sleep(new Random().nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            System.err.println(e);
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

    public long getGana() {
        return Gana;
    }

    public void setGana(int gana) {
        this.Gana = gana;
    }

    public void agafaForquillaEsquerra() {
        while (!this.forquillaEsquerra.tryAgafar()) {
            try {
                Thread.sleep(new Random().nextInt(500) + 500);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        System.out.println("Filòsof: " + getName() + " agafa la forquilla esquerra " + this.forquillaEsquerra.getNumeroF());
    }

    public void agafaForquillaDreta() {
        while (!this.forquillaDreta.tryAgafar()) {
            try {
                Thread.sleep(new Random().nextInt(500) + 500);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        System.out.println("Filòsof: " + getName() + " agafa la forquilla dreta " + this.forquillaDreta.getNumeroF());
    }

    private void deixarForquilles() {
        this.forquillaDreta.deixar();
        this.forquillaEsquerra.deixar();
    }

    public void agafarForquilles() {
        agafaForquillaEsquerra();
        agafaForquillaDreta();
    }

    @Override
    public void run() {
        try {
        
            while (true) {
                this.menjar();
                this.pensar();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
