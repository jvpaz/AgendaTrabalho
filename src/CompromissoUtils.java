import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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
        LocalDateTime periodo = comp.getPeriodo();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(url, true))){//true faz com que adicione no final do arquivo
            System.out.println("Descriçao: "+comp.getDescricao());// botei pra poder debugar
            System.out.println("Periodo: "+comp.getPeriodo());// botei pra poder debugar
            String saida = "Descriçao: " + conteudo + " Perido: " + periodo;
            bw.write(saida);
            bw.newLine();
            System.out.println("Conteudo adicionado com sucesso");
        }catch (IOException e){
            System.err.println("Erro ao ler o arquivo");
        }
    }

    //manter comentado por enquanto, vou continuar isso depois
    //public static void removerDoTXT(DefaultListModel<Compromisso> CompromissoListModel, int selectedIndex)
    //{
        //String url = "bin\\agenda.txt";
        //String desc = CompromissoListModel.getElementAt(selectedIndex).getDescricao();
        //LocalDateTime periodo = CompromissoListModel.getElementAt(selectedIndex).getPeriodo();
        //String saida = "Descriçao: " + desc + " Perido: " + periodo;
        //System.out.println();
        //System.out.println(CompromissoListModel.getElementAt(selectedIndex));// em teoria ta pegado oque ta sendo removido
    
        //try {BufferedReader br = new BufferedReader(new FileReader(url));
        //    while(br.readLine() != null)
        //    {
        //        if(br.equals(saida)){//Continuar depois
        //            System.out.println("SAIDA ACHADA");
        //        }
        //    }
            
        //} catch (IOException e) {
        //    System.err.println("Erro ao ler o arquivo");
        //}
    //}
}
