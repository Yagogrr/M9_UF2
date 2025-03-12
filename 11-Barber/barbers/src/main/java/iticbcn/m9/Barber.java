package iticbcn.m9;

public class Barber extends Thread{
    public Barber(String nombre){
        super(nombre);
    }

    @Override
    public void run(){
        try{
            while (true) {
                Client client = Barberia.barberia.seguentClient();
                if(client!=null){
                    client.tallarseElCabell();
                } else{
                    Barberia.barberia.getCondBarber().wait();
                }
            }
        } catch (InterruptedException ie){

        }
    }
}