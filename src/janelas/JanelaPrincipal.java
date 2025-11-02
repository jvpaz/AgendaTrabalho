package janelas;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Compromisso;
import model.Estado;
import paineis.PainelDescricao;
import paineis.PainelHorario;
import utils.ArquivoUtils;
import utils.CompromissoUtils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JanelaPrincipal extends JFrame {
    private DefaultListModel<Compromisso> CompromissoListModel; //Modelo da lista de compromissos
    private JList<Compromisso> CompromissoList; //Lista de compromissos
    private JButton addButton, editButton, deleteButton; //Botões de adicionar, deletar e editar.
    private JRadioButton radioData, radioState;
    private ButtonGroup radioButtonGroup;

public JanelaPrincipal() {
        //Estabelece informações básicas da janela:
        setTitle("Agenda de Compromissos"); //Nome
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //O que fazer caso fechar a janela.
        setSize(400, 400); //Tamanho
        setResizable(false); //Se é Ajustavél
        setLocationRelativeTo(null); //Posição inicial

        //Cria o painel das listas para ser posicionado na janela.
        //ListModel guarda os dados, List mostra os dados visualmente no painel.
        CompromissoListModel = new DefaultListModel<>();
        CompromissoList = new JList<>(CompromissoListModel);
        CompromissoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        CompromissoListModel.addAll(ArquivoUtils.lerTxt());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int check = JOptionPane.showConfirmDialog(JanelaPrincipal.this, "Deseja salvar alterações antes de sair?", "Sair", JOptionPane.YES_NO_OPTION);
                if(check == JOptionPane.YES_NO_OPTION)
                ArquivoUtils.salvarList(CompromissoListModel); 

                dispose();             
                System.exit(0);        
            }
        });
        

        JScrollPane scrollPane = new JScrollPane(CompromissoList);

        //Dá nome aos botões.
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Deletar");


        radioData = new JRadioButton("Periodo");
        radioState = new JRadioButton("Estado");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioData);
        radioButtonGroup.add(radioState);

        JPanel radioPanel = new JPanel(new GridLayout(3, 1, -70, -70));
        JLabel radioLabel = new JLabel("Ordenar por:");
        radioPanel.add(radioLabel);
        radioPanel.add(radioData);
        radioPanel.add(radioState);

        radioData.addActionListener(e -> CompromissoUtils.ordernarPorData(CompromissoListModel));
        radioState.addActionListener(e -> CompromissoUtils.ordenarPorEstado(CompromissoListModel));
        

        //Cria os botões e os posiciona em seu painel respectivo.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        //Arruma a posição de cada painel.
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(radioPanel, BorderLayout.EAST);

        //Adiciona um evento a cada botão criado.
        addButton.addActionListener(e -> addCompromisso());
        editButton.addActionListener(e -> editCompromisso());
        deleteButton.addActionListener(e -> deleteCompromisso());

        //Torna a janela visivel.
        setVisible(true);
}

private void addCompromisso() {
    Compromisso novo = new Compromisso();

    // Painel de descrição
    new PainelDescricao(novo);
    if (novo.getDescricao() == null || novo.getDescricao().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma descrição!");
        return;
    }

    // Painel de data e hora
    new PainelHorario(novo);
    if (novo.getPeriodo() == null) {
        JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma data/horário válido!");
        return;
    }

    // Adiciona à lista apenas se os dados forem válidos
    CompromissoListModel.addElement(novo);
}

private void editCompromisso() {
    int selectedIndex = CompromissoList.getSelectedIndex();
    if (selectedIndex == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um compromisso válido para edição!");
        return;
    }

    Compromisso compromisso = CompromissoListModel.getElementAt(selectedIndex);

    int check = JOptionPane.showConfirmDialog(this, "Deseja editar a descrição do compromisso selecionado?", "Descrição", JOptionPane.YES_NO_OPTION);

    if (check == JOptionPane.YES_OPTION) {
        new PainelDescricao(compromisso);
        if (compromisso.getDescricao() == null || compromisso.getDescricao().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma descrição!");
            return;
    }
    }
    
    check = JOptionPane.showConfirmDialog(this, "Deseja editar o período do compromisso selecionado?", "Período", JOptionPane.YES_NO_OPTION);
    if (check == JOptionPane.YES_OPTION) {
         new PainelHorario(compromisso);
        if (compromisso.getPeriodo() == null) {
            JOptionPane.showMessageDialog(this, "O compromisso deve possuir uma data/horário válido!");
            return;
    }
    }
   
    check = JOptionPane.showConfirmDialog(this, "Deseja mudar o estado do compromisso selecionado?", "Estado", JOptionPane.YES_NO_OPTION);
    if (check == JOptionPane.YES_OPTION) {
        compromisso.setEstado(Estado.Pendente == compromisso.getEstado() ? Estado.Concluida : Estado.Pendente);
    }

    // Atualiza na lista
    CompromissoListModel.setElementAt(compromisso, selectedIndex);
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
