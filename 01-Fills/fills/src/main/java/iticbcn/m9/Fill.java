/*
 * Este es el código del comportamiento 1, los demas códigos estan puestos en el README
 */
package iticbcn.m9;

public class Fill extends Thread {
    public String nom;
    public Fill(String nom){
        this.nom = nom;
    }

    @Override
    public void run(){
        for(int i = 1;i<10;i++){
            System.out.printf("%s %d%n",this.nom,i);
        }
        System.out.println("Termina el fil "+this.nom);
    }
}
