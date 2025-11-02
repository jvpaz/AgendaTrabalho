import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public final class ArquivoUtils {
    private ArquivoUtils () {} //Impede instância de classe auxiliar.
    public static List<Compromisso> lerTxt()
    {
        if (!checarPasta()) 
            criarPasta();

        if (!checarTxt())
            criarTxt();

        List<Compromisso> tempCompromissos = new ArrayList<>();

        try {
            BufferedReader leitor = Files.newBufferedReader(Path.of("log/comp.txt"));
            String linhas;
            

            while ((linhas = leitor.readLine()) != null) {
                String[] informacoes = linhas.split("\\|"); //Posicao [0] guarda a descrição, [1] a data;
                String descricao = informacoes[0];
                LocalDateTime data = LocalDateTime.parse(informacoes[1]);
                Estado estado = Estado.valueOf(informacoes[2]);
               tempCompromissos.add(new Compromisso(descricao, data, estado));    
            }

            leitor.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        return tempCompromissos;
    }

    private static void criarPasta()
    {
        Path pasta = Path.of("log");
        try {
            Files.createDirectories(pasta);
            criarTxt(); //Criar o arquivo Txt, por garantia.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checarPasta()
    {
        Path pasta = Path.of("log");

        return (Files.exists(pasta) && Files.isDirectory(pasta)) ? true : false;
    }

    private static void criarTxt()
    {
        Path arquivo = Path.of("log/comp.txt");
        try {
            Files.createFile(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checarTxt()
    {
        Path arquivo = Path.of("log/comp.txt");

        return (Files.exists(arquivo) && (Files.isWritable(arquivo) && Files.isReadable(arquivo))) ? true : false;
    }

    public static void salvarList(DefaultListModel<Compromisso> listaModels)
    {
        if (!checarPasta()) 
            criarPasta();

        if (!checarTxt())
            criarTxt();

        try {
            BufferedWriter leitor = Files.newBufferedWriter(Path.of("log/comp.txt"));
            
            ArrayList<Compromisso> lista = new ArrayList<>();
            
            for(int i = 0; i < listaModels.size(); i++)
                lista.add(listaModels.getElementAt(i));

           for(int i = 0; i < lista.size(); i++)   
            {
                leitor.write(lista.get(i).getDescricao() + "|" + lista.get(i).getPeriodo() + "|" + lista.get(i).geEstado().getDescricao() + "\n");
            }

            leitor.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
        
    }
}
