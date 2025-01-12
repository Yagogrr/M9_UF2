package iticbcn.m9;

import java.util.Scanner;

public class Coet {
    private final Motor[] motors;
    private static final int MAX_POTENCIA = 10;
    
    public Coet() {
        motors = new Motor[4];
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i);
        }
    }
    
    public void passaAPotencia(int p) throws IllegalArgumentException {
        if (p < 0 || p > MAX_POTENCIA) {
            throw new IllegalArgumentException("La potència ha d'estar entre 0 i " + MAX_POTENCIA);
        }
        System.out.println("Passant a potència " + p);
        for (Motor motor : motors) {
            motor.setPotencia(p);
        }
    }
    
    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int potencia = scanner.nextInt();
                passaAPotencia(potencia);
                if (potencia == 0) {
                    // Esperar a que todos los motores se detengan
                    boolean allStopped;
                    do {
                        allStopped = true;
                        for (Motor motor : motors) {
                            if (motor.isEncendido()) {
                                allStopped = false;
                                break;
                            }
                        }
                        Thread.sleep(100);
                    } while (!allStopped);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
    
    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
    }
}