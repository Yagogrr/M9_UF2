package com.iticbcn;

import java.util.List;

public class Esdeveniment {
    private final int MAX_PLACES;
    private List<Assistent> assistents;
    private int placesDisponibles;
    public Esdeveniment(int MAX_PLACES){
        this.MAX_PLACES = MAX_PLACES;
        this.placesDisponibles = MAX_PLACES;
    }
    public void ferReserva(Assistent assistent){
        try{
            if(this.assistents.contains(assistent)) { 
                System.out.printf("%s no ha pogut fer una reserva. Places disponibles: %d%n",assistent.getName(),this.getPlacesDisponibles());
                return;
            }
            while(this.placesDisponibles>=5){
                this.assistents.wait();
            }
            this.assistents.add(assistent);
            setPlacesDisponibles(this.placesDisponibles-1);
            System.out.printf("%s ha fet una reserva. Places disponibles: %d%n",assistent.getName(),this.getPlacesDisponibles());
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
    }
    public void cancelarReserva(Assistent assistent){
        try{
            if(this.assistents.contains(assistent)){
                while(this.placesDisponibles<=0){
                    assistent.wait();
                }
                this.assistents.remove(assistent);
                setPlacesDisponibles(this.placesDisponibles+1);
                System.out.printf("%s ha cancel.lat una reserva. Places disponibles: %d%n",assistent.getName(),this.getPlacesDisponibles());
                return;
            }
            System.out.printf("%s no ha pogut cancel.lar una reserva inexistent. Places disponibles: %d%n",assistent.getName(),this.getPlacesDisponibles());     
        } catch (InterruptedException e ){
            e.printStackTrace();
        }
        
    }
    
    public List<Assistent> getAssistents() {
        return assistents;
    }
    public void setAssistents(List<Assistent> assistents) {
        this.assistents = assistents;
    }
    public int getPlacesDisponibles() {
        return placesDisponibles;
    }
    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }
    
   
    
}