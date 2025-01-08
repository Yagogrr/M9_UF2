package iticbcn.m9;

import java.util.Random;

public class DormAleatori extends Thread {

    long timeCreated;

    public DormAleatori(String nom) {
        super(nom);
        timeCreated = System.currentTimeMillis();
    }

    @Override
    public void run(){
        try{
            for(int i = 0;i<10;i++){
                String nom = getName();
                int randomI = new Random().nextInt(1000);
                long total = System.currentTimeMillis() - timeCreated;
                System.out.printf("%s(%d) a dormir %dms total %d%n",nom,i,randomI,total);
                sleep(randomI);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }

    public static void main(String[] args){
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        joan.start();
        pep.start();
        System.out.println("-- Fi de main -----------");
    }
}