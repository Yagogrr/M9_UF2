package iticbcn.m9;

import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int numeroF;
    private ReentrantLock bloqueig = new ReentrantLock(true);

    public Forquilla(int id){
        this.numeroF = id;
    }

    
    public int getNumeroF() {
        return numeroF;
    }

    public boolean estaOcupada(){
        return bloqueig.isLocked();
    }

    public void setNumeroF(int id) {
        this.numeroF = id;
    }

    public ReentrantLock getBloqueig() {
        return bloqueig;
    } 
    public boolean tryAgafar() {
        return bloqueig.tryLock();
    }
    public void agafar (){
        bloqueig.lock();
    }

    public void deixar (){
        if(estaOcupada()){
            bloqueig.unlock();
        }
    }
    
}
