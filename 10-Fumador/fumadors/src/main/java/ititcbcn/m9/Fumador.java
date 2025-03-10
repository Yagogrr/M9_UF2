package ititcbcn.m9;

import java.util.Random;

/**
 * Representa un fumador que necessita tabac, paper i llumins per fumar.
 */
public class Fumador extends Thread {
    private final Estanc estanc;
    private final int id;
    private Tabac tabac;
    private Paper paper;
    private Llumi llumi;
    private int numFumades;
    private final Random random;
    
    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.tabac = null;
        this.paper = null;
        this.llumi = null;
        this.numFumades = 0;
        this.random = new Random();
    }
    
    /**
     * Compra tabac a l'estanc si no en té.
     */
    public void compraTabac() {
        if (tabac == null) {
            System.out.println("Fumador " + id + " comprant Tabac");
            tabac = estanc.venTabac();
        }
    }
    
    /**
     * Compra paper a l'estanc si no en té.
     */
    public void compraPaper() {
        if (paper == null) {
            System.out.println("Fumador " + id + " comprant Paper");
            paper = estanc.venPaper();
        }
    }
    
    /**
     * Compra llumí a l'estanc si no en té.
     */
    public void compraLlumi() {
        if (llumi == null) {
            System.out.println("Fumador " + id + " comprant Llumí");
            llumi = estanc.venLlumi();
        }
    }
    
    /**
     * Fuma si té tots els materials necessaris (tabac, paper i llumí).
     */
    public boolean fuma() {
        if (tabac != null && paper != null && llumi != null) {
            System.out.println("Fumador " + id + " fumant");
            
            // Consumir els materials
            tabac = null;
            paper = null;
            llumi = null;
            
            try {
                // Fumar durant un temps entre 0,5 i 1 segon
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException e) {
            
            }
            
            numFumades++;
            System.out.println("Fumador " + id + " ha fumat " + numFumades + " vegades");
            return true;
        }
        return false;
    }
    
    /**
     * Comprova si el fumador ha completat les seves fumades.
     */
    public boolean haAcabat() {
        return numFumades >= 3;
    }
    
    @Override
    public void run() {
        while (!haAcabat() && estanc.isObert()) {
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
    }
}