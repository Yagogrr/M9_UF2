package ititcbcn.m9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representa un estanc que subministra tabac, paper i llumins als fumadors.
 */
public class Estanc extends Thread {
    private List<Tabac> tabacList;
    private List<Paper> paperList;
    private List<Llumi> llumiList;
    private boolean obert;
    private final Random random;
    
    /**
     * Constructor que inicialitza les llistes i l'estat de l'estanc.
     */
    public Estanc() {
        this.tabacList = new ArrayList<>();
        this.paperList = new ArrayList<>();
        this.llumiList = new ArrayList<>();
        this.obert = true;
        this.random = new Random();
        System.out.println("Estanc obert");
    }
    
    /**
     * Produeix de forma aleatòria un nou subministrament (tabac, paper o llumí).
     */
    public synchronized void nouSubministrament() {
        int opcio = random.nextInt(3); // 0: tabac, 1: paper, 2: llumí
        
        switch (opcio) {
            case 0:
                addTabac();
                break;
            case 1:
                addPaper();
                break;
            case 2:
                addLlumi();
                break;
        }
        
        // Notificar a tots els fumadors que hi ha un nou subministrament
        notifyAll();
    }
    
    /**
     * Afegeix una unitat de tabac a la llista.
     */
    public synchronized void addTabac() {
        tabacList.add(new Tabac());
        System.out.println("Afegint tabac");
    }
    
    /**
     * Afegeix una unitat de paper a la llista.
     */
    public synchronized void addPaper() {
        paperList.add(new Paper());
        System.out.println("Afegint Paper");
    }
    
    /**
     * Afegeix una unitat de llumí a la llista.
     */
    public synchronized void addLlumi() {
        llumiList.add(new Llumi());
        System.out.println("Afegint Llumí");
    }
    
    /**
     * Ven una unitat de tabac si n'hi ha disponible.
     */
    public synchronized Tabac venTabac() {
        while (obert && tabacList.isEmpty()) {
            try {
                wait(); // Esperar fins que hi hagi tabac disponible
            } catch (InterruptedException e) {
                
                return null;
            }
        }
        
        if (!obert) {
            return null; // L'estanc està tancat
        }
        
        return tabacList.remove(0); // Retorna i elimina el primer element
    }
    
    /**
     * Ven una unitat de paper si n'hi ha disponible.
     */
    public synchronized Paper venPaper() {
        while (obert && paperList.isEmpty()) {
            try {
                wait(); // Esperar fins que hi hagi paper disponible
            } catch (InterruptedException e) {
                
                return null;
            }
        }
        
        if (!obert) {
            return null; // L'estanc està tancat
        }
        
        return paperList.remove(0); // Retorna i elimina el primer element
    }
    
    /**
     * Ven una unitat de llumí si n'hi ha disponible.
     */
    public synchronized Llumi venLlumi() {
        while (obert && llumiList.isEmpty()) {
            try {
                wait(); // Esperar fins que hi hagi llumí disponible
            } catch (InterruptedException e) {
                
                return null;
            }
        }
        
        if (!obert) {
            return null; // L'estanc està tancat
        }
        
        return llumiList.remove(0); // Retorna i elimina el primer element
    }
    
    /**
     * Tanca l'estanc i notifica a tots els fumadors.
     */
    public synchronized void tancarEstanc() {
        this.obert = false;
        System.out.println("Estanc tancat");
        notifyAll(); // Despertar tots els fumadors que estan esperant
    }
    
    /**
     * Comprova si l'estanc està obert.
     */
    public boolean isObert() {
        return obert;
    }
    
    @Override
    public void run() {
        while (isObert()) {
            nouSubministrament();
            
            try {
                // Esperar entre 0,5 i 1,5 segons
                Thread.sleep(500 + random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}