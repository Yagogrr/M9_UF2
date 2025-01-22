package iticbcn.m9;

public class Associacio {
    private static int numSocis = 1000;
    private Soci[] socis;
    private Thread[] threads;

    public Associacio() {
        socis = new Soci[numSocis];
        threads = new Thread[numSocis];
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci();
            threads[i] = new Thread(socis[i]);
        }
    }

    public void iniciaCompteTempsSocis() {
        for (Thread t : threads) {
            t.start();
        }
    }

    public void esperaPeriodeSocis() {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mostraBalancComptes() {
        System.out.printf("Saldo: %.2f%n", Compte.getInstance().getSaldo());
    }

    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }
}
