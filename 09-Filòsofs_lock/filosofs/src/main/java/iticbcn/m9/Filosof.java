package iticbcn.m9;

import java.util.Random;

public class Filosof extends Thread {
    private long inicioHambre;
    private long finHambre;
    private long hambre;
    private Forquilla tenedorDerecho;
    private Forquilla tenedorIzquierdo;
    private int numComensal;

    public int getNumComensal() {
        return numComensal;
    }

    public void setNumComensal(int numComensal) {
        this.numComensal = numComensal;
    }

    public long getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public long getInicioHambre() {
        return inicioHambre;
    }

    public void setInicioHambre(long inicioHambre) {
        this.inicioHambre = inicioHambre;
    }

    public long getFinHambre() {
        return finHambre;
    }

    public void setFinHambre(long finHambre) {
        this.finHambre = finHambre;
    }

    public Forquilla getTenedorDerecho() {
        return tenedorDerecho;
    }

    public void setTenedorDerecho(Forquilla tenedorDerecho) {
        this.tenedorDerecho = tenedorDerecho;
    }

    public Forquilla getTenedorIzquierdo() {
        return tenedorIzquierdo;
    }

    public void setTenedorIzquierdo(Forquilla tenedorIzquierdo) {
        this.tenedorIzquierdo = tenedorIzquierdo;
    }

    public Forquilla gettenedorDerecho() {
        return tenedorDerecho;
    }

    public void settenedorDerecho(Forquilla tenedorDerecho) {
        this.tenedorDerecho = tenedorDerecho;
    }

    public Forquilla gettenedorIzquierdo() {
        return tenedorIzquierdo;
    }

    public void settenedorIzquierdo(Forquilla tenedorIzquierdo) {
        this.tenedorIzquierdo = tenedorIzquierdo;
    }

    public Filosof(String nom) {
        super(nom);
        inicioHambre = System.currentTimeMillis();
        finHambre = System.currentTimeMillis();
    }

    public void menjar() throws InterruptedException, Exception {
        agafarForquilles();
        finHambre = System.currentTimeMillis();
        calcularGana();
        System.out.println("Filòsof: " + this.getName() + " menja amb gana " + this.hambre);
        try {
            Thread.sleep(new Random().nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filòsof: " + this.getName() + " ha acabat de menjar");
        deixarForquilles();
    }

    private void agafarForquilles() throws InterruptedException, Exception {
        boolean tieneIzquierda = false;
        
        while (true) {
            if (!tieneIzquierda) {
                tieneIzquierda = agafatenedorIzquierdo();
                if (tieneIzquierda) {
                    System.out.println("Filòsof: " + this.getName() + " agafa la forquilla esquerra " + this.tenedorIzquierdo.getNumeroF());
                }
            }
            if (tieneIzquierda) {
                boolean tieneDerecha = agafatenedorDerecho();
                if (tieneDerecha) {
                    System.out.println("Filòsof: " + this.getName() + " agafa la forquilla dreta " + this.tenedorDerecho.getNumeroF());
                    return;
                } else {
                    this.tenedorIzquierdo.deixar();
                    tieneIzquierda = false;
                    System.out.println("Filòsof: " + this.getName() + " deixa l'esquerra (" + this.tenedorIzquierdo.getNumeroF() + ") i espera (dreta ocupada)");
                    Thread.sleep(new Random().nextInt(501) + 500);
                }
            }
        }
    }

    private boolean agafatenedorIzquierdo() throws Exception {
        return this.tenedorIzquierdo.agafar();
    }

    private boolean agafatenedorDerecho() throws Exception {
        return this.tenedorDerecho.agafar();
    }

    private void deixarForquilles() {
        this.tenedorDerecho.deixar();
        this.tenedorIzquierdo.deixar();
    }
    
    private void calcularGana() {
        hambre = ((finHambre - inicioHambre) / 1000);
    }

    public void pensar() {
        inicioHambre = System.currentTimeMillis();
        System.out.println("Filòsof: " + this.getName() + " pensant");
        Random rand = new Random();
        int tiempo = rand.nextInt(1000) + 1000;
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.menjar();
                this.pensar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}