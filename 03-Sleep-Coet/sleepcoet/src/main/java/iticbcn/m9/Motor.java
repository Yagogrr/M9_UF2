package iticbcn.m9;
public class Motor extends Thread {
    private int potenciaActual;
    private int potenciaObjectiu;
    private boolean encendido;
    private final int id;

    public Motor(int id) {
        this.id = id;
        this.potenciaActual = 0;
        this.potenciaObjectiu = 0;
        this.encendido = true;
    }

    public void setPotencia(int p) {
        this.potenciaObjectiu = p;
    }

    @Override
    public void run() {
        while (encendido) {
            if (potenciaActual != potenciaObjectiu) {
                try {
                    Thread.sleep((long) (Math.random() * 1000 + 1000)); //Pausa la ejecución de un hilo durante un tiempo aleatorio entre 1 y 2 segundos, simulando un retraso
                    
                    if (potenciaActual < potenciaObjectiu) {
                        potenciaActual++;
                        System.out.printf("Motor %d: Incre. Objectiu: %d Actual: %d%n", 
                            id, potenciaObjectiu, potenciaActual);
                    } else if (potenciaActual > potenciaObjectiu) {
                        potenciaActual--;
                        System.out.printf("Motor %d: Decre. Objectiu: %d Actual: %d%n", 
                            id, potenciaObjectiu, potenciaActual);
                    }

                    if (potenciaActual == potenciaObjectiu) {
                        System.out.printf("Motor %d: FerRes Objectiu: %d Actual: %d%n", 
                            id, potenciaObjectiu, potenciaActual);
                        if (potenciaObjectiu == 0) {
                            encendido = false;
                        }
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            try {
                Thread.sleep(50);  // Small delay to prevent CPU overuse
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean isEncendido() {
        return encendido;
    }
}
