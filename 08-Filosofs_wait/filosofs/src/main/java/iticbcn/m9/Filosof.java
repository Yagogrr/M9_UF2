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

    public void menjar() {
        while (true) {
            try {
                if(agafarForquilles()) {
                    System.out.println("Filòsof: "+this.getName()+" menja");
                    this.gana = 0;
                    Thread.sleep(new Random().nextInt(1000)+1000);
                    System.out.println("Filòsof: "+this.getName()+" ha acabat de menjar");
                    this.pensar();
                    deixarForquilles();
                    notifyAll();
                } else{
                    this.gana++;
                    System.out.println("Filòsof: "+this.getName()+" gana = "+this.gana);
                    Thread.sleep(new Random().nextInt(501)+500);
                    wait();
                }
            } catch (InterruptedException e) {
                System.err.println(e );
            }
        }
    }

    public void pensar() {
        try {
            System.out.println("Filòsof: "+ getName() + " pensant");
            Thread.sleep(new Random().nextInt(1000)+1000);
        } catch (InterruptedException e) {
            System.err.println(e );
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
        if (this.forquillaEsquerra.getLLIURE()==this.forquillaEsquerra.getPropietari()) {
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

    private void deixarForquilles() {
        this.forquillaDreta.setPropietari(this.forquillaDreta.getLLIURE());
        this.forquillaEsquerra.setPropietari(this.forquillaDreta.getLLIURE());
        notifyAll();
    }

    public boolean agafarForquilles() {
        if (agafaForquillaEsquerra()) {
            if (agafaForquillaDreta()) {
                return true;
            } else {
                this.forquillaEsquerra.setPropietari(this.forquillaDreta.getLLIURE());
                System.out.println("Filòsof: "+ getName() + " deixa la forquilla esquerra("+this.forquillaEsquerra.getNumeroF()+") i espera(dreta ocupada)");
            }
        }
        return false;
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
 