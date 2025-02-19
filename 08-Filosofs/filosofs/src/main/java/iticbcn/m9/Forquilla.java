package iticbcn.m9;

public class Forquilla {
    private int numeroF;
    private boolean enUs;
    private final int LLIURE=-1;

    private int propietari;

    public Forquilla(int id){
        this.numeroF = id;
        this.enUs = false;
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

    public boolean getEnUs(){
        return enUs;
    }

    public void setEnUs(boolean enUs){
        this.enUs = enUs;
    }
    
}
