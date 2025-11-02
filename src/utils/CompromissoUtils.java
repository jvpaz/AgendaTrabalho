package utils;

import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.DefaultListModel;
import model.Compromisso;

public final class CompromissoUtils {

    private CompromissoUtils() {} //Impede instância de classe auxiliar.

    public static void ordernarPorData(DefaultListModel<Compromisso> listaModels)
    {

        //Passa todos elementos da ListModel para um array temporário e depois ordena 
        //o array se baseando nos períodos de cada compromisso.
        ArrayList<Compromisso> lista = new ArrayList<>();
        for(int i = 0; i < listaModels.size(); i++)
        lista.add(listaModels.getElementAt(i));

        listaModels.clear();
        lista.sort(Comparator.comparing(e -> e.getPeriodo()));
        
        listaModels.addAll(lista);
    }

    public static void ordenarPorEstado(DefaultListModel<Compromisso> listaModels)
    {
        ArrayList<Compromisso> lista = new ArrayList<>();
        for(int i = 0; i < listaModels.size(); i++)
        lista.add(listaModels.getElementAt(i));

        listaModels.clear();
        lista.sort(Comparator.comparing(e -> e.getEstado()));
        
        listaModels.addAll(lista);
    }
}
