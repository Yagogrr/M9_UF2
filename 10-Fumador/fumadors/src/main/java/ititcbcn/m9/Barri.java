package ititcbcn.m9;

/**
 * Representa un barri amb un estanc i diversos fumadors.
 */
public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;
    
    /**
     * Constructor que inicialitza l'estanc i els fumadors.
     */
    public Barri() {
        this.estanc = null;
        this.fumadors = new Fumador[3];
        for (int i = 0; i < fumadors.length; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }
    
    /**
     * Inicia l'execució de l'estanc i els fumadors.
     */
    public void iniciar() {
        // Crear i iniciar el fil de l'estanc
        Estanc estanc = new Estanc();

        // Crear i iniciar els fils dels fumadors
        Fumador[] fumadors = new Fumador[3];
        for (int i = 0; i < fumadors.length; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }

        //Ficar en marxa els fumadors
        for (Fumador fil : fumadors) {
            fil.start();
        }

        // Ficar en marxa el fil de l'estanc
        estanc.start();
        
        // Esperar a que tots els fumadors acabin
        try {
            for (Fumador fil : fumadors) {
                fil.join();
            }
        } catch (InterruptedException e) {
           
        }
        
        // Tancar l'estanc quan tots els fumadors han acabat
        estanc.tancarEstanc();
        
        // Esperar a que el fil de l'estanc acabi
        try {
            estanc.join();
        } catch (InterruptedException e) {
           
        }
    }
    
    /**
     * Mètode principal per executar el programa.
     */
    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.iniciar();
    }
}