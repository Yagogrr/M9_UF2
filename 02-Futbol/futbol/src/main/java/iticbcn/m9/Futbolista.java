package iticbcn.m9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Futbolista extends Thread {
    private final static int NUM_JUGADORS = 11;
    private final static int NUM_TIRADES = 20;
    private final static float PROBABILITAT = 0.5f;

    private int ngols;
    private int ntirades;

    public Futbolista(String nom) {
        super(nom);
        this.ngols = 0;
        this.ntirades = 0;
    }

    public int getGoals() {
        return ngols;
    }

    public static void main(String[] args) {
        String[] nombres = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"};
        Futbolista[] futbolistas = crearFills(nombres);
        penaltis(futbolistas);
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            this.ntirades++;
            if (new Random().nextFloat() < PROBABILITAT) { // posibilidad de marcar del 50%
                this.ngols++;
            }
        }
    }

    public static Futbolista[] crearFills(String[] nombres) {
        Futbolista[] futbolistas = new Futbolista[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            futbolistas[i] = new Futbolista(nombres[i]);
        }
        return futbolistas;
    }

    public static void penaltis(Futbolista[] futbolistas) {
        System.out.println("Inici dels xuts --------------------");
        for (Futbolista futbolista : futbolistas) {
            futbolista.start();
        }

        // Asegurarse de que todos los hilos terminen
        for (Futbolista futbolista : futbolistas) {
            try {
                futbolista.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");
        for (Futbolista futbolista : futbolistas) {
            String nombreFutbolista = futbolista.getName();
            int goles = futbolista.getGoals();
            System.out.printf("%s         -> %d gols%n", nombreFutbolista, goles);
        }
    }
}
