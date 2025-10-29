import javax.swing.*;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import java.awt.*;
import java.time.LocalDateTime;

public class JanelaPrincipal extends JFrame {
    private DefaultListModel<Compromisso> CompromissoListModel; //Modelo da lista de compromissos
    private JList<Compromisso> CompromissoList; //Lista de compromissos
    private JButton addButton, editButton, deleteButton; //Botões de adicionar, deletar e editar.

public JanelaPrincipal() {
        //Estabelece informações básicas da janela:
        setTitle("Agenda de Compromissos"); //Nome
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //O que fazer caso fechar a janela.
        setSize(400, 400); //Tamanho
        setResizable(false); //Se é Ajustavél
        setLocationRelativeTo(null); //Posição inicial

        //Cria o painel das listas para ser posicionado na janela.
        //ListModel guarda os dados, List mostra os dados visualmente no painel.
        CompromissoListModel = new DefaultListModel<>();
        CompromissoList = new JList<>(CompromissoListModel);
        CompromissoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(CompromissoList);

        //Dá nome aos botões.
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Deletar");

        //Cria os botões e os posiciona em seu painel respectivo.
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        //Arruma a posição de cada painel.
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //Adiciona um evento a cada botão criado.
        addButton.addActionListener(e -> addCompromisso());
        editButton.addActionListener(e -> editCompromisso());
        deleteButton.addActionListener(e -> deleteCompromisso());

        //Torna a janela visivel.
        setVisible(true);
}

private void addCompromisso() {
    Compromisso newCompromisso = new Compromisso();

    //Cria caixa de dialógo para descrição do compromisso.
    newCompromisso.setDescricao(JOptionPane.showInputDialog(this, "Adicione um novo compromisso:"));

    //Checa para ver se está vazio.
    if (newCompromisso.getDescricao() == null || newCompromisso.getDescricao().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma descrição!");
        return; 
    }

    //Variavéis para receber informações dos calendários
    DatePicker datePicker = new DatePicker();
    TimePicker timePicker = new TimePicker();

    //Cria painéis dos calendários
    JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
    panel.add(new JLabel("Selecione a data:")); 
    panel.add(datePicker);
    panel.add(new JLabel("Selecione o horário:"));
    panel.add(timePicker);

    int result = JOptionPane.showConfirmDialog(this, panel, "Selecione a data e horário", JOptionPane.OK_CANCEL_OPTION);

    //Checa se horário ou data estão inválidos ou vazios
    if (result == JOptionPane.OK_OPTION && datePicker.getDate() == null && timePicker.getTime() == null) {
        JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma data/horário válido!");
        return;
    }

    LocalDateTime dateTime = LocalDateTime.of(datePicker.getDate(), timePicker.getTime());
    newCompromisso.setPeriodo(dateTime);

    CompromissoListModel.addElement(newCompromisso);
}

private void editCompromisso() {
    //Procura pelo index de compromisso através da função getSelectIndex oferecido pela List
        int selectedIndex = CompromissoList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Compromisso para edição inválido ou nulo!");
            return;
        }

        Compromisso newCompromisso = CompromissoListModel.getElementAt(selectedIndex);
        newCompromisso.setDescricao(JOptionPane.showInputDialog(this, "Edite o compromisso:", newCompromisso.getDescricao()));
        if (newCompromisso.getDescricao() == null && newCompromisso.getDescricao().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma descrição!");
            return;
        }

        CompromissoListModel.setElementAt(newCompromisso, selectedIndex);
}

private void deleteCompromisso() {
        int selectedIndex = CompromissoList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Compromisso para exclusão inválido ou nulo!.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this, "Tem certeza que deseja deletar esse compromisso?", "", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            CompromissoListModel.remove(selectedIndex);
        }
}
}
