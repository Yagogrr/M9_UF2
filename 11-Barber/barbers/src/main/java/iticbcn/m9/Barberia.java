package iticbcn.m9;

import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> salaEspera;
    private int cadires;
    private Object condBarber;
    public static Barberia barberia;

    public Barberia(int cadires) {
        this.cadires = cadires;
        this.salaEspera = new LinkedList<Client>();
        this.condBarber = new Object();
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public Client seguentClient() {
        synchronized (condBarber) {
            return salaEspera.poll();
        }
    }

    public void entrarClient(Client client) {
        synchronized (condBarber) {
            if (salaEspera.size() < cadires) {
                salaEspera.add(client);
                System.out.println(client.getNom() + " en espera");
                condBarber.notifyAll();
            } else {
                System.out.println("No queden cadires, " + client.getNom() + " se'n va");
            }
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                entrarClient(new Client(i));
                sleep(500);
            }
            sleep(10000);
            for (int i = 11; i <= 20; i++) {
                entrarClient(new Client(i));
                sleep(500);
            }
        } catch (InterruptedException ie) {
            
        }
    }

    public static void main(String[] args) {
        Barberia.barberia = new Barberia(3);
        Barber barber = new Barber("Manuel");
        barber.start();
        Barberia.barberia.start();
    }
}
