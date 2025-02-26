package iticbcn.m9;

import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int numeroF;
    private ReentrantLock bloqueig = new ReentrantLock();

    public Forquilla(int id){
        this.numeroF = id;
    }

    
    public int getNumeroF() {
        return numeroF;
    }

    public void setNumeroF(int id) {
        this.numeroF = id;
    }

    public ReentrantLock getBloqueig() {
        return bloqueig;
    } 

    public void agafar (){
        bloqueig.lock();
    }

    public void deixar (){
        bloqueig.unlock();
    }
    
}
