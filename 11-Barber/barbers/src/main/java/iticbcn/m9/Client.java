package iticbcn.m9;

import java.util.Random;

public class Client extends Thread{
    public Client(int id){
        super(Integer.toString(id));
    }

    public void tallarseElCabell()throws InterruptedException{
        sleep(900+new Random().nextInt(100));
    }

    public String getNom(){
        return super.getName();
    }
    
}
