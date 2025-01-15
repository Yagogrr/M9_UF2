package iticbcn.m9;

public class Administració {
    public static final int NUM_POBLACIÓ_ACTIVA = 50;
    private Treballador[] poblacío_activa = new Treballador[NUM_POBLACIÓ_ACTIVA];
    public Administració(){
        for(int i = 0; i < NUM_POBLACIÓ_ACTIVA; i++){
            poblacío_activa[i] = new Treballador("Ciutadà-"+i, 20, 65, 25000);
        }
    }
    public Treballador[] getTreballadors(){
        return poblacío_activa;
    }
    public static void main(String[] args)throws InterruptedException {
        Administració admin = new Administració();
        for(int i = 0;i<NUM_POBLACIÓ_ACTIVA;i++){
            admin.getTreballadors()[i].start();
        }
        for(int i = 0;i<NUM_POBLACIÓ_ACTIVA;i++){
            admin.getTreballadors()[i].join();
        }
        for(int i = 0;i<NUM_POBLACIÓ_ACTIVA;i++){
            System.out.println(admin.getTreballadors()[i].toString());
        }
    }
}
