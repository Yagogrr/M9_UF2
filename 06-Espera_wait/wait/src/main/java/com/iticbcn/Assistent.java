package com.iticbcn;

import java.util.Random;

public class Assistent extends Thread{
    private Esdeveniment esdeveniment;
    public Assistent(String nom, Esdeveniment esdeveniment){
        super(nom);
        this.esdeveniment = esdeveniment;
    }

    @Override
    public void run(){
        while(true){
            Random r = new Random();
            if(r.nextInt(1000) % 2 == 0){
                this.esdeveniment.ferReserva(this);
            } else {
                this. esdeveniment.cancelarReserva(this);
            }
            try{
                this.sleep(r.nextInt(1000));
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }   
    }

    public Esdeveniment getEsdeveniment() {
        return esdeveniment;
    }
    public void setEsdeveniment(Esdeveniment esdeveniment) {
        this.esdeveniment = esdeveniment;
    }

    
}
