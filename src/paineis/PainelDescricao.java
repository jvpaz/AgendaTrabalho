package paineis;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Compromisso;

public class PainelDescricao extends JPanel{
    public PainelDescricao(Compromisso newCompromisso)
    {
        newCompromisso.setDescricao(JOptionPane.showInputDialog(this, "Adicione um novo compromisso:"));
    }
}
