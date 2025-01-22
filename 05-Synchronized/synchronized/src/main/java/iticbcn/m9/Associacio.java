package iticbcn.m9;


public class Associacio {
    // Número total de socios en la asociación
    private static int numSocis = 1000;
    
    // Array para almacenar los objetos Soci
    private Soci[] socis;
    
    // Array para almacenar los hilos asociados a cada socio
    private Thread[] threads;

    /**
     * Inicializa los arrays de socios y threads, creando un nuevo socio
     * y su hilo para cada posición.
     */
    public Associacio() {
        socis = new Soci[numSocis];
        threads = new Thread[numSocis];
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci();              // Crea un nuevo socio
            threads[i] = new Thread(socis[i]);  // Crea un nuevo hilo asociado al socio
        }
    }

    /**
     * Inicia la ejecución de todos los hilos de los socios.
     * Cada hilo ejecutará el método run() de su correspondiente objeto Soci.
     */
    public void iniciaCompteTempsSocis() {
        for (Thread t : threads) {
            t.start();  // Inicia la ejecución del hilo
        }
    }

    /**
     * Utiliza el método join() para asegurar que todos los hilos completen
     * sus operaciones antes de continuar.
     */
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
        Associacio associacio = new Associacio();           // Crea la asociación
        associacio.iniciaCompteTempsSocis();               // Inicia los hilos
        associacio.esperaPeriodeSocis();                   // Espera a que terminen
        associacio.mostraBalancComptes();                  // Muestra el balance final
    }
}