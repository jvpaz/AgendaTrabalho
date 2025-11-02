package paineis;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Compromisso;

public class PainelDescricao extends JPanel{
    public PainelDescricao(Compromisso newCompromisso)
    {
        //Exibe painel de dialógo para usuário com caixa de entrada para descrição, e atribui o descrição a newCompromisso
        newCompromisso.setDescricao(JOptionPane.showInputDialog(this, "Adicione um novo compromisso:", JOptionPane.YES_NO_OPTION));
    }
}
