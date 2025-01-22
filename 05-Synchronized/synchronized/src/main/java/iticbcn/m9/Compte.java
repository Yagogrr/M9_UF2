package iticbcn.m9;

/*
 * PATRON SINGLETON: SOLO PODER "INSTANCIAR" UNA VEZ ESA CLASE
 */

public class Compte {
    private static Compte instance;
    private float saldo;

    /*
     * Hacemos el constructor privado sin nada para que java no te cree
     * un constructor por defecto publico y asi no se pueda instanciar
     */
    private Compte() {}

    /*
     * Con este metodo nos aseguramos de que solo se pueda instanciar una vez
     */
    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}