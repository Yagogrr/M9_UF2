package iticbcn.m9;

public class Barber extends Thread {
    public Barber(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Client client;
                synchronized (Barberia.barberia.getCondBarber()) {
                    client = Barberia.barberia.seguentClient();
                    if (client == null) {
                        System.out.println("Ningú en espera");
                        System.out.println("Barber " + getName() + " dormint");
                        Barberia.barberia.getCondBarber().wait();
                        continue; // tornar a intentar després del wait
                    } else {
                        System.out.println("Li toca al " + client.getNom());
                    }
                }
                // Tallar el cabell fora del bloc sincronitzat per no bloquejar altres operacions
                System.out.println("Tallant cabell a " + client.getNom());
                client.tallarseElCabell();
            }
        } catch (InterruptedException ie) {
        }
    }
}
