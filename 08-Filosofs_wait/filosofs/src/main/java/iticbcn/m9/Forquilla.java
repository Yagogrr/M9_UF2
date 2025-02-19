package iticbcn.m9;

public class Forquilla {
    private int numeroF;
    private final int LLIURE=-1;

    private int propietari;

    public Forquilla(int id){
        this.numeroF = id;
        this.propietari = LLIURE;
    }

    public int getLLIURE() {
        return LLIURE;
    }
    
    public int getNumeroF() {
        return numeroF;
    }

    public void setNumeroF(int id) {
        this.numeroF = id;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }
    
}
