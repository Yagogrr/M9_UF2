package com.iticbcn;

import java.util.ArrayList;
import java.util.List;

public class Organitzador {
    public static void main(String[] args) {
        Esdeveniment e = new Esdeveniment(5);
        List<Assistent> assistents = new ArrayList<Assistent>();
        for(int i = 0;i<10;i++){
            assistents.add(new Assistent("Asistent-"+i, e));
        }
        e.setAssistents(assistents);
        for(int i = 0;i<10;i++){
           e.getAssistents().get(i).start();
        }
    }
}
