package iticbcn.m9;

public class Taula {
    private Filosof[] filosofs;
    private Forquilla[] forquilles;

    public Taula(int nombreFilosofs) {
        this.filosofs = new Filosof[nombreFilosofs];
        this.forquilles = new Forquilla[nombreFilosofs];
    
        // Iniciar filósofos
        for (int i = 0; i < nombreFilosofs; i++) {
            String nombre = "fil" + i;
            this.filosofs[i] = new Filosof(nombre, null, null);
        }
    
        // Iniciar forquilles
        for (int i = 0; i < nombreFilosofs; i++) {
            this.forquilles[i] = new Forquilla(i);
        }
    
        // Asignar las forquilles a los filósofos
        for (int i = 0; i < nombreFilosofs; i++) {
            this.filosofs[i].setForquillaEsquerra(this.forquilles[i]);
            this.filosofs[i].setForquillaDreta(this.forquilles[(i + 1) % nombreFilosofs]);
        }
    }
    

    public void showTaula() {
        System.out.println(this.toString());
    }

    public void cridarTaula() {
        for(Filosof f : this.filosofs){
            f.start();
        }
    }

    public static void main(String[] args) {
        Taula t = new Taula(4);
        t.showTaula();
        t.cridarTaula();
    }

    public Filosof[] getFilosofs() {
        return filosofs;
    }

    public void setFilosofs(Filosof[] filosofs) {
        this.filosofs = filosofs;
    }

    public Forquilla[] getForquilles() {
        return forquilles;
    }

    public void setForquilles(Forquilla[] forquilles) {
        this.forquilles = forquilles;
    }

    @Override
    public String toString() {
        StringBuilder taula = new StringBuilder("\n");
        for (int i = 0; i < filosofs.length; i++) {
            taula.append("Comensal:" + filosofs[i].getName() + " esq:" + filosofs[i].getForquillaEsquerra().getNumeroF() + " dret:"
                    + filosofs[i].getForquillaDreta().getNumeroF() + "\n");
        }
        return taula.toString();
    }
}