import javax.swing.SwingUtilities;

import janelas.JanelaPrincipal;

public class App
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JanelaPrincipal::new); //Cria a janela inicial do programa.
    }
}