import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.DefaultListModel;

public class CompromissoUtils {
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
}
