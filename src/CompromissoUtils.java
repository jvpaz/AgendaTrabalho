import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void inserirNoTXT(Compromisso comp)
    {//fazer com que insira as informações do CompromissoModelList em um [txt] ou Json
        String url = "bin\\agenda.txt";
        String conteudo = comp.getDescricao();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(url))){
            bw.write(conteudo);
            bw.newLine();
            System.out.println("Conteudo adicionado com sucesso");
        }catch (IOException e){
            System.err.println("Erro ao ler o arquivo");
        }
    }
}
