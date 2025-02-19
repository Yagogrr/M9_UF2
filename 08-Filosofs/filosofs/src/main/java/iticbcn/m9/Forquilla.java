package iticbcn.m9;

public class Forquilla {
    private int numeroF;
    private boolean enUs;
    public final int LLIURE=-1;
    public Forquilla(int id){
        this.numeroF = id;
        this.enUs = false;
    }
    
    public int getNumeroF() {
        return numeroF;
    }

    public void setNumeroF(int id) {
        this.numeroF = id;
    }

    public boolean getEnUs(){
        return enUs;
    }

    public void setEnUs(boolean enUs){
        this.enUs = enUs;
    }
    
}
