package iticbcn.m9;

public class MainDemoFil {
    public static void main(String[] args) {
        // Obtenim el fil actual
        Thread currentThread = Thread.currentThread();

        // Mostrem informació sobre el fil principal
        System.out.println("MainDemoFil.main:");
        System.out.println("Prioritat -> " + currentThread.getPriority());
        System.out.println("Nom -> " + currentThread.getName());
        System.out.println("toString() -> " + currentThread);
    }
}
