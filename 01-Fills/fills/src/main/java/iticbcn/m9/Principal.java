/*
 * Este es el código del comportamiento 1, los demas códigos estan puestos en el README
 */
package iticbcn.m9;

public class Principal {
    public static void main(String[] args) {
        Fill f_juan = new Fill("Juan");
        Fill f_pepe = new Fill("Pepe");

        f_juan.start();
        f_pepe.start();
        System.out.println("Termina thread main");
    }
}
