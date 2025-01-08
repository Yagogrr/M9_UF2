package iticbcn.m9;

public class Coet {
    public static void main(String[] args) {
        Motor[] motors = initMotors(4);
        arranca(motors);
        pasaAPotencia(3, motors);
        pasaAPotencia(7, motors);
        pasaAPotencia(0, motors);
        
    }
    public static Motor[] initMotors(int n){
        Motor[] motors = new Motor[n];
        for(int i = 0;i<n;i++){
            motors[i] = new Motor(Integer.toString(i));
        }
        return motors;
    }
    public static void pasaAPotencia(int p, Motor[] motors){
        if(p<0&&p>10){
            System.out.println("Potència no vàlida");
            return;
        }
        System.out.println("Pasant a potència "+p);
        for(Motor m: motors){
            m.setPotencia(p);
        }
    }
    public static void arranca(Motor[] motors){
        for(Motor m: motors){
            m.start();
        }
    }
}
