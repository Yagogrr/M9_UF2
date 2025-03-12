package iticbcn.m9;

import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> salaEspera;
    private int cadires;
    private Object condBarber;
    public static Barberia barberia;
    public Barberia(int cadires){
        this.cadires = cadires;
        this.salaEspera = new LinkedList<Client>();
        this.condBarber = new Object();
    }

    public Object getCondBarber(){return condBarber;}

    public void entrarClient(Client client){
        if(salaEspera.size()<cadires){
            this.salaEspera.add(client);
            condBarber.notifyAll();
        }
    }

    public Client seguentClient(){
        //TODO
    }

    @Override
    public void run(){
        try{
            for(int i = 0;i<10;i++){
                salaEspera.add(new Client(i));
                sleep(500);  
            }
            sleep(10000);
            for(int i = 0;i<10;i++){
                salaEspera.add(new Client(i));
                sleep(500);  
            }
        } catch(InterruptedException ie){

        }
    }

    public static void main(String[] args) {
        Barberia.barberia = new Barberia(3);
        Barber manuel = new Barber("manuel");
        manuel.start();
        Barberia.barberia.start();
    }
}
