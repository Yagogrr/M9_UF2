package iticbcn.m9;

public class Motor extends Thread{
    int pActual;
    int pObjectiu;
    public Motor(String name){
        super(name);
        pActual = 0;
        pObjectiu = 0;
    }
    public void setPotencia(int p){
        this.pObjectiu = p;
    }

    @Override
    public void run(){
        try{
            String incredecre;
            while(true){
                while(pActual!=pObjectiu){
                    if(pObjectiu<pActual){
                        incredecre = "Decre.";
                        pActual--;
                        System.out.printf("Motor %s: %s Objectiu: %d Actual: %d%n",getName(),incredecre,pObjectiu,pActual);
                    } else {
                        incredecre = "Incre.";
                        pActual++;
                        System.out.printf("Motor %s: %s Objectiu: %d Actual: %d%n",getName(),incredecre,pObjectiu,pActual);
                    }
                    System.out.printf("Motor %s: FerRes Objectiu: %d Actual: %d%n",getName(),pObjectiu,pActual);
                    sleep(2000);
                }
                sleep(100);
                if(pActual==0){
                    return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
