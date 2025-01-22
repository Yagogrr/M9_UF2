package iticbcn.m9;

import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio = 10f;
    private int esperaMax = 100;
    private Random random = new Random();
    private int maxAnys = 10;

    public Soci() {
        this.compte = Compte.getInstance();
    }

    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run() {
        for (int any = 0; any < maxAnys; any++) {
            for (int mes = 0; mes < 12; mes++) {
                /* Ahora sincronizado
                * Los meses pares se hace un ingreso a la cuenta
                * Los meses impares se hace un retiro de la cuenta
                */
                synchronized (compte) {
                    float saldoActual = compte.getSaldo();
                    if (mes % 2 == 0) {
                        compte.setSaldo(saldoActual + aportacio);
                    } else {
                        compte.setSaldo(saldoActual - aportacio);
                    }
                }
                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}